package com.example.myapplication.ui.presentation.screens.task


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.presentation.components.CustomDatePickerDialog
import com.example.myapplication.ui.presentation.navigation.NavigationDestination
import java.util.Calendar

object EditTaskDestination : NavigationDestination {
    override val route: String = "edit"
    override val titleRes: Int = R.string.edit_title
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: EditTaskViewModel,
    onBackNavigationClick: () -> Unit,
    onSubmitClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Task",
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackNavigationClick
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            val datePickerState: DatePickerState = rememberDatePickerState(
                initialDisplayMode = DisplayMode.Picker,
                initialSelectedDateMillis = Calendar.getInstance().timeInMillis
            )

            OutlinedTextField(
                label = {
                    Text(text = "Title")
                },
                placeholder = {
                    Text(text = "What is the task about ?")
                },
                modifier = Modifier.fillMaxWidth(),
                isError = viewModel.taskTitle.isBlank(),
                singleLine = true,
                value = viewModel.taskTitle,
                onValueChange = { newValue ->
                    viewModel.setTitle(newValue)
                }
            )
            OutlinedTextField(
                label = {
                    Text(text = "Description")
                },
                placeholder = {
                    Text(text = "What does the task do ?")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                value = viewModel.taskDescription,
                isError = viewModel.taskDescription.isBlank(),
                onValueChange = {
                    value -> viewModel.setDescription(value)
                }
            )

            Text(
                text = "Priority",
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (i in 1..3) {
                    val text = when (i) {
                        1 -> "High"
                        2 -> "Medium"
                        else -> "Low"
                    }
                    FilterChip(
                        selected = i == viewModel.taskPriority,
                        onClick = {
                            viewModel.setPriority(i)
                        },
                        label = {
                            Text(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                text = text,
                                fontSize = 16.sp
                            )
                        })
                }
            }

            CustomDatePickerDialog(
                datePickerState = datePickerState,
                isOpen = viewModel.isDatePickerOpen,
                onDismissRequest = {
                    viewModel.isDatePickerOpen = false
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.setEndDate(datePickerState.selectedDateMillis!!)
                        viewModel.isDatePickerOpen = false
                    }) {
                        Text(text = "Select")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.isDatePickerOpen = false
                    }) {
                        Text(text = "Dismiss")
                    }
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Deadline")
                },
                readOnly = true,
                placeholder = {
                    Text(text = viewModel.endDate)
                },
                value = viewModel.endDate,
                onValueChange = {
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.isDatePickerOpen = true
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSubmitClick
            ) {
                Text(text = "Add Task")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditTaskScreenPreview() {
//    EditTaskScreen()
}