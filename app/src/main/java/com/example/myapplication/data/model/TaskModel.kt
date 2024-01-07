package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int,
    val startDate: String,
    val endDate: String,
    val isComplete: Boolean = false,
)

