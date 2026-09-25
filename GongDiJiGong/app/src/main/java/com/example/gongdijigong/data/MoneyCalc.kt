package com.example.gongdijigong.data

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 精确金额计算工具。
 * 所有金额运算均用 [BigDecimal]（而非 float/double），并在运算后统一保留 2 位小数，
 * 从根本上避免“0.1+0.2=0.30000000000000004”这类浮点误差，保证工钱结算不出错。
 */
object MoneyCalc {

    /** 乘法，保留 2 位小数，四舍五入 */
    fun mul(a: BigDecimal, b: BigDecimal): BigDecimal =
        a.multiply(b).setScale(2, RoundingMode.HALF_UP)

    /** 加法，保留 2 位小数 */
    fun add(a: BigDecimal, b: BigDecimal): BigDecimal =
        a.add(b).setScale(2, RoundingMode.HALF_UP)

    /** 记工金额 = 工时×工价 + 加班工时×加班工价 */
    fun recordAmount(
        hours: BigDecimal,
        unitPrice: BigDecimal,
        overtimeHours: BigDecimal,
        overtimePrice: BigDecimal
    ): BigDecimal = add(mul(hours, unitPrice), mul(overtimeHours, overtimePrice))

    /**
     * 把“小时数”折算成“工数”：hours ÷ 每工小时数。
     * 每工小时数 <= 0 时原样返回（视为非计时）。
     */
    fun toWorkCount(hours: BigDecimal, hourPerWork: BigDecimal): BigDecimal =
        if (hourPerWork.signum() > 0) {
            hours.divide(hourPerWork, 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP)
        } else {
            hours
        }

    /** 去掉多余的尾零，以普通字符串展示 */
    fun fmt(bd: BigDecimal): String =
        bd.stripTrailingZeros().toPlainString()

    /** 从字符串安全解析，失败返回 0 */
    fun parse(s: String?): BigDecimal =
        runCatching { BigDecimal(s!!.trim()) }.getOrDefault(BigDecimal.ZERO)
}
