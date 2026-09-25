package com.example.gongdijigong.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gongdijigong.data.AppDatabase
import com.example.gongdijigong.data.Project
import com.example.gongdijigong.databinding.ActivityProjectsBinding
import com.example.gongdijigong.databinding.ItemProjectBinding
import kotlinx.coroutines.launch

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
        binding.fab.setOnClickListener { showAddDialog() }
        load()
    }

    private fun load() {
        lifecycleScope.launch {
            (binding.rv.adapter as Adapter).submit(db.projectDao().getAll())
        }
    }

    private fun showAddDialog() {
        val input = android.widget.EditText(this).apply { hint = "工地名称" }
        val boss = android.widget.EditText(this).apply { hint = "老板/班组（可选）" }
        val wrap = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            addView(input)
            addView(boss)
        }
        AlertDialog.Builder(this)
            .setTitle("添加工地")
            .setView(wrap)
            .setPositiveButton("保存") { _, _ ->
                val name = input.text.toString().trim()
                if (name.isEmpty()) return@setPositiveButton
                lifecycleScope.launch {
                    db.projectDao().insert(Project(name = name, boss = boss.text.toString().trim()))
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
            h.b.tvSummary.text = "点击查看记工记录 · 长按删除"
            h.b.root.setOnClickListener {
                startActivity(
                    Intent(this@ProjectsActivity, RecordsActivity::class.java)
                        .putExtra("projectId", p.id)
                        .putExtra("projectName", p.name)
                )
            }
            h.b.root.setOnLongClickListener {
                AlertDialog.Builder(this@ProjectsActivity)
                    .setTitle("删除工地")
                    .setMessage("将删除“${p.name}”的工地信息，其下的记工、借支记录一并删除。")
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
                true
            }
        }
    }
}
