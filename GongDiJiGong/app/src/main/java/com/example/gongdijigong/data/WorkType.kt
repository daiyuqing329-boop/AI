package com.example.gongdijigong.data

/** 记工方式：点工 / 包工 / 计时(零工) */
enum class WorkType(val label: String) {
    POINT("点工"),
    PACKAGE("包工"),
    TIME("计时")
}
