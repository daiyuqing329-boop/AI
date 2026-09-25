package com.example.gongdijigong.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.databinding.ActivityExportBinding
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExportBinding
    private val db by lazy { AppDatabase.get(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnExport.setOnClickListener {
            lifecycleScope.launch {
                val text = buildExportText()
                binding.tvPreview.text = text
                val send = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(android.content.Intent.EXTRA_SUBJECT, "工地记工数据导出")
                    putExtra(android.content.Intent.EXTRA_TEXT, text)
                }
                startActivity(android.content.Intent.createChooser(send, "分享记工数据"))
            }
        }
    }

    private suspend fun buildExportText(): String {
        val sb = StringBuilder()
        sb.append("===== 工地记工数据导出 =====\n导出时间：").append(LocalDate.now()).append("\n\n")
        val projects = db.projectDao().getAll()
        projects.forEach { p ->
            sb.append("【工地】").append(p.name).append("\n")
            if (p.boss.isNotEmpty()) sb.append("老板/班组：").append(p.boss).append("\n")
            val records = db.workRecordDao().getAllByProject(p.id)
            sb.append("-- 记工记录 --\n")
            records.forEach { r ->
                val date = LocalDate.ofEpochDay(r.date)
                sb.append(date)
                    .append("  ").append(r.workType.label)
                    .append("  工时/量=").append(MoneyCalc.fmt(r.hours))
                    .append("  工价=").append(MoneyCalc.fmt(r.unitPrice))
                if (r.overtimeHours.signum() > 0) {
                    sb.append("  加班=").append(MoneyCalc.fmt(r.overtimeHours)).append("×").append(MoneyCalc.fmt(r.overtimePrice))
                }
                sb.append("  金额=").append(MoneyCalc.fmt(r.amount)).append("元")
                    .append(if (r.settled) "  [已结]" else "  [未结]")
                    .append("\n")
            }
            val borrows = db.borrowRecordDao().getAllByProject(p.id)
            sb.append("-- 借支/结算 --\n")
            borrows.forEach { b ->
                sb.append(LocalDate.ofEpochDay(b.date)).append("  ")
                    .append(b.kind).append("  ")
                    .append(MoneyCalc.fmt(b.amount.abs())).append("元")
                if (b.note.isNotEmpty()) sb.append("  (").append(b.note).append(")")
                sb.append("\n")
            }
            val total = db.workRecordDao().sumAll(p.id)
            val settled = db.borrowRecordDao().sumSettled(p.id).abs()
            val borrow = db.borrowRecordDao().sumBorrow(p.id)
            val due = total.subtract(settled).subtract(borrow)
            sb.append("-- 汇总 --\n")
                .append("记工总额=").append(MoneyCalc.fmt(total)).append("元\n")
                .append("已结算=").append(MoneyCalc.fmt(settled)).append("元\n")
                .append("已借支=").append(MoneyCalc.fmt(borrow)).append("元\n")
                .append("待结算=").append(MoneyCalc.fmt(due)).append("元\n\n")
        }
        return sb.toString()
    }
}
