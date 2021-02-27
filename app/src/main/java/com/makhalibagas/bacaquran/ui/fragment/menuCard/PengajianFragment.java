package com.makhalibagas.bacaquran.ui.fragment.menuCard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.PengajianAdapter;
import com.makhalibagas.bacaquran.utils.DataPengajian;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengajianFragment extends Fragment {

    public PengajianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengajian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.rvPengajian);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PengajianAdapter(DataPengajian.INSTANCE.dataPengajian()));

        super.onViewCreated(view, savedInstanceState);
    }
}
