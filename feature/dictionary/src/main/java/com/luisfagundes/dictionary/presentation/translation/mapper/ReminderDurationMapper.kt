package com.luisfagundes.dictionary.presentation.translation.mapper

import com.luisfagundes.dictionary.domain.model.ReminderDuration
import com.luisfagundes.dictionary.domain.model.ReminderDuration.ONE_WEEK
import com.luisfagundes.dictionary.domain.model.ReminderDuration.TWO_WEEKS
import com.luisfagundes.dictionary.domain.model.ReminderDuration.ONE_MONTH
import com.luisfagundes.dictionary.presentation.translation.components.UiReminderDuration

internal class ReminderDurationMapper {
    fun ReminderDuration.mapToUi(): UiReminderDuration {
        return when (this) {
            ONE_WEEK -> UiReminderDuration.ONE_WEEK
            TWO_WEEKS -> UiReminderDuration.TWO_WEEKS
            ONE_MONTH -> UiReminderDuration.ONE_MONTH
        }
    }
}