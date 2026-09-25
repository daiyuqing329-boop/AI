package com.example.gongdijigong

import android.app.Application
import com.example.gongdijigong.data.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // 应用数据完全保存在本地 SQLite，离线可用。
        // 后续“数据上传服务器”由 sync.Uploader 负责，这里初始化数据库即可。
    }
}
