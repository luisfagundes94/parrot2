package com.luisfagundes.translation.presentation.main.mapper

import com.luisfagundes.translation.domain.model.ReminderDuration
import com.luisfagundes.translation.presentation.main.components.UiReminderDuration

internal class ReminderDurationMapper {
    fun ReminderDuration.mapToUi(): UiReminderDuration {
        return when (this) {
            ReminderDuration.ONE_WEEK -> UiReminderDuration.ONE_WEEK
            ReminderDuration.TWO_WEEKS -> UiReminderDuration.TWO_WEEKS
            ReminderDuration.ONE_MONTH -> UiReminderDuration.ONE_MONTH
        }
    }

    fun UiReminderDuration.mapToDomain(): ReminderDuration {
        return when (this) {
            UiReminderDuration.ONE_WEEK -> ReminderDuration.ONE_WEEK
            UiReminderDuration.TWO_WEEKS -> ReminderDuration.TWO_WEEKS
            UiReminderDuration.ONE_MONTH -> ReminderDuration.ONE_MONTH
        }
    }
}