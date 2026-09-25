package com.example.gongdijigong.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project ORDER BY id DESC")
    suspend fun getAll(): List<Project>

    @Query("SELECT * FROM project WHERE id = :id")
    suspend fun getById(id: Long): Project?

    @Insert
    suspend fun insert(project: Project): Long

    @Delete
    suspend fun delete(project: Project)
}
