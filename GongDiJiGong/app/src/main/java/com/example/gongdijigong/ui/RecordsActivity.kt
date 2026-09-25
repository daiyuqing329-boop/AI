package com.example.gongdijigong.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gongdijigong.R
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.data.WorkRecord
import com.example.gongdijigong.databinding.ActivityRecordsBinding
import com.example.gongdijigong.databinding.ItemRecordBinding
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordsBinding
    private val db by lazy { AppDatabase.get(this) }
    private var projectId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getLongExtra("projectId", 0L)
        binding.toolbar.title = intent.getStringExtra("projectName") ?: "记工记录"
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = Adapter()
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddRecordActivity::class.java).putExtra("projectId", projectId))
        }
        load()
    }

    override fun onResume() {
        super.onResume()
        if (projectId != 0L) load()
    }

    private fun load() {
        lifecycleScope.launch {
            binding.tvUnsettled.text = MoneyCalc.fmt(db.workRecordDao().sumUnsettled(projectId))
            binding.tvTotal.text = MoneyCalc.fmt(db.workRecordDao().sumAll(projectId))
            (binding.rv.adapter as Adapter).submit(db.workRecordDao().getAllByProject(projectId))
        }
    }

    inner class VH(val b: ItemRecordBinding) : RecyclerView.ViewHolder(b.root)

    inner class Adapter : RecyclerView.Adapter<VH>() {
        private val items = mutableListOf<WorkRecord>()

        fun submit(list: List<WorkRecord>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH =
            VH(ItemRecordBinding.inflate(layoutInflater, parent, false))

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(h: VH, position: Int) {
            val r = items[position]
            h.b.tvDate.text = LocalDate.ofEpochDay(r.date).toString()
            h.b.tvType.text = r.workType.label
            h.b.tvSettled.text = if (r.settled) "已结" else "未结"
            h.b.tvSettled.setBackgroundColor(getColor(if (r.settled) R.color.primary else R.color.accent))
            h.b.tvAmount.text = "${MoneyCalc.fmt(r.hours)} × ${MoneyCalc.fmt(r.unitPrice)} 元 = ${MoneyCalc.fmt(r.amount)} 元"
            h.b.tvOvertime.text = if (r.overtimeHours.signum() > 0) {
                "加班 ${MoneyCalc.fmt(r.overtimeHours)} 小时 × ${MoneyCalc.fmt(r.overtimePrice)} 元/时"
            } else ""
            h.b.tvNote.text = r.note
            h.b.root.setOnClickListener {
                lifecycleScope.launch {
                    db.workRecordDao().setSettled(r.id, !r.settled)
                    load()
                }
            }
            h.b.root.setOnLongClickListener {
                AlertDialog.Builder(this@RecordsActivity)
                    .setTitle("删除记录")
                    .setMessage("删除这条记工记录？")
                    .setPositiveButton("删除") { _, _ ->
                        lifecycleScope.launch { db.workRecordDao().delete(r); load() }
                    }
                    .setNegativeButton("取消", null)
                    .show()
                true
            }
        }
    }
}
