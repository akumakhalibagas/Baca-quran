package com.makhalibagas.bacaquran.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.bacaquran.R
import com.makhalibagas.bacaquran.model.pengajian.Pengajian
import com.makhalibagas.bacaquran.ui.activity.VideoActivity

/**
 * Created by Bagas Makhali on 6/28/2020.
 */
class PengajianAdapter (var pengajianList: List<Pengajian> = listOf()) : RecyclerView.Adapter<PengajianAdapter.ViewHolder>(){




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgView = view.findViewById<ImageView>(R.id.imgView);
        private val title = view.findViewById<TextView>(R.id.tvTitlePengajian)
        private val btPlay = view.findViewById<Button>(R.id.btPlayVideo)

        fun bind(mpengajian: Pengajian) {
            Glide.with(itemView.context)
                    .load(mpengajian.image)
                    .into(imgView)
            btPlay.setOnClickListener {
                val intent = Intent(itemView.context, VideoActivity::class.java)
                intent.putExtra("EXTRA", mpengajian)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                itemView.context.startActivity(intent)
            }
            title.text = mpengajian.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pengajian, parent, false))
    }

    override fun getItemCount(): Int {
        return pengajianList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pengajianList[position])
    }
}