package com.example.gongdijigong.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/**
 * 工地(项目)，每个工地绑定一个固定的“记工模板”：
 * 记工方式 + 工价 + 加班工价 + 每工小时数。创建工地时设置一次，记工时自动带出。
 * 计时记工时，录入小时数后按“每工小时数”自动折算成工数再算金额。
 */
@Entity(tableName = "project")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "boss") val boss: String = "",       // 老板/班组
    @ColumnInfo(name = "work_type") val workType: WorkType = WorkType.POINT, // 记工方式
    @ColumnInfo(name = "unit_price") val unitPrice: BigDecimal = BigDecimal.ZERO, // 工价
    @ColumnInfo(name = "overtime_price") val overtimePrice: BigDecimal = BigDecimal.ZERO, // 加班工价
    @ColumnInfo(name = "hour_per_work") val hourPerWork: BigDecimal = BigDecimal("8"), // 每工小时数（计时折算用）
    @ColumnInfo(name = "note") val note: String = ""
)
