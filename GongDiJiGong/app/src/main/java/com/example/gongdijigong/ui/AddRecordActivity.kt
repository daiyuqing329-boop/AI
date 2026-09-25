package com.example.gongdijigong.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.data.Project
import com.example.gongdijigong.data.WorkRecord
import com.example.gongdijigong.data.WorkType
import com.example.gongdijigong.databinding.ActivityAddRecordBinding
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDate

class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding
    private val db by lazy { AppDatabase.get(this) }
    private val projects = mutableListOf<Project>()
    private var selectedProjectId = 0L
    private var selectedDate: LocalDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val presetId = intent.getLongExtra("projectId", 0L)
        lifecycleScope.launch {
            projects.addAll(db.projectDao().getAll())
            binding.spProject.adapter = ArrayAdapter(
                this@AddRecordActivity,
                android.R.layout.simple_spinner_item,
                projects.map { it.name }
            )
            val idx = projects.indexOfFirst { it.id == presetId }
            if (idx >= 0) binding.spProject.setSelection(idx)
        }

        binding.typePoint.isChecked = true
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
        binding.edOtPrice.addTextChangedListener(watcher)

        binding.btnSave.setOnClickListener { save() }
    }

    private fun updatePreview() {
        val hours = MoneyCalc.parse(binding.edHours.text.toString())
        val price = MoneyCalc.parse(binding.edUnitPrice.text.toString())
        val otH = MoneyCalc.parse(binding.edOtHours.text.toString())
        val otP = MoneyCalc.parse(binding.edOtPrice.text.toString())
        val amount = MoneyCalc.recordAmount(hours, price, otH, otP)
        binding.tvAmountPreview.text = "金额：${MoneyCalc.fmt(amount)} 元"
    }

    private fun selectedType(): WorkType =
        when (binding.rgType.checkedRadioButtonId) {
            binding.typePackage.id -> WorkType.PACKAGE
            binding.typeTime.id -> WorkType.TIME
            else -> WorkType.POINT
        }

    private fun save() {
        if (projects.isEmpty()) {
            Toast.makeText(this, "请先添加工地", Toast.LENGTH_SHORT).show()
            return
        }
        selectedProjectId = projects[binding.spProject.selectedItemPosition].id
        val hours = MoneyCalc.parse(binding.edHours.text.toString())
        val price = MoneyCalc.parse(binding.edUnitPrice.text.toString())
        if (hours.signum() <= 0 || price.signum() <= 0) {
            Toast.makeText(this, "请填写工时/工量和工价", Toast.LENGTH_SHORT).show()
            return
        }
        val otH = MoneyCalc.parse(binding.edOtHours.text.toString())
        val otP = MoneyCalc.parse(binding.edOtPrice.text.toString())
        val amount = MoneyCalc.recordAmount(hours, price, otH, otP)

        val record = WorkRecord(
            projectId = selectedProjectId,
            date = selectedDate.toEpochDay(),
            workType = selectedType(),
            hours = hours,
            unitPrice = price,
            amount = amount,
            overtimeHours = otH,
            overtimePrice = otP,
            note = binding.edNote.text.toString().trim()
        )
        lifecycleScope.launch {
            db.workRecordDao().insert(record)
            Toast.makeText(this@AddRecordActivity, "已保存，金额 ${MoneyCalc.fmt(amount)} 元", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
