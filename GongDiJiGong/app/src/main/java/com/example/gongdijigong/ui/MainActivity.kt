package com.example.gongdijigong.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRecord.setOnClickListener { startActivity(Intent(this, AddRecordActivity::class.java)) }
        binding.btnProjects.setOnClickListener { startActivity(Intent(this, ProjectsActivity::class.java)) }
        binding.btnBorrow.setOnClickListener {
            val db = AppDatabase.get(this)
            lifecycleScope.launch {
                val first = db.projectDao().getAll().firstOrNull()
                if (first == null) {
                    android.widget.Toast.makeText(this@MainActivity, "请先在“工地”中添加一个工地", android.widget.Toast.LENGTH_SHORT).show()
                } else {
                    startActivity(Intent(this@MainActivity, BorrowActivity::class.java).putExtra("projectId", first.id))
                }
            }
        }
        binding.btnStats.setOnClickListener { startActivity(Intent(this, StatsActivity::class.java)) }
        binding.btnCalendar.setOnClickListener { startActivity(Intent(this, CalendarActivity::class.java)) }
        binding.btnExport.setOnClickListener { startActivity(Intent(this, ExportActivity::class.java)) }
    }

    override fun onResume() {
        super.onResume()
        loadOverview()
    }

    private fun loadOverview() {
        val db = AppDatabase.get(this)
        lifecycleScope.launch {
            val records = db.workRecordDao().getAll()
            val monthStart = LocalDate.now().withDayOfMonth(1)
            var monthAmount = java.math.BigDecimal.ZERO
            var monthHours = java.math.BigDecimal.ZERO
            var unsettled = java.math.BigDecimal.ZERO
            records.forEach { r ->
                val d = LocalDate.ofEpochDay(r.date)
                if (d.month == monthStart.month && d.year == monthStart.year) {
                    monthAmount = MoneyCalc.add(monthAmount, r.amount)
                    monthHours = MoneyCalc.add(monthHours, r.hours)
                }
                if (!r.settled) unsettled = MoneyCalc.add(unsettled, r.amount)
            }
            binding.cardTodayValue.text = "${MoneyCalc.fmt(monthAmount)} 元"
            binding.cardUnsettledValue.text = "${MoneyCalc.fmt(unsettled)} 元"
            binding.cardHoursValue.text = MoneyCalc.fmt(monthHours)
        }
    }
}
