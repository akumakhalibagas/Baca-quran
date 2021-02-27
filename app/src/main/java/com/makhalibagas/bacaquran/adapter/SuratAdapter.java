package com.makhalibagas.bacaquran.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.database.SuratHelper;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.preference.Preferences;
import com.makhalibagas.bacaquran.ui.activity.AyatActivity;

import java.util.ArrayList;
import java.util.List;

public class SuratAdapter extends RecyclerView.Adapter<SuratAdapter.SuratViewHolder> implements Filterable {
    private Context context;
    private List<Surat> suratList;
    private List<Surat> suratListFilter;


    public SuratAdapter(Context context, List<Surat> suratList) {
        this.context = context;
        this.suratList = suratList;
        this.suratListFilter = suratList;
    }

    @NonNull
    @Override
    public SuratAdapter.SuratViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surat, parent, false);

        return new SuratViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SuratAdapter.SuratViewHolder holder, int position) {

        final Surat surat = suratListFilter.get(position);
        final SuratHelper suratHelper = SuratHelper.getInstance(context);
        suratHelper.open();
        if (suratHelper.checkSurat(String.valueOf(surat.getIndex()))){
            holder.star.setVisibility(View.GONE);
            holder.del.setVisibility(View.VISIBLE);
        }
        holder.nomor.setText(String.valueOf(surat.getIndex()));
        holder.arab.setText(surat.getArabic());
        holder.indo.setText(surat.getLatin());
        holder.arti.setText("( " + surat.getTranslation() + " : ");
        holder.jumlahayat.setText(String.valueOf(surat.getAyahCount()) + " Ayat" + " )");
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suratHelper.addFavorite(surat);
                Dialog dialog = new Dialog(holder.itemView.getContext());
                dialog.setContentView(R.layout.alert_favorite);
                holder.del.setVisibility(View.VISIBLE);
                holder.star.setVisibility(View.INVISIBLE);
                dialog.show();
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suratHelper.deleteFavorite(String.valueOf(surat.getIndex()));
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.alert_delete);
                holder.del.setVisibility(View.INVISIBLE);
                holder.star.setVisibility(View.VISIBLE);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return suratListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                    suratListFilter = suratList;
                }else {
                    List<Surat> filterList = new ArrayList<>();
                    for (Surat surat : suratList){
                        if (surat.getLatin().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(surat.getIndex()).contains(charString.toLowerCase())){
                            filterList.add(surat);
                        }
                    }

                    suratListFilter = filterList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = suratListFilter;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suratListFilter = (List<Surat>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SuratViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomor, arab, indo, jumlahayat, arti;
        Button star, del;
        SuratViewHolder(@NonNull View itemView) {
            super(itemView);
            nomor = itemView.findViewById(R.id.tv_nomor);
            arab = itemView.findViewById(R.id.tv_arab);
            indo = itemView.findViewById(R.id.tv_indo);
            arti = itemView.findViewById(R.id.tv_arti);
            jumlahayat = itemView.findViewById(R.id.tv_jumlhayat);
            star = itemView.findViewById(R.id.bt_star);
            del = itemView.findViewById(R.id.bt_del);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Surat surat = suratListFilter.get(getAdapterPosition());
            Preferences preferences = new Preferences(context);
            preferences.setSurat(surat);
            Intent intent = new Intent(context, AyatActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
}
