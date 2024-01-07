package com.example.myapplication.ui.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Task
import com.example.myapplication.data.repositories.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    savedStateHandle: SavedStateHandle,
    private val taskRepository: TaskRepository
) : ViewModel() {


    val homeUiState: StateFlow<HomeUiState> = taskRepository.getAllTasks().map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun filterTask() {
        homeUiState.value.taskList.filter {
            val text =  homeUiState.value.searchText.value
            it.title.contains(text) or it.description.contains(text)
        }.also {
            if (it.isNotEmpty()) homeUiState.value.filteredTasklist = it
        }
    }

    fun restoreInitialList() = homeUiState.value.restoreInitialList()



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class HomeUiState(
    val taskList: List<Task> = listOf(),
    val searchText: MutableState<String> = mutableStateOf(""),
    var filteredTasklist: List<Task> = taskList,
)

private fun HomeUiState.restoreInitialList(){
    filteredTasklist = taskList
}