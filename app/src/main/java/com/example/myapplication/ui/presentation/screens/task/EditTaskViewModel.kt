package com.example.myapplication.ui.presentation.screens.task


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Task
import com.example.myapplication.data.repositories.TaskRepository
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Calendar

class EditTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {


    var taskTitle: String by mutableStateOf("")
        private set
    var taskDescription: String by mutableStateOf("")
        private set
    var taskPriority: Int by mutableStateOf(1)
        private set
    var endDate by mutableStateOf(formatDate(Calendar.getInstance().timeInMillis))
        private set

    var isDatePickerOpen by mutableStateOf(false)
    fun setTitle(newTitle: String) {
        taskTitle = newTitle
    }
    fun setDescription(description: String) {
        taskDescription = description
    }

    fun setPriority(priority: Int) {
        taskPriority = priority
    }

    fun setEndDate(dateInMills: Long) {
        endDate = formatDate(dateInMills)
    }

    private fun formatDate(dateInMills: Long): String =
        DateFormat.getDateInstance(DateFormat.MEDIUM).format(dateInMills)

    fun addTask() {
        if(verifyTask()){
            viewModelScope.launch {
                taskRepository.addTask(
                    Task(
                        title = taskTitle,
                        description = taskDescription,
                        startDate = formatDate(Calendar.getInstance().timeInMillis),
                        endDate = endDate,
                        priority = taskPriority
                    )
                )
            }
        }
    }

    fun verifyTask():Boolean {
        return (taskTitle.isNotEmpty() and taskDescription.isNotBlank())
    }
}

