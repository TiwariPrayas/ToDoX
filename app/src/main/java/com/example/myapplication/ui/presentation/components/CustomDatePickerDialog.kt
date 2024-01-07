package com.example.myapplication.ui.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState,
    isOpen: Boolean,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
) {
    if (isOpen) {
        DatePickerDialog(
            modifier = modifier,
            confirmButton = confirmButton,
            dismissButton = dismissButton,
            onDismissRequest = onDismissRequest,
            tonalElevation = 10.dp
        ) {
            DatePicker(
                state = datePickerState,
                dateFormatter = DatePickerFormatter(DatePickerDefaults.YearAbbrMonthDaySkeleton),
                showModeToggle = false
            )
        }
    }
}