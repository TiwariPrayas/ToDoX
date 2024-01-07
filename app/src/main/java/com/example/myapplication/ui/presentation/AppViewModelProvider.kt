package com.example.myapplication.ui.presentation

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.TodoApplication
import com.example.myapplication.ui.presentation.screens.task.EditTaskViewModel
import com.example.myapplication.ui.presentation.screens.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                this.createSavedStateHandle(),
                todoApplication().container.taskRepository
            )
        }
        initializer {
            EditTaskViewModel(
                todoApplication().container.taskRepository
            )
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication = (this[AndroidViewModelFactory
    .APPLICATION_KEY] as TodoApplication)