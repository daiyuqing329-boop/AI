package com.example.gongdijigong.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.data.WorkRecord
import com.example.gongdijigong.databinding.ActivityCalendarBinding
import com.example.gongdijigong.databinding.ItemCalendarDayBinding
import com.example.gongdijigong.util.TimeSync
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarBinding
    private val db by lazy { AppDatabase.get(this) }
    private var month: YearMonth = YearMonth.now()
    private var today: LocalDate = LocalDate.now()

    private val cells = mutableListOf<LocalDate?>() // null = 空白格
    private val dayAmount = mutableMapOf<Long, BigDecimal>()
    private val dayHours = mutableMapOf<Long, BigDecimal>()
    private val recordsByDay = mutableMapOf<Long, List<WorkRecord>>()
    private val dayInfo = mutableMapOf<Int, List<WorkRecord>>() // grid index -> records

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.grid.adapter = GridAdapter()
        binding.grid.setOnItemClickListener { _, _, position, _ -> showDayDetail(position) }

        binding.btnPrev.setOnClickListener { month = month.minusMonths(1); load() }
        binding.btnNext.setOnClickListener { month = month.plusMonths(1); load() }

        // 联网校准“今天”并定位到最新月份，刷新日历
        lifecycleScope.launch {
            today = TimeSync.today()
            month = YearMonth.from(today)
            load()
        }
    }

    private fun load() {
        binding.tvMonth.text = "${month.year}年${month.monthValue}月"
        val start = month.atDay(1)
        val end = month.atEndOfMonth()
        lifecycleScope.launch {
            val records = db.workRecordDao().getByRange(start.toEpochDay(), end.toEpochDay())
            dayAmount.clear(); dayHours.clear(); recordsByDay.clear()
            var totalAmount = BigDecimal.ZERO
            var totalHours = BigDecimal.ZERO
            records.forEach { r ->
                val amt = r.amount
                dayAmount[r.date] = MoneyCalc.add(dayAmount[r.date] ?: BigDecimal.ZERO, amt)
                dayHours[r.date] = MoneyCalc.add(dayHours[r.date] ?: BigDecimal.ZERO, r.hours)
                recordsByDay[r.date] = (recordsByDay[r.date] ?: emptyList()) + r
                totalAmount = MoneyCalc.add(totalAmount, amt)
                totalHours = MoneyCalc.add(totalHours, r.hours)
            }
            binding.tvMonthTotal.text = "${MoneyCalc.fmt(totalAmount)} 元"
            binding.tvMonthHours.text = MoneyCalc.fmt(totalHours)

            // 构建网格：前面按周一开头补齐空白
            cells.clear(); dayInfo.clear()
            val firstDow = start.dayOfWeek
            val leading = when (firstDow) {
                DayOfWeek.MONDAY -> 0
                DayOfWeek.TUESDAY -> 1
                DayOfWeek.WEDNESDAY -> 2
                DayOfWeek.THURSDAY -> 3
                DayOfWeek.FRIDAY -> 4
                DayOfWeek.SATURDAY -> 5
                DayOfWeek.SUNDAY -> 6
            }
            repeat(leading) { cells.add(null) }
            var idx = leading
            for (d in 1..month.lengthOfMonth()) {
                val date = month.atDay(d)
                cells.add(date)
                dayInfo[idx] = recordsByDay[date.toEpochDay()] ?: emptyList()
                idx++
            }
            (binding.grid.adapter as GridAdapter).notifyDataSetChanged()
            binding.dayDetail.removeAllViews()
        }
    }

    private fun showDayDetail(position: Int) {
        binding.dayDetail.removeAllViews()
        val date = cells.getOrNull(position) ?: return
        if (date == null) return
        val list = dayInfo[position] ?: emptyList()
        val header = TextView(this).apply {
            text = "${date.monthValue}月${date.dayOfMonth}日 记工明细（共${list.size}条）"
            setTextColor(Color.parseColor("#333333"))
            setTextSize(16f)
            setPadding(0, 8, 0, 8)
        }
        binding.dayDetail.addView(header)
        if (list.isEmpty()) {
            binding.dayDetail.addView(TextView(this).apply { text = "当天无记工记录" })
            return
        }
        list.forEach { r ->
            binding.dayDetail.addView(TextView(this).apply {
                text = "${r.workType.label}  ${MoneyCalc.fmt(r.hours)}×${MoneyCalc.fmt(r.unitPrice)} = ${MoneyCalc.fmt(r.amount)}元" +
                    (if (r.overtimeHours.signum() > 0) "（加班${MoneyCalc.fmt(r.overtimeHours)}）" else "")
                setPadding(0, 4, 0, 4)
            })
        }
    }

    inner class GridAdapter : BaseAdapter() {
        override fun getCount(): Int = cells.size
        override fun getItem(pos: Int): LocalDate? = cells[pos]
        override fun getItemId(pos: Int): Long = pos.toLong()

        override fun getView(pos: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
            val b = if (convertView == null) ItemCalendarDayBinding.inflate(layoutInflater, parent, false)
            else ItemCalendarDayBinding.bind(convertView)
            val date = cells[pos]
            if (date == null) {
                b.tvDay.text = ""
                b.tvAmount.text = ""
            } else {
                b.tvDay.text = "${date.dayOfMonth}"
                val amt = dayAmount[date.toEpochDay()]
                b.tvAmount.text = if (amt != null && amt.signum() > 0) MoneyCalc.fmt(amt) else ""
                if (date == today) b.tvDay.setTextColor(Color.parseColor("#FF9800"))
                else b.tvDay.setTextColor(Color.parseColor("#222222"))
            }
            return b.root
        }
    }
}
