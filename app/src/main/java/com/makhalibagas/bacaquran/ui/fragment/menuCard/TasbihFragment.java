package com.makhalibagas.bacaquran.ui.fragment.menuCard;

import android.content.Intent;
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
import com.makhalibagas.bacaquran.ui.activity.SettingsActivity;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.HomeFragment;

/**
 * Created by Bagas Makhali
 */

public class TasbihFragment extends Fragment implements View.OnClickListener {

    public TasbihFragment() {
        // Required empty public constructor
    }

    private TextView angka;
    private int dzikir = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasbih, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        angka = view.findViewById(R.id.tv_angka);
        angka.setText("" + dzikir);
        Button tambah = view.findViewById(R.id.bt_tambah);
        Button kurang = view.findViewById(R.id.bt_kurang);
        Button reset = view.findViewById(R.id.bt_reset);
        tambah.setOnClickListener(this);
        kurang.setOnClickListener(this);
        reset.setOnClickListener(this);
        Button settings = view.findViewById(R.id.bt_settings);
        settings.setOnClickListener(this);
        Button back = view.findViewById(R.id.bt_back);
        back.setOnClickListener(this);

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
            case R.id.bt_reset:
                dzikir = 0;
                angka.setText("" + dzikir);
                break;
            case R.id.bt_tambah:
                dzikir = dzikir + 1;
                angka.setText("" + dzikir);
                break;
            case R.id.bt_kurang:
                dzikir = dzikir - 1;
                angka.setText("" + dzikir);
                break;
        }
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

}
