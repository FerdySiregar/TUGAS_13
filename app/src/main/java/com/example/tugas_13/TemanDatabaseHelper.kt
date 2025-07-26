package com.example.tugas_13

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TemanDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "teman.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TEMAN = "teman"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAMA = "nama"
        private const val COLUMN_SEKOLAH = "sekolah"
        private const val COLUMN_HOBI = "hobi"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_TEMAN (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAMA TEXT,
                $COLUMN_SEKOLAH TEXT,
                $COLUMN_HOBI TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TEMAN")
        onCreate(db)
    }

    fun tambahTeman(teman: teman) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAMA, teman.nama)
            put(COLUMN_SEKOLAH, teman.sekolah)
            put(COLUMN_HOBI, teman.hobi)
        }
        db.insert(TABLE_TEMAN, null, values)
        db.close()
    }

    fun getSemuaTeman(): List<teman> {
        val temanList = mutableListOf<teman>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TEMAN", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val nama = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA))
                val sekolah = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SEKOLAH))
                val hobi = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOBI))
                temanList.add(teman(id, nama, sekolah, hobi))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return temanList
    }
}