package com.makhalibagas.bacaquran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.doa.Doa;

import java.util.List;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.DoaViewHolder> implements Filterable {

    private Context context;
    private List<Doa> doaList;

    public DoaAdapter(Context context, List<Doa> doaList) {
        this.context = context;
        this.doaList = doaList;
    }


    @NonNull
    @Override
    public DoaAdapter.DoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doa, parent, false);
        return new DoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoaAdapter.DoaViewHolder holder, final int position) {

        final Doa doa = doaList.get(position);

        boolean expanded = doa.isExpanded();
        holder.viewexpanded.setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.cardTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = doa.isExpanded();
                doa.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });
        holder.title.setText(doa.getTitle());
        holder.arabic.setText(doa.getArabic());
        holder.latin.setText(doa.getLatin());
        holder.translation.setText(doa.getTranslation());

    }

    @Override
    public int getItemCount() {
        return doaList == null ? 0 : doaList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    static class DoaViewHolder extends RecyclerView.ViewHolder {
        TextView title, arabic, latin, translation;
        CardView viewexpanded, cardTitle;
         DoaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            arabic = itemView.findViewById(R.id.tv_arabic);
            latin = itemView.findViewById(R.id.tv_latin);
            translation = itemView.findViewById(R.id.tv_translation);
            viewexpanded = itemView.findViewById(R.id.view_expanded);
            cardTitle = itemView.findViewById(R.id.cardTitle_id);
        }
    }
}
