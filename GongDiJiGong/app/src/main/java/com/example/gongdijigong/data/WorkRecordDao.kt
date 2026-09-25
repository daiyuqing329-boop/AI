package com.example.gongdijigong.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.math.BigDecimal

@Dao
interface WorkRecordDao {
    @Query("SELECT * FROM work_record WHERE project_id = :projectId ORDER BY date DESC, id DESC")
    suspend fun getAllByProject(projectId: Long): List<WorkRecord>

    @Query("SELECT * FROM work_record ORDER BY date DESC, id DESC")
    suspend fun getAll(): List<WorkRecord>

    @Insert
    suspend fun insert(record: WorkRecord): Long

    @Delete
    suspend fun delete(record: WorkRecord)

    @Query("UPDATE work_record SET settled = :settled WHERE id = :id")
    suspend fun setSettled(id: Long, settled: Boolean)

    /** 某项目未结算的记工总额 */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM work_record WHERE project_id = :projectId AND settled = 0")
    suspend fun sumUnsettled(projectId: Long): BigDecimal

    /** 某项目记工总额 */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM work_record WHERE project_id = :projectId")
    suspend fun sumAll(projectId: Long): BigDecimal

    /** 某时间范围内的记工记录（日期存 epochDay） */
    @Query("SELECT * FROM work_record WHERE date BETWEEN :start AND :end ORDER BY date ASC, id ASC")
    suspend fun getByRange(start: Long, end: Long): List<WorkRecord>
}
