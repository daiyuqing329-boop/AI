package com.example.gongdijigong.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate

/**
 * 联网校准时间：从公开时间接口获取“今天”的准确日期，避免依赖手机本地时间（可能不准）。
 * 若联网失败则回退到手机本地时间，保证离线可用。
 */
object TimeSync {

    private val apis = listOf(
        "https://worldtimeapi.org/api/timezone/Asia/Shanghai",
        "https://timeapi.io/api/Time/current/zone?timeZone=Asia/Shanghai"
    )

    /** 返回校准后的“今天”；失败回退本地日期。 */
    suspend fun today(): LocalDate = withContext(Dispatchers.IO) {
        for (api in apis) {
            try {
                val conn = URL(api).openConnection() as HttpURLConnection
                conn.connectTimeout = 4000
                conn.readTimeout = 4000
                conn.setRequestProperty("User-Agent", "Mozilla/5.0")
                val body = conn.inputStream.bufferedReader().use { it.readText() }
                parseDate(body)?.let { return@withContext it }
            } catch (e: Exception) {
                // 继续尝试下一个接口
            }
        }
        LocalDate.now()
    }

    private fun parseDate(body: String): LocalDate? {
        val m = Regex("(\\d{4}-\\d{2}-\\d{2})").find(body) ?: return null
        return runCatching { LocalDate.parse(m.groupValues[1]) }.getOrNull()
    }
}
