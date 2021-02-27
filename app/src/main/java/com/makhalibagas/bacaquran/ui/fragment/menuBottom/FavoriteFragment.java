package com.makhalibagas.bacaquran.ui.fragment.menuBottom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.SuratAdapter;
import com.makhalibagas.bacaquran.database.SuratHelper;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.ui.activity.SettingsActivity;
import com.makhalibagas.bacaquran.utils.LoadSuratCallback;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Bagas Makhali
 */

public class FavoriteFragment extends Fragment implements LoadSuratCallback, View.OnClickListener {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        SuratHelper suratHelper = SuratHelper.getInstance(getActivity());
        suratHelper.open();

        recyclerView = view.findViewById(R.id.rv_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button settings = view.findViewById(R.id.bt_settings);
        settings.setOnClickListener(this);
        Button back = view.findViewById(R.id.bt_back);
        back.setOnClickListener(this);

        TextView pesan = view.findViewById(R.id.pesan);
        pesan.setText(R.string.pesan);

        if (savedInstanceState == null){
            new LoadAsyncSurat(suratHelper, this).execute();
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(List<Surat> suratList) {
        SuratAdapter suratAdapter = new SuratAdapter(getContext(), suratList);
        recyclerView.setAdapter(suratAdapter);

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

    private static class LoadAsyncSurat extends AsyncTask<Void, Void, List<Surat>>{
        private final WeakReference<SuratHelper> suratHelperWeakReference;
        private final WeakReference<LoadSuratCallback> loadSuratCallbackWeakReference;

        LoadAsyncSurat(SuratHelper suratHelper, LoadSuratCallback loadSuratCallback){
            suratHelperWeakReference = new WeakReference<>(suratHelper);
            loadSuratCallbackWeakReference = new WeakReference<>(loadSuratCallback);
        }

        @Override
        protected List<Surat> doInBackground(Void... voids) {
            return suratHelperWeakReference.get().getFavoriteSurat();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadSuratCallbackWeakReference.get().preExecute();
        }

        @Override
        protected void onPostExecute(List<Surat> surats) {
            super.onPostExecute(surats);
            loadSuratCallbackWeakReference.get().postExecute(surats);
        }
    }
}