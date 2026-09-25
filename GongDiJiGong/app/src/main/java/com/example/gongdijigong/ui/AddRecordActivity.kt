package com.example.gongdijigong.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.data.Project
import com.example.gongdijigong.data.WorkRecord
import com.example.gongdijigong.databinding.ActivityAddRecordBinding
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate

class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding
    private val db by lazy { AppDatabase.get(this) }
    private val projects = mutableListOf<Project>()
    private var selectedProjectId = 0L
    private var hourPerWork = BigDecimal("8")
    private var selectedDate: LocalDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val presetId = intent.getLongExtra("projectId", 0L)
        val lastProjectId = prefs().getLong("last_project_id", 0L)

        binding.spProject.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, pos: Int, id: Long) {
                if (pos in projects.indices) applyTemplate(projects[pos])
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        lifecycleScope.launch {
            projects.addAll(db.projectDao().getAll())
            binding.spProject.adapter = ArrayAdapter(
                this@AddRecordActivity,
                android.R.layout.simple_spinner_item,
                projects.map { it.name }
            )
            var sel = projects.indexOfFirst { it.id == presetId }
            if (sel < 0) sel = projects.indexOfFirst { it.id == lastProjectId }
            if (sel < 0 && projects.isNotEmpty()) sel = 0
            if (sel >= 0) binding.spProject.setSelection(sel)
        }

        binding.edDate.setText(selectedDate.toString())
        binding.edDate.setOnClickListener {
            val dp = android.app.DatePickerDialog(
                this,
                { _, y, m, d -> selectedDate = LocalDate.of(y, m + 1, d); binding.edDate.setText(selectedDate.toString()) },
                selectedDate.year, selectedDate.monthValue - 1, selectedDate.dayOfMonth
            )
            dp.datePicker.maxDate = System.currentTimeMillis()
            dp.show()
        }

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) = updatePreview()
            override fun onTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
        }
        binding.edHours.addTextChangedListener(watcher)
        binding.edUnitPrice.addTextChangedListener(watcher)
        binding.edOtHours.addTextChangedListener(watcher)

        binding.btnSave.setOnClickListener { save() }
    }

    private fun prefs(): SharedPreferences = getSharedPreferences("hongzhizhao", MODE_PRIVATE)

    /** 使用工地模板：每工小时数 + 一个工的价，自动带出。 */
    private fun applyTemplate(p: Project) {
        selectedProjectId = p.id
        hourPerWork = p.hourPerWork
        binding.tvWorkType.text = "本工地：1 工 = ${MoneyCalc.fmt(p.hourPerWork)} 小时 · ${MoneyCalc.fmt(p.unitPrice)} 元/工"
        if (p.unitPrice.signum() > 0) binding.edUnitPrice.setText(MoneyCalc.fmt(p.unitPrice))
        updatePreview()
    }

    /** 小时工价 = 一个工的价 ÷ 每工小时数。 */
    private fun hourlyRate(price: BigDecimal): BigDecimal =
        if (hourPerWork.signum() > 0) price.divide(hourPerWork, 4, java.math.RoundingMode.HALF_UP) else price

    /**
     * 金额 = (正常小时 + 加班小时) × 小时工价。
     * 小时工价 = 工价 ÷ 每工小时数，没有倍率。
     */
    private fun updatePreview() {
        val hours = MoneyCalc.parse(binding.edHours.text.toString())
        val price = MoneyCalc.parse(binding.edUnitPrice.text.toString())
        val otH = MoneyCalc.parse(binding.edOtHours.text.toString())
        val totalH = hours.add(otH)
        val rate = hourlyRate(price)
        val amount = MoneyCalc.mul(totalH, rate)
        val work = MoneyCalc.fmt(MoneyCalc.toWorkCount(totalH, hourPerWork))
        binding.tvAmountPreview.text = "金额：${MoneyCalc.fmt(amount)} 元（${MoneyCalc.fmt(totalH)}小时 = $work 工 · ${MoneyCalc.fmt(rate)}元/小时）"
    }

    private fun save() {
        if (projects.isEmpty()) {
            Toast.makeText(this, "请先添加工地并设置记工模板", Toast.LENGTH_SHORT).show()
            return
        }
        selectedProjectId = projects[binding.spProject.selectedItemPosition].id
        val hours = MoneyCalc.parse(binding.edHours.text.toString())
        val price = MoneyCalc.parse(binding.edUnitPrice.text.toString())
        if (hours.signum() < 0 || price.signum() <= 0) {
            Toast.makeText(this, "请填写小时数和工价", Toast.LENGTH_SHORT).show()
            return
        }
        val otH = MoneyCalc.parse(binding.edOtHours.text.toString())
        val totalH = hours.add(otH)
        val rate = hourlyRate(price)
        val amount = MoneyCalc.mul(totalH, rate)

        val record = WorkRecord(
            projectId = selectedProjectId,
            date = selectedDate.toEpochDay(),
            workType = com.example.gongdijigong.data.WorkType.POINT,
            hours = hours,
            unitPrice = price,
            amount = amount,
            overtimeHours = otH,
            overtimePrice = BigDecimal.ZERO,
            note = binding.edNote.text.toString().trim()
        )
        lifecycleScope.launch {
            db.workRecordDao().insert(record)
            prefs().edit().putLong("last_project_id", selectedProjectId).apply()
            Toast.makeText(this@AddRecordActivity, "已保存，金额 ${MoneyCalc.fmt(amount)} 元", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
