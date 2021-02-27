package com.makhalibagas.bacaquran.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.quran.Ayat;
import com.makhalibagas.bacaquran.preference.Preferences;

import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.AyatViewHolder> {

    private Context context;
    private List<Ayat> ayatList;

    public AyatAdapter(Context context, List<Ayat> ayatList) {
        this.context = context;
        this.ayatList = ayatList;
    }

    @NonNull
    @Override
    public AyatAdapter.AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ayat, parent, false);

        return new AyatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AyatAdapter.AyatViewHolder holder, int position) {

        final Ayat ayat = ayatList.get(position);
        final Preferences preferences = new Preferences(context);

        holder.setlastread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.setAyat(ayat);
                Toast.makeText(context, "Berhasil Ditandai", Toast.LENGTH_SHORT).show();
            }
        });


        holder.nomor.setText(ayat.getNomor());
        holder.arab.setText(ayat.getAr());
        holder.latin.setText(Html.fromHtml(ayat.getTr()));
        holder.terjemahan.setText(ayat.getId());

    }

    @Override
    public int getItemCount() {
        return ayatList == null ? 0 : ayatList.size();
    }


    class AyatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomor, arab,latin, terjemahan;
        Button setlastread, btShare;
         AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            nomor = itemView.findViewById(R.id.tv_nomor);
            arab = itemView.findViewById(R.id.tv_arab);
            latin = itemView.findViewById(R.id.tv_latin);
            terjemahan = itemView.findViewById(R.id.tv_terjemahan);
            setlastread = itemView.findViewById(R.id.bt_bookmark);
            btShare = itemView.findViewById(R.id.bt_share);
            btShare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
             Ayat ayat = ayatList.get(getAdapterPosition());
             Intent intent = new Intent(Intent.ACTION_SEND);
             intent.putExtra(Intent.EXTRA_TEXT, ayat.getAr() + "\n" + Html.fromHtml(ayat.getTr()) + "\n" + ayat.getId());
             intent.setType("text/plain");
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
             context.startActivity(intent);
        }
    }
}
