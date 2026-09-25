package com.example.gongdijigong.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.BorrowRecord
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.databinding.ActivityBorrowBinding
import com.example.gongdijigong.databinding.ItemBorrowBinding
import kotlinx.coroutines.launch
import java.time.LocalDate

class BorrowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBorrowBinding
    private val db by lazy { AppDatabase.get(this) }
    private var projectId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getLongExtra("projectId", 0L)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = Adapter()
        binding.fab.setOnClickListener { showAddDialog() }
        load()
    }

    private fun load() {
        lifecycleScope.launch {
            binding.tvBorrow.text = MoneyCalc.fmt(db.borrowRecordDao().sumBorrow(projectId))
            binding.tvSettled.text = MoneyCalc.fmt(db.borrowRecordDao().sumSettled(projectId).abs())
            (binding.rv.adapter as Adapter).submit(db.borrowRecordDao().getAllByProject(projectId))
        }
    }

    private fun showAddDialog() {
        val amt = android.widget.EditText(this).apply { hint = "金额（元）"; inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL }
        val note = android.widget.EditText(this).apply { hint = "备注（可选）" }
        val kinds = arrayOf("借支", "结算")
        val rbBorrow = android.widget.RadioButton(this).apply { text = "借支"; id = android.view.View.generateViewId() }
        val rbSettle = android.widget.RadioButton(this).apply { text = "结算"; id = android.view.View.generateViewId() }
        rbBorrow.isChecked = true
        val rbGroup = android.widget.RadioGroup(this).apply {
            orientation = android.widget.RadioGroup.VERTICAL
            addView(rbBorrow)
            addView(rbSettle)
        }
        val wrap = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            addView(amt)
            addView(note)
            addView(rbGroup)
        }
        AlertDialog.Builder(this)
            .setTitle("新增借支/结算")
            .setView(wrap)
            .setPositiveButton("保存") { _, _ ->
                val amount = MoneyCalc.parse(amt.text.toString())
                if (amount.signum() <= 0) {
                    Toast.makeText(this, "金额需大于 0", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val isBorrow = rbBorrow.isChecked
                val signed = if (isBorrow) amount else amount.negate()
                lifecycleScope.launch {
                    db.borrowRecordDao().insert(
                        BorrowRecord(
                            projectId = projectId,
                            date = LocalDate.now().toEpochDay(),
                            amount = signed,
                            kind = if (isBorrow) "借支" else "结算",
                            note = note.text.toString().trim()
                        )
                    )
                    load()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    inner class VH(val b: ItemBorrowBinding) : RecyclerView.ViewHolder(b.root)

    inner class Adapter : RecyclerView.Adapter<VH>() {
        private val items = mutableListOf<BorrowRecord>()

        fun submit(list: List<BorrowRecord>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH =
            VH(ItemBorrowBinding.inflate(layoutInflater, parent, false))

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(h: VH, position: Int) {
            val r = items[position]
            h.b.tvDate.text = LocalDate.ofEpochDay(r.date).toString()
            h.b.tvKind.text = r.kind
            h.b.tvAmount.text = "${if (r.amount.signum() >= 0) "+" else "-"}${MoneyCalc.fmt(r.amount.abs())} 元"
            h.b.tvNote.text = r.note
            h.b.root.setOnLongClickListener {
                AlertDialog.Builder(this@BorrowActivity)
                    .setTitle("删除")
                    .setMessage("删除这条${r.kind}记录？")
                    .setPositiveButton("删除") { _, _ ->
                        lifecycleScope.launch { db.borrowRecordDao().delete(r); load() }
                    }
                    .setNegativeButton("取消", null)
                    .show()
                true
            }
        }
    }
}
