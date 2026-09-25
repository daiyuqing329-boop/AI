package com.example.gongdijigong.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.databinding.ActivityStatsBinding
import com.example.gongdijigong.databinding.ItemStatBinding
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

data class StatRow(val name: String, val detail: String)

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private val db by lazy { AppDatabase.get(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = Adapter()
        load()
    }

    private fun load() {
        lifecycleScope.launch {
            val projects = db.projectDao().getAll()
            val rows = projects.map { p ->
                val total = db.workRecordDao().sumAll(p.id)
                val settled = db.borrowRecordDao().sumSettled(p.id).abs()
                val borrow = db.borrowRecordDao().sumBorrow(p.id)
                val due = total.subtract(settled).subtract(borrow).setScale(2, RoundingMode.HALF_UP)
                StatRow(
                    p.name,
                    "记工总额：${MoneyCalc.fmt(total)} 元\n" +
                        "已结算：${MoneyCalc.fmt(settled)} 元\n" +
                        "已借支：${MoneyCalc.fmt(borrow)} 元\n" +
                        "待结算：${MoneyCalc.fmt(due)} 元"
                )
            }
            (binding.rv.adapter as Adapter).submit(rows)
        }
    }

    inner class VH(val b: ItemStatBinding) : RecyclerView.ViewHolder(b.root)

    inner class Adapter : RecyclerView.Adapter<VH>() {
        private val items = mutableListOf<StatRow>()

        fun submit(list: List<StatRow>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH =
            VH(ItemStatBinding.inflate(layoutInflater, parent, false))

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(h: VH, position: Int) {
            val s = items[position]
            h.b.tvName.text = s.name
            h.b.tvDetail.text = s.detail
        }
    }
}
