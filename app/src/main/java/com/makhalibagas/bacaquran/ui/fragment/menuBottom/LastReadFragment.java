package com.makhalibagas.bacaquran.ui.fragment.menuBottom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.quran.Ayat;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.preference.Preferences;
import com.makhalibagas.bacaquran.ui.activity.AyatActivity;
import com.makhalibagas.bacaquran.ui.activity.SettingsActivity;

import java.util.Objects;

/**
 * Created by Bagas Makhali
 */

public class LastReadFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_read, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button settings = view.findViewById(R.id.bt_settings);
        settings.setOnClickListener(this);
        Button back = view.findViewById(R.id.bt_back);
        back.setOnClickListener(this);


        Preferences preferences = new Preferences(Objects.requireNonNull(getContext()));
        Surat surat = preferences.getSurat();

        TextView tvArabLast = view.findViewById(R.id.tv_arabLast);
        TextView tvSurahLat = view.findViewById(R.id.tv_surahLast);

        tvArabLast.setText(surat.getArabic().isEmpty() ? "الفاتحة" : surat.getArabic());
        tvSurahLat.setText(surat.getLatin().isEmpty() ? "Al-Fatihah" : surat.getLatin());


        CardView cvLast = view.findViewById(R.id.cv_last);
        cvLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AyatActivity.class);
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()){
            case R.id.bt_back:
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;
            case R.id.bt_settings:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
        }
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}
