package com.makhalibagas.bacaquran.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.ui.activity.AyatActivity;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<Surat> suratList;
    private Context context;

    public ViewPagerAdapter(List<Surat> suratList, Context context) {
        this.suratList = suratList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return suratList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rekomend, container, false);
        TextView arab = view.findViewById(R.id.tv_arab);
        TextView indo = view.findViewById(R.id.tv_indo);
        TextView arti = view.findViewById(R.id.tv_arti);
        TextView jumlah = view.findViewById(R.id.tv_jumlhayat);

        arab.setText(suratList.get(position).getArabic());
        indo.setText(suratList.get(position).getLatin());
        arti.setText("( " + suratList.get(position).getTranslation() + " : ");
        jumlah.setText(String.valueOf(suratList.get(position).getAyahCount()) + " Ayat" + " )");
        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
