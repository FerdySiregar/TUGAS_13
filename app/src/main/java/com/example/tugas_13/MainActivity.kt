package com.example.tugas_13

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var temanAdapter: TemanAdapter
    private lateinit var databaseHelper: TemanDatabaseHelper
    private lateinit var btnTambah: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnTambah = findViewById(R.id.btnTambah)
        databaseHelper = TemanDatabaseHelper(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnTambah.setOnClickListener {
            val intent = Intent(this, TambahTemanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadTeman()
    }

    private fun loadTeman() {
        val temanList = databaseHelper.getSemuaTeman()
        temanAdapter = TemanAdapter(temanList)
        recyclerView.adapter = temanAdapter
    }
}
