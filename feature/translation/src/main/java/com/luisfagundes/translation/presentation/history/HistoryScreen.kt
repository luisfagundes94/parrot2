package com.luisfagundes.translation.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.translation.R
import com.luisfagundes.translation.presentation.history.components.SavedWordItem

@Composable
internal fun HistoryRoute(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    
    HistoryScreen(
        uiState = uiState.value,
        onDeleteWord = viewModel::deleteWordFromHistory,
        onClearAll = viewModel::showClearAllDialog,
        onConfirmClearAll = viewModel::clearAllHistory,
        onDismissClearAllDialog = viewModel::hideClearAllDialog
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HistoryScreen(
    uiState: HistoryUiState,
    onDeleteWord: (Long) -> Unit,
    onClearAll: () -> Unit,
    onConfirmClearAll: () -> Unit,
    onDismissClearAllDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    
    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(uiState.errorMessage)
        }
    }
    
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            if (uiState.words.isNotEmpty()) {
                ExtendedFloatingActionButton(
                    onClick = onClearAll,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.DeleteSweep,
                            contentDescription = stringResource(R.string.clear_all_history)
                        )
                    },
                    text = {
                        Text(text = stringResource(R.string.clear_all))
                    }
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                
                uiState.isEmpty -> {
                    EmptyHistoryContent(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(MaterialTheme.spacing.default),
                        verticalArrangement = Arrangement.spacedBy(
                            space = MaterialTheme.spacing.small
                        )
                    ) {
                        items(
                            items = uiState.words,
                            key = { it.id }
                        ) { word ->
                            SavedWordItem(
                                word = word,
                                onDelete = onDeleteWord
                            )
                        }
                    }
                }
            }
        }
    }

    if (uiState.showClearAllDialog) {
        AlertDialog(
            onDismissRequest = onDismissClearAllDialog,
            title = {
                Text(text = stringResource(R.string.clear_all_history_title))
            },
            text = {
                Text(text = stringResource(R.string.clear_all_history_message))
            },
            confirmButton = {
                TextButton(onClick = onConfirmClearAll) {
                    Text(text = stringResource(R.string.clear_all))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissClearAllDialog) {
                    Text(text = stringResource(android.R.string.cancel))
                }
            }
        )
    }
}

@Composable
private fun EmptyHistoryContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.empty_history_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        
        Text(
            text = stringResource(R.string.empty_history_message),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = MaterialTheme.spacing.small)
        )
    }
}