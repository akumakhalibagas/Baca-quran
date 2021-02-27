package com.makhalibagas.bacaquran.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makhalibagas.bacaquran.R
import com.makhalibagas.bacaquran.model.quran.SuratMp3
import com.makhalibagas.bacaquran.ui.activity.Mp3DetailActivity

/**
 * Created by Bagas Makhali on 6/29/2020.
 */
class Mp3Adapter(var mp3List : List<SuratMp3>) : RecyclerView.Adapter<Mp3Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvNomor = view.findViewById<TextView>(R.id.tv_nomor)
        fun bind(suratMp3: SuratMp3){
            tvName.text = suratMp3.nama
            tvNomor.text = suratMp3.nomor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ayatmp3, parent, false))
    }

    override fun getItemCount(): Int {
        return mp3List.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mp3List[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Mp3DetailActivity::class.java)
            intent.putExtra(Mp3DetailActivity.EXTRA_DATA, mp3List[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}