package com.luisfagundes.dictionary.presentation.translation.mapper

import com.luisfagundes.dictionary.domain.model.ReminderFrequency
import com.luisfagundes.dictionary.presentation.translation.components.UiReminderFrequency

internal class ReminderFrequencyMapper {
    fun ReminderFrequency.mapToUi(): UiReminderFrequency {
        return when (this) {
            ReminderFrequency.DAILY -> UiReminderFrequency.DAILY
            ReminderFrequency.WEEKLY -> UiReminderFrequency.WEEKLY
        }
    }

    fun UiReminderFrequency.mapToDomain(): ReminderFrequency {
        return when (this) {
            UiReminderFrequency.DAILY -> ReminderFrequency.DAILY
            UiReminderFrequency.WEEKLY -> ReminderFrequency.WEEKLY
        }
    }
}