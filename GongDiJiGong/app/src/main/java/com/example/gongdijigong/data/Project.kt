package com.example.gongdijigong.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/**
 * 工地(项目)，每个工地绑定一个固定的“记工模板”：
 * 记工方式 + 工价 + 加班工价。创建工地时设置一次，记工时自动带出。
 */
@Entity(tableName = "project")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "boss") val boss: String = "",       // 老板/班组
    @ColumnInfo(name = "work_type") val workType: WorkType = WorkType.POINT, // 记工方式
    @ColumnInfo(name = "unit_price") val unitPrice: BigDecimal = BigDecimal.ZERO, // 工价
    @ColumnInfo(name = "overtime_price") val overtimePrice: BigDecimal = BigDecimal.ZERO, // 加班工价
    @ColumnInfo(name = "note") val note: String = ""
)
