package com.example.tugas_13

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TambahTemanActivity : AppCompatActivity() {

    private lateinit var inputNama: EditText
    private lateinit var inputSekolah: EditText
    private lateinit var inputHobi: EditText
    private lateinit var btnSimpan: MaterialButton
    private lateinit var dbHelper: TemanDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_teman)

        inputNama = findViewById(R.id.inputNama)
        inputSekolah = findViewById(R.id.inputSekolah)
        inputHobi = findViewById(R.id.inputHobi)
        btnSimpan = findViewById(R.id.btnSimpan)
        dbHelper = TemanDatabaseHelper(this)

        btnSimpan.setOnClickListener {
            val nama = inputNama.text.toString()
            val sekolah = inputSekolah.text.toString()
            val hobi = inputHobi.text.toString()

            if (nama.isNotEmpty() && sekolah.isNotEmpty() && hobi.isNotEmpty()) {
                val teman = teman(nama = nama, sekolah = sekolah, hobi = hobi)
                dbHelper.tambahTeman(teman)
                Toast.makeText(this, "Teman berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                finish() // Kembali ke MainActivity
            } else {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}