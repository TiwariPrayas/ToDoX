package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.data.repositories.TaskRepository
import com.example.myapplication.data.repositories.TaskRepositoryImpl

interface AppContainer {
    val taskRepository: TaskRepository
}

class AppDataContainer(private val context: Context):AppContainer {
    override val taskRepository: TaskRepository by lazy {
        TaskRepositoryImpl(taskDao = AppDatabase.getDatabase(context = context).taskDao())
    }
}