package com.makhalibagas.bacaquran.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.ui.activity.SettingsActivity;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.HomeFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.Mp3Fragment;

import java.util.ArrayList;

/**
 * Created by Bagas Makhali
 */

public class AboutFragment extends Fragment implements View.OnClickListener {

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_about, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button settings = view.findViewById(R.id.bt_settings);
        settings.setOnClickListener(this);
        Button back = view.findViewById(R.id.bt_back);
        back.setOnClickListener(this);

        TextView url1 = view.findViewById(R.id.ra1);
        url1.setOnClickListener(this);

        TextView url2 = view.findViewById(R.id.ra2);
        url2.setOnClickListener(this);

        TextView url3 = view.findViewById(R.id.ra3);
        url3.setOnClickListener(this);

        TextView url4 = view.findViewById(R.id.ra4);
        url4.setOnClickListener(this);

        TextView urlInpired = view.findViewById(R.id.urlInspired);
        urlInpired.setOnClickListener(this);


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
            case R.id.bt_settings:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
            case R.id.ra1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mazipan/quran-offline")));
                break;
            case R.id.ra2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/bachors/Al-Quran-ID-API")));
                break;
            case R.id.ra3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flaticon.com/free-icon/muslim_2759837")));
                break;
            case R.id.ra4:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flaticon.com/search?word=tasbih#")));
                break;
            case R.id.urlInspired:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baca-quran.id/")));
                break;
        }
    }

}
