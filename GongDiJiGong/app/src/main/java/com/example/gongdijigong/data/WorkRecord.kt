package com.example.gongdijigong.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/** 记工记录 */
@Entity(tableName = "work_record")
data class WorkRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "project_id") val projectId: Long,
    @ColumnInfo(name = "date") val date: Long,          // LocalDate epochDay
    @ColumnInfo(name = "work_type") val workType: WorkType,
    @ColumnInfo(name = "hours") val hours: BigDecimal,   // 工时/工量
    @ColumnInfo(name = "unit_price") val unitPrice: BigDecimal, // 工价
    @ColumnInfo(name = "amount") val amount: BigDecimal, // 金额 = 工时×工价
    @ColumnInfo(name = "overtime_hours") val overtimeHours: BigDecimal, // 加班工时
    @ColumnInfo(name = "overtime_price") val overtimePrice: BigDecimal, // 加班工价
    @ColumnInfo(name = "settled") val settled: Boolean = false,
    @ColumnInfo(name = "note") val note: String = ""
)
