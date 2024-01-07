package com.example.myapplication.data.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.model.Task
import kotlinx.coroutines.flow.Flow

//val title: String,
//val description: String,
//val priority: Int,
//val startDate:String,
//val endDate:String,
//val isComplete:Boolean = false,


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun getTask(id:Int): Flow<Task>

    @Query("SELECT * FROM tasks")
    fun getAllTasks():Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isComplete = 0")
    fun getIncompleteTasks():Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE priority = :priority")
    fun getTaskByPriority(priority:Int):Flow<List<Task>>
}