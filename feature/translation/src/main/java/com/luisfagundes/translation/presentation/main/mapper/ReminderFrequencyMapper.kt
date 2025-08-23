package com.luisfagundes.translation.presentation.main.mapper

import com.luisfagundes.translation.domain.model.ReminderFrequency
import com.luisfagundes.translation.presentation.main.components.UiReminderFrequency

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