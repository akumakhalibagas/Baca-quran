package com.makhalibagas.bacaquran.ui.fragment.menuCard;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.Mp3Adapter;
import com.makhalibagas.bacaquran.model.quran.SuratMp3;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.HomeFragment;
import com.makhalibagas.bacaquran.viewmodel.SuratViewModel;

import java.util.List;

/**
 * Created by Bagas Makhali
 */

public class Mp3Fragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mp3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final RecyclerView recyclerView = view.findViewById(R.id.rvMp3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SuratViewModel suratViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SuratViewModel.class);
        suratViewModel.loadDataSuratMp3();
        suratViewModel.getAllSuratMp3().observe(getViewLifecycleOwner(), new Observer<List<SuratMp3>>() {
            @Override
            public void onChanged(List<SuratMp3> suratMp3s) {
                recyclerView.setAdapter(new Mp3Adapter(suratMp3s));
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

}
