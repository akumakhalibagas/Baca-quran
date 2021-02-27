package com.makhalibagas.bacaquran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.wirid.Wirid;

import java.util.List;

public class WiridAdapter extends RecyclerView.Adapter<WiridAdapter.WiridViewHolder> {

    private Context context;
    private List<Wirid> wiridList;

    public WiridAdapter(Context context, List<Wirid> wiridList) {
        this.context = context;
        this.wiridList = wiridList;
    }

    @NonNull
    @Override
    public WiridAdapter.WiridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_wirid, parent, false);
        return new WiridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WiridAdapter.WiridViewHolder holder, int position) {

        Wirid wirid = wiridList.get(position);
        holder.arabic.setText(wirid.getArabic());
        holder.time.setText(String.valueOf(wirid.getTimes() + " X"));
        holder.tnc.setText(wirid.getTnc());
    }

    @Override
    public int getItemCount() {
        return wiridList.size();
    }

    static class WiridViewHolder extends RecyclerView.ViewHolder {
        TextView time, tnc, arabic;
         WiridViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_time);
            arabic = itemView.findViewById(R.id.tv_arabic);
            tnc = itemView.findViewById(R.id.tv_tnc);
        }
    }
}
