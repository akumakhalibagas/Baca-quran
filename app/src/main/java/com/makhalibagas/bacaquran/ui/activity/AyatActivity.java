package com.makhalibagas.bacaquran.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.AyatAdapter;
import com.makhalibagas.bacaquran.model.quran.Ayat;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.preference.Preferences;
import com.makhalibagas.bacaquran.viewmodel.AyatViewModel;

import java.util.List;

/**
 * Created by Bagas Makhali
 */

public class AyatActivity extends AppCompatActivity implements View.OnClickListener {

    private AyatAdapter ayatAdapter;
    private ProgressBar progressBar;
    private Dialog dialogPergiKe, dialogDetail;
    private RecyclerView recyclerView;
    private Surat surat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ayat);

        Preferences preferences = new Preferences(this);
        surat = preferences.getSurat();
        loadData();

        TextView tvtoolbarMain = findViewById(R.id.tv_toolbar);
        tvtoolbarMain.setText(surat.getLatin().isEmpty() ? "" : surat.getLatin());
        tvtoolbarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetail = new Dialog(AyatActivity.this);

                dialogDetail.setContentView(R.layout.layout_detail);
                TextView arab = dialogDetail.findViewById(R.id.tv_arab);
                TextView name = dialogDetail.findViewById(R.id.tv_name);
                TextView arti = dialogDetail.findViewById(R.id.tv_arti);
                TextView tvtoolbar = dialogDetail.findViewById(R.id.tv_toolbar);
                TextView jumlahayat = dialogDetail.findViewById(R.id.tv_jumlhayat);
                TextView opening = dialogDetail.findViewById(R.id.tvOpening);
                TextView closing = dialogDetail.findViewById(R.id.tvClosing);
                Button btBack = dialogDetail.findViewById(R.id.bt_back);

                btBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDetail.dismiss();
                    }
                });

                arab.setText(surat.getArabic().isEmpty() ? "" : surat.getArabic());
                name.setText(surat.getLatin().isEmpty() ? "" : "( " + surat.getLatin() + " : ");
                arti.setText(surat.getTranslation().isEmpty() ? "" : surat.getTranslation() + " )");
                tvtoolbar.setText(surat.getLatin().isEmpty() ? "" : surat.getLatin());
                jumlahayat.setText(String.valueOf(surat.getAyahCount()).isEmpty() ? "" : String.valueOf(surat.getAyahCount()) + " Ayat");
                opening.setText(Html.fromHtml(surat.getOpening().isEmpty() ? "" : surat.getOpening()));
                closing.setText(Html.fromHtml(surat.getClosing().isEmpty() ? "" : surat.getClosing()));
                dialogDetail.show();
            }
        });

    }

    private void loadData(){
        progressBar = findViewById(R.id.progressBar);
        showLoading(true);
        recyclerView = findViewById(R.id.rv_ayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AyatViewModel ayatViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(AyatViewModel.class);
        ayatViewModel.loadDataAyat(String.valueOf(surat.getIndex()).isEmpty() ? "" : String.valueOf(surat.getIndex()));
        ayatViewModel.getAllAyat().observe(this, new Observer<List<Ayat>>() {
            @Override
            public void onChanged(List<Ayat> ayats) {
                ayatAdapter = new AyatAdapter(getApplicationContext(), ayats);
                recyclerView.setAdapter(ayatAdapter);
                showLoading(false);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSettings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            case R.id.bt_back:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.btSet:
                dialogPergiKe = new Dialog(AyatActivity.this);
                dialogPergiKe.setContentView(R.layout.dialog_pergike);
                final EditText editText = dialogPergiKe.findViewById(R.id.etLast);
                TextView tvNomor = dialogPergiKe.findViewById(R.id.tvNomor);
                tvNomor.setText(String.valueOf(surat.getAyahCount()).isEmpty() ? "" : String.valueOf(surat.getAyahCount()));
                Button tvOk = dialogPergiKe.findViewById(R.id.tvOk);
                Button tvBtal = dialogPergiKe.findViewById(R.id.tvBatal);
                tvBtal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPergiKe.dismiss();
                    }
                });

                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String index = editText.getText().toString().trim();
                        int ayatke = !index.equals("")?Integer.parseInt(index):0;
                        recyclerView.scrollToPosition(ayatke - 1);
                        //dikurangi 1 karena index default dimulai dari 0
                        dialogPergiKe.dismiss();
                    }
                });

                dialogPergiKe.show();
                break;
        }
    }

    private void showLoading(boolean status){
        if (status){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
