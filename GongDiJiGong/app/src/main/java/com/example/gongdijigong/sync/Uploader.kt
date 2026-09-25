package com.example.gongdijigong.sync

import android.content.Context
import com.example.gongdijigong.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 数据上传占位接口。
 *
 * 当前版本：数据完全保存在本地 SQLite（离线可用），本类仅为“之后把数据上传到服务器”预留。
 * 实现方式建议：定时/联网时把本地 records 通过 JSON POST 到自建服务器，成功后记录 lastSyncTime 增量同步。
 * 后续接入时在此填充真实网络实现（例如 Retrofit + 服务器地址配置），无需改动本地数据库与 UI。
 */
object Uploader {

    fun startSync(context: Context, scope: CoroutineScope) {
        // TODO: 之后在此实现上传逻辑，当前保持离线纯本地。
        scope.launch(Dispatchers.IO) {
            // 示例：读取本地数据（后续在此打包上传）
            val db = AppDatabase.get(context)
            db.workRecordDao().getAll()
        }
    }
}
