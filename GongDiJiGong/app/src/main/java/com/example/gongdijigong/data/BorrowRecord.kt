package com.example.gongdijigong.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/** 借支 / 结算记录 */
@Entity(tableName = "borrow_record")
data class BorrowRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "project_id") val projectId: Long,
    @ColumnInfo(name = "date") val date: Long,            // LocalDate epochDay
    @ColumnInfo(name = "amount") val amount: BigDecimal,  // 金额(借支为正，还款/结算为负)
    @ColumnInfo(name = "kind") val kind: String,          // 借支 / 结算
    @ColumnInfo(name = "note") val note: String = ""
)
