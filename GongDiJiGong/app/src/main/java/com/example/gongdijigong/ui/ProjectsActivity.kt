package com.example.gongdijigong.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.MoneyCalc
import com.example.gongdijigong.data.Project
import com.example.gongdijigong.data.WorkType
import com.example.gongdijigong.databinding.ActivityProjectsBinding
import com.example.gongdijigong.databinding.ItemProjectBinding
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ProjectsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectsBinding
    private val db by lazy { AppDatabase.get(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = Adapter()
        binding.fab.setOnClickListener { showProjectDialog(null) }
        load()
    }

    private fun load() {
        lifecycleScope.launch {
            (binding.rv.adapter as Adapter).submit(db.projectDao().getAll())
        }
    }

    /** 新建或编辑工地（含记工模板参数）。 */
    private fun showProjectDialog(existing: Project?) {
        val name = android.widget.EditText(this).apply { hint = "工地名称"; setText(existing?.name ?: "") }
        val boss = android.widget.EditText(this).apply { hint = "老板/班组（可选）"; setText(existing?.boss ?: "") }
        val price = android.widget.EditText(this).apply {
            hint = "工价（元，如 300.00）"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
            if (existing != null && existing.unitPrice.signum() > 0) setText(MoneyCalc.fmt(existing.unitPrice))
        }
        val otPrice = BigDecimal.ZERO
        val hpw = android.widget.EditText(this).apply {
            hint = "每工小时数（如 8，即几小时算一个工）"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
            setText(MoneyCalc.fmt(existing?.hourPerWork ?: BigDecimal("8")))
        }
        val rbPoint = android.widget.RadioButton(this).apply { text = "点工"; id = android.view.View.generateViewId() }
        val rbPackage = android.widget.RadioButton(this).apply { text = "包工"; id = android.view.View.generateViewId() }
        val rbTime = android.widget.RadioButton(this).apply { text = "计时"; id = android.view.View.generateViewId() }
        val typeGroup = android.widget.RadioGroup(this).apply {
            orientation = android.widget.RadioGroup.HORIZONTAL
            addView(rbPoint); addView(rbPackage); addView(rbTime)
        }
        when (existing?.workType) {
            WorkType.PACKAGE -> rbPackage.isChecked = true
            WorkType.TIME -> rbTime.isChecked = true
            else -> rbPoint.isChecked = true
        }
        val tip = android.widget.TextView(this).apply {
            text = "工地记工模板：设定每工小时数与工价。点工/计时录小时数自动折算成工；加班满每工小时数算一个工的钱。"
            setTextSize(12f)
            setTextColor(0xFF888888.toInt())
        }
        val wrap = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            addView(tip)
            addView(name)
            addView(boss)
            addView(typeGroup)
            addView(price)
            addView(hpw)
        }
        AlertDialog.Builder(this)
            .setTitle(if (existing == null) "添加工地（设置记工模板）" else "编辑工地 / 模板")
            .setView(wrap)
            .setPositiveButton("保存") { _, _ ->
                val nm = name.text.toString().trim()
                if (nm.isEmpty()) return@setPositiveButton
                val wt = when (typeGroup.checkedRadioButtonId) {
                    rbPackage.id -> WorkType.PACKAGE
                    rbTime.id -> WorkType.TIME
                    else -> WorkType.POINT
                }
                val up = MoneyCalc.parse(price.text.toString())
                val h = MoneyCalc.parse(hpw.text.toString()).let { if (it.signum() <= 0) BigDecimal("8") else it }
                lifecycleScope.launch {
                    if (existing == null) {
                        db.projectDao().insert(
                            Project(name = nm, boss = boss.text.toString().trim(), workType = wt, unitPrice = up, overtimePrice = otPrice, hourPerWork = h)
                        )
                    } else {
                        db.projectDao().update(existing.id, nm, boss.text.toString().trim(), wt, up, otPrice, h, existing.note)
                    }
                    load()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    inner class VH(val b: ItemProjectBinding) : RecyclerView.ViewHolder(b.root)

    inner class Adapter : RecyclerView.Adapter<VH>() {
        private val items = mutableListOf<Project>()

        fun submit(list: List<Project>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH =
            VH(ItemProjectBinding.inflate(layoutInflater, parent, false))

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(h: VH, position: Int) {
            val p = items[position]
            h.b.tvName.text = p.name
            h.b.tvBoss.text = p.boss.ifEmpty { "（无老板）" }
            val up = if (p.unitPrice.signum() > 0) "${MoneyCalc.fmt(p.unitPrice)}元/工" else "未设工价"
            h.b.tvSummary.text = "每工 ${MoneyCalc.fmt(p.hourPerWork)} 小时 · $up · 点击记工"
            h.b.root.setOnClickListener {
                startActivity(
                    Intent(this@ProjectsActivity, RecordsActivity::class.java)
                        .putExtra("projectId", p.id)
                        .putExtra("projectName", p.name)
                )
            }
            h.b.root.setOnLongClickListener {
                val actions = arrayOf("编辑工地/模板", "删除工地")
                AlertDialog.Builder(this@ProjectsActivity)
                    .setTitle(p.name)
                    .setItems(actions) { _, which ->
                        when (which) {
                            0 -> showProjectDialog(p)
                            1 -> confirmDelete(p)
                        }
                    }
                    .show()
                true
            }
        }
    }

    private fun confirmDelete(p: Project) {
        AlertDialog.Builder(this)
            .setTitle("删除工地")
            .setMessage("将删除“${p.name}”，其下的记工、借支记录一并删除。")
            .setPositiveButton("删除") { _, _ ->
                lifecycleScope.launch {
                    db.workRecordDao().getAllByProject(p.id).forEach { db.workRecordDao().delete(it) }
                    db.borrowRecordDao().getAllByProject(p.id).forEach { db.borrowRecordDao().delete(it) }
                    db.projectDao().delete(p)
                    load()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }
}
