package com.luisfagundes.dictionary.presentation.translation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.luisfagundes.designsystem.theme.LightAndDarkPreview
import com.luisfagundes.designsystem.theme.ParrotTheme
import com.luisfagundes.designsystem.theme.spacing
import com.luisfagundes.dictionary.R

@Composable
internal fun WordReminderDialog(
    onDismissRequest: () -> Unit,
    onSetReminder: (frequency: UiReminderFrequency, duration: UiReminderDuration) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFrequency by remember { mutableStateOf(UiReminderFrequency.DAILY) }
    var selectedDuration by remember { mutableStateOf(UiReminderDuration.ONE_WEEK) }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.spacing.default)
            ) {
                Text(
                    text = stringResource(R.string.set_reminder_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(
                    modifier = Modifier.height(MaterialTheme.spacing.default)
                )
                Text(
                    text = stringResource(R.string.reminder_dialog_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(
                    modifier = Modifier.height(MaterialTheme.spacing.default)
                )
                FrequencyOptions(
                    selectedFrequency = selectedFrequency,
                    onSelectFrequency = { selectedFrequency = it }
                )
                Spacer(
                    modifier = Modifier.height(MaterialTheme.spacing.default)
                )
                DurationOptions(
                    selectedDuration = selectedDuration,
                    onSelectDuration = { selectedDuration = it }
                )
                Spacer(
                    modifier = Modifier.height(MaterialTheme.spacing.default)
                )
                ActionButtons(
                    onDismissRequest = onDismissRequest,
                    onSetReminder = onSetReminder,
                    selectedFrequency = selectedFrequency,
                    selectedDuration = selectedDuration
                )
            }
        }
    }
}

@Composable
private fun ActionButtons(
    onDismissRequest: () -> Unit,
    onSetReminder: (UiReminderFrequency, UiReminderDuration) -> Unit,
    selectedFrequency: UiReminderFrequency,
    selectedDuration: UiReminderDuration
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(
            onClick = onDismissRequest
        ) {
            Text(text = stringResource(R.string.cancel))
        }
        Spacer(
            modifier = Modifier.width(MaterialTheme.spacing.verySmall)
        )
        Button(
            onClick = {
                onSetReminder(selectedFrequency, selectedDuration)
            }
        ) {
            Text(text = stringResource(R.string.set_reminder))
        }
    }
}

@Composable
private fun FrequencyOptions(
    selectedFrequency: UiReminderFrequency,
    onSelectFrequency: (UiReminderFrequency) -> Unit
) {
    Text(
        text = stringResource(R.string.reminder_frequency_label),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    UiReminderFrequency.entries.forEach { frequency ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = selectedFrequency == frequency,
                    onClick = { onSelectFrequency(frequency) }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedFrequency == frequency,
                onClick = { onSelectFrequency(frequency) }
            )
            Text(
                text = stringResource(frequency.labelRes),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun DurationOptions(
    selectedDuration: UiReminderDuration,
    onSelectDuration: (UiReminderDuration) -> Unit
) {
    Text(
        text = stringResource(R.string.reminder_duration_label),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    UiReminderDuration.entries.forEach { duration ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = selectedDuration == duration,
                    onClick = { onSelectDuration(duration) }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedDuration == duration,
                onClick = { onSelectDuration(duration) }
            )
            Text(
                text = stringResource(duration.labelRes),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

internal enum class UiReminderFrequency(@param:StringRes val labelRes: Int) {
    DAILY(R.string.daily),
    WEEKLY(R.string.weekly),
}

internal enum class UiReminderDuration(@param:StringRes val labelRes: Int) {
    ONE_WEEK(R.string.one_week),
    TWO_WEEKS(R.string.two_weeks),
    ONE_MONTH(R.string.one_month),
}

@Composable
@LightAndDarkPreview
internal fun ReminderDialogPreview() {
    ParrotTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            WordReminderDialog(
                onDismissRequest = {},
                onSetReminder = { _, _ -> },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}