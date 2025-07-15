package com.luisfagundes.dictionary.presentation

import app.cash.turbine.test
import com.luisfagundes.common.provider.ResourceProvider
import com.luisfagundes.dictionary.R
import com.luisfagundes.dictionary.domain.model.SupportedLanguage.English
import com.luisfagundes.dictionary.domain.model.SupportedLanguage.Portuguese
import com.luisfagundes.dictionary.domain.model.TranslationParams
import com.luisfagundes.dictionary.domain.usecase.GetTranslationsUseCase
import com.luisfagundes.dictionary.utils.mockWords
import com.luisfagundes.dictionary.utils.mockWordsWithExamples
import com.luisfagundes.testing.rule.MainCoroutineDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DictionaryViewModelTest {

    @get:Rule
    val mainCoroutineDispatcherRule = MainCoroutineDispatcherRule()

    private val getTranslationsUseCase = mockk<GetTranslationsUseCase>()
    private val resourceProvider = mockk<ResourceProvider>()
    private val testDispatcher = UnconfinedTestDispatcher()

    private val initialState = DictionaryUiState()

    private lateinit var viewModel: DictionaryViewModel

    @Before
    fun setup() {
        viewModel = DictionaryViewModel(
            getTranslationsUseCase = getTranslationsUseCase,
            resourceProvider = resourceProvider,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `initial state should have default values`() = runTest {
        viewModel.uiState.test {
            assertEquals(initialState, awaitItem())
        }
    }

    @Test
    fun `translate with blank text should set error state`() = runTest {
        val errorMessage = "Input cannot be blank"
        every { resourceProvider.getString(R.string.blank_input_text_error) } returns errorMessage

        viewModel.uiState.test {
            awaitItem() // Initial state

            viewModel.translate("   ")

            val errorState = awaitItem()
            assertEquals(initialState.setError(errorMessage), errorState)
        }

        verify { resourceProvider.getString(R.string.blank_input_text_error) }
    }

    @Test
    fun `translate with valid text should show loading then success state`() = runTest {
        val inputText = "hello"

        coEvery {
            getTranslationsUseCase.invoke(any())
        } returns flowOf(mockWords)

        viewModel.uiState.test {
            awaitItem() // Initial state

            viewModel.translate(inputText)

            val loadingState = awaitItem()
            assertEquals(initialState.setLoading(true), loadingState)

            val successState = awaitItem()
            assertEquals(initialState.setResult(mockWords), successState)
        }
    }

    @Test
    fun `translate with empty response should set error state`() = runTest {
        val inputText = "nonexistent"
        val errorMessage = "Translation not found"

        coEvery {
            getTranslationsUseCase.invoke(any())
        } returns flowOf(emptyList())

        every { resourceProvider.getString(R.string.translation_not_found) } returns errorMessage

        viewModel.uiState.test {
            awaitItem() // Initial state

            viewModel.translate(inputText)

            awaitItem() // Loading state

            val errorState = awaitItem()
            assertEquals(initialState.setError(errorMessage), errorState)
        }

        verify { resourceProvider.getString(R.string.translation_not_found) }
    }

    @Test
    fun `translate with exception should set error state`() = runTest {
        val inputText = "hello"
        val errorMessage = "Network error"

        coEvery {
            getTranslationsUseCase.invoke(any())
        } returns flow { throw Exception(errorMessage) }

        viewModel.uiState.test {
            awaitItem() // Initial state

            viewModel.translate(inputText)

            awaitItem() // Loading state

            val errorState = awaitItem()
            assertEquals(initialState.setError(errorMessage), errorState)
        }
    }

    @Test
    fun `swapLanguagePair should swap source and target languages`() = runTest {
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(English, initialState.languagePair.first)
            assertEquals(Portuguese, initialState.languagePair.second)

            viewModel.swapLanguagePair()

            val swappedState = awaitItem()
            assertEquals(Portuguese, swappedState.languagePair.first)
            assertEquals(English, swappedState.languagePair.second)
        }
    }

    @Test
    fun `translate should create correct TranslationParams`() = runTest {
        val inputText = "hello"
        val paramsSlot = slot<TranslationParams>()

        coEvery {
            getTranslationsUseCase.invoke(capture(paramsSlot))
        } returns flowOf(mockWords)

        viewModel.translate(inputText)

        assertEquals(inputText, paramsSlot.captured.query)
        assertEquals(English.code, paramsSlot.captured.sourceLanguage)
        assertEquals(Portuguese.code, paramsSlot.captured.targetLanguage)
    }

    @Test
    fun `translate should create correct TranslationParams after language swap`() = runTest {
        val inputText = "olá"
        val paramsSlot = slot<TranslationParams>()

        coEvery {
            getTranslationsUseCase.invoke(capture(paramsSlot))
        } returns flowOf(mockWords)

        viewModel.swapLanguagePair()
        viewModel.translate(inputText)

        assertEquals(inputText, paramsSlot.captured.query)
        assertEquals(Portuguese.code, paramsSlot.captured.sourceLanguage)
        assertEquals(English.code, paramsSlot.captured.targetLanguage)
    }

    @Test
    fun `hasExamples should return true when words have examples`() = runTest {
        coEvery {
            getTranslationsUseCase.invoke(any())
        } returns flowOf(mockWordsWithExamples)

        viewModel.uiState.test {
            awaitItem() // Initial state

            viewModel.translate("hello")

            awaitItem() // Loading state

            val successState = awaitItem()
            assertTrue(successState.hasExamples)
        }
    }

    @Test
    fun `hasExamples should return false when words have no examples`() = runTest {
        coEvery {
            getTranslationsUseCase.invoke(any())
        } returns flowOf(mockWords)

        viewModel.uiState.test {
            awaitItem() // Initial state

            viewModel.translate("hello")

            awaitItem() // Loading state

            val successState = awaitItem()
            assertFalse(successState.hasExamples)
        }
    }
}