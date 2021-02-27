package com.makhalibagas.bacaquran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.asmaul.Asmaul;

import java.util.List;

public class AsmaulAdapter extends RecyclerView.Adapter<AsmaulAdapter.AsmaulViewHolder> {

    private Context context;
    private List<Asmaul> dataItems;

    public AsmaulAdapter(Context context, List<Asmaul> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public AsmaulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_asmaul, parent, false);


        return new AsmaulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsmaulViewHolder holder, int position) {
        Asmaul data = dataItems.get(position);
        holder.arab.setText(data.getArabic());
        holder.name.setText(data.getLatin());
        holder.arti.setText(data.getTranslationId());

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    static class AsmaulViewHolder extends RecyclerView.ViewHolder {
        TextView arab, name, arti;
        AsmaulViewHolder(@NonNull View itemView) {
            super(itemView);
            arab = itemView.findViewById(R.id.tv_arab);
            name = itemView.findViewById(R.id.tv_name);
            arti = itemView.findViewById(R.id.tv_arti);
        }
    }
}
