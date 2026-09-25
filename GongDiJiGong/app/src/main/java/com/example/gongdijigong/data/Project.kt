package com.example.gongdijigong.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** 工地(项目) */
@Entity(tableName = "project")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "boss") val boss: String = "",   // 老板/班组
    @ColumnInfo(name = "note") val note: String = ""
)
