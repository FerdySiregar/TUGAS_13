package com.example.tugas_13

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TemanAdapter(private val temanList: List<teman>) :
    RecyclerView.Adapter<TemanAdapter.TemanViewHolder>() {

    inner class TemanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNama: TextView = itemView.findViewById(R.id.txtNama)
        val txtSekolah: TextView = itemView.findViewById(R.id.txtSekolah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teman, parent, false)
        return TemanViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemanViewHolder, position: Int) {
        val teman = temanList[position]
        holder.txtNama.text = teman.nama
        holder.txtSekolah.text = teman.sekolah
    }

    override fun getItemCount(): Int = temanList.size
}