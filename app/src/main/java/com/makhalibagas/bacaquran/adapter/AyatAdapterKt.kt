package com.makhalibagas.bacaquran.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.makhalibagas.bacaquran.R
import com.makhalibagas.bacaquran.model.quran.Ayat

/**
 * Created by Bagas Makhali on 6/29/2020.
 */
class AyatAdapterKt(private var viewPager2: ViewPager2, private val ayatList: List<Ayat>) : RecyclerView.Adapter<AyatAdapterKt.ViewHolder>(){
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        private var nomor = view.findViewById<TextView>(R.id.tv_nomor)
        private var name = view.findViewById<TextView>(R.id.tv_arab)
        private var terjemah = view.findViewById<TextView>(R.id.tv_terjemahan)
        var btNext = view.findViewById<Button>(R.id.btNext)
        var btPrevious = view.findViewById<Button>(R.id.btPrevious)

        fun bind(ayat: Ayat){
            nomor.text = ayat.nomor
            name.text = ayat.ar
            terjemah.text = ayat.id

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ayatdetail, parent, false))
    }

    override fun getItemCount(): Int {
        return ayatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ayatList[position])
        holder.btNext.setOnClickListener {
            viewPager2.currentItem = viewPager2.currentItem + 1
        }
        holder.btPrevious.setOnClickListener {
            viewPager2.currentItem = viewPager2.currentItem - 1
        }
    }
}