package com.example.gongdijigong.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.math.BigDecimal

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

    /** 更新工地信息与记工模板参数 */
    @Query("UPDATE project SET name = :name, boss = :boss, work_type = :workType, unit_price = :unitPrice, overtime_price = :overtimePrice, note = :note WHERE id = :id")
    suspend fun update(
        id: Long,
        name: String,
        boss: String,
        workType: WorkType,
        unitPrice: BigDecimal,
        overtimePrice: BigDecimal,
        note: String
    )
}
