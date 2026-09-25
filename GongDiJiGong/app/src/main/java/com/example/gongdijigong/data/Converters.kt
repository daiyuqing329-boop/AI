package com.example.gongdijigong.data

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.LocalDate

/**
 * Room 类型转换器。
 * 金额一律用 BigDecimal（字符串存储）并在计算时采用精确运算，避免 float/double 浮点误差 —— 这是“不能算错”的关键。
 */
class Converters {

    @TypeConverter
    fun bigDecimalToString(value: BigDecimal?): String? = value?.toPlainString()

    @TypeConverter
    fun stringToBigDecimal(value: String?): BigDecimal? =
        value?.let { runCatching { BigDecimal(it) }.getOrNull() }

    @TypeConverter
    fun workTypeToInt(value: WorkType?): Int? = value?.ordinal

    @TypeConverter
    fun intToWorkType(value: Int?): WorkType? =
        value?.let { WorkType.values().getOrNull(it) }

    @TypeConverter
    fun localDateToLong(value: LocalDate?): Long? = value?.toEpochDay()

    @TypeConverter
    fun longToLocalDate(value: Long?): LocalDate? = value?.let { LocalDate.ofEpochDay(it) }
}
