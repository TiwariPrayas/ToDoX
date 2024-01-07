package com.example.myapplication.data.repositories

import com.example.myapplication.data.interfaces.TaskDao
import com.example.myapplication.data.model.Task
import kotlinx.coroutines.flow.Flow


interface TaskRepository {

    // Add a new task
    suspend fun addTask(task: Task)

    // Update an existing task
    suspend fun updateTask(task: Task)

    // Delete an existing task
    suspend fun deleteTask(task: Task)

    // Get task by id
    fun getTaskById(id: Int): Flow<Task>

    // Get tasks by priority
    fun getTaskByPriority(priority: Int): Flow<List<Task>>

    // Get incomplete tasks as stream
    fun getIncompleteTasks(): Flow<List<Task>>

    // Get all tasks as stream
    fun getAllTasks(): Flow<List<Task>>

}

class TaskRepositoryImpl(private val taskDao: TaskDao) :TaskRepository {
    override suspend fun addTask(task: Task) = taskDao.insert(task)
    override suspend fun updateTask(task: Task) = taskDao.update(task)
    override suspend fun deleteTask(task: Task)= taskDao.delete(task)
    override fun getTaskById(id: Int): Flow<Task> = taskDao.getTask(id)
    override fun getTaskByPriority(priority: Int): Flow<List<Task>> = taskDao.getTaskByPriority(priority)
    override fun getIncompleteTasks(): Flow<List<Task>> = taskDao.getIncompleteTasks()
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
}

