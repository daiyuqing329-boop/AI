package com.example.gongdijigong.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.math.BigDecimal

@Dao
interface BorrowRecordDao {
    @Query("SELECT * FROM borrow_record WHERE project_id = :projectId ORDER BY date DESC, id DESC")
    suspend fun getAllByProject(projectId: Long): List<BorrowRecord>

    @Query("SELECT * FROM borrow_record ORDER BY date DESC, id DESC")
    suspend fun getAll(): List<BorrowRecord>

    @Insert
    suspend fun insert(record: BorrowRecord): Long

    @Delete
    suspend fun delete(record: BorrowRecord)

    /** 借支合计(借支为正) */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM borrow_record WHERE project_id = :projectId AND kind = '借支'")
    suspend fun sumBorrow(projectId: Long): BigDecimal

    /** 已结算合计(结算为负) */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM borrow_record WHERE project_id = :projectId AND kind = '结算'")
    suspend fun sumSettled(projectId: Long): BigDecimal
}
