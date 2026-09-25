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
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.YearMonth

data class StatRow(val name: String, val detail: String)

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private val db by lazy { AppDatabase.get(this) }
    private val colors = listOf("#2E7D32", "#FF9800", "#2196F3", "#9C27B0", "#F44336", "#009688", "#795548")

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

            // 饼图：各工地记工占比
            val pieEntries = projects.mapIndexed { i, p ->
                PieEntry(db.workRecordDao().sumAll(p.id).toFloat(), p.name)
            }.filter { it.value > 0f }
            setupPie(pieEntries)

            // 柱状图：近6个月记工趋势
            val all = db.workRecordDao().getAll()
            setupBar(all)
        }
    }

    private fun setupPie(entries: List<PieEntry>) {
        binding.pie.description.isEnabled = false
        binding.pie.setUsePercentValues(false)
        binding.pie.setHoleRadius(40f)
        binding.pie.isRotationEnabled = true
        binding.pie.legend.isEnabled = entries.isNotEmpty()
        binding.pie.setEntryLabelColor(android.graphics.Color.WHITE)
        binding.pie.setEntryLabelTextSize(11f)
        if (entries.isEmpty()) {
            binding.pie.data = null
            binding.pie.centerText = "暂无数据"
            binding.pie.invalidate()
            return
        }
        val set = PieDataSet(entries, "记工金额").apply {
            this.colors = this@StatsActivity.colors.map { android.graphics.Color.parseColor(it) }
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String = if (value >= 10000) "${(value / 10000f).toInt()}万" else value.toInt().toString()
            }
            valueTextSize = 12f
        }
        binding.pie.data = PieData(set)
        binding.pie.invalidate()
    }

    private fun setupBar(allRecords: List<com.example.gongdijigong.data.WorkRecord>) {
        binding.bar.description.isEnabled = false
        binding.bar.legend.isEnabled = false
        val labels = mutableListOf<String>()
        val entries = mutableListOf<BarEntry>()
        val now = YearMonth.now()
        for (m in 5 downTo 0) {
            val ym = now.minusMonths(m.toLong())
            val amount = allRecords.filter {
                val d = LocalDate.ofEpochDay(it.date)
                YearMonth.of(d.year, d.monthValue) == ym
            }.fold(BigDecimal.ZERO) { acc, r -> MoneyCalc.add(acc, r.amount) }
            entries.add(BarEntry(m.toFloat(), amount.toFloat()))
            labels.add("${ym.monthValue}月")
        }
        val set = BarDataSet(entries, "记工金额").apply {
            color = android.graphics.Color.parseColor("#2E7D32")
            valueTextColor = android.graphics.Color.parseColor("#333333")
            valueTextSize = 11f
        }
        binding.bar.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.bar.xAxis.granularity = 1f
        binding.bar.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        binding.bar.axisRight.isEnabled = false
        binding.bar.setFitBars(true)
        binding.bar.data = BarData(set)
        binding.bar.invalidate()
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
