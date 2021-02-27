package com.makhalibagas.bacaquran.ui.fragment.menuCard;

import android.content.Intent;
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
import android.widget.SearchView;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.SuratAdapter;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.ui.activity.SettingsActivity;
import com.makhalibagas.bacaquran.ui.fragment.menuBottom.HomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Bagas Makhali
 */


public class SuratFragment extends Fragment implements View.OnClickListener {

    private List<Surat> suratList = new ArrayList<>();
    private SuratAdapter suratAdapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_surat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rv_surat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        writeJson();
        Button settings = view.findViewById(R.id.bt_settings);
        settings.setOnClickListener(this);
        Button back = view.findViewById(R.id.bt_back);
        back.setOnClickListener(this);

        SearchView searchView = view.findViewById(R.id.sv_surat);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                suratAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                suratAdapter.getFilter().filter(newText);


                return false;
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void writeJson(){
        try {

            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJson()));
            JSONArray jsonArray = jsonObject.getJSONArray("surah_info");
            for ( int position = 0; position < jsonArray.length(); position++){

                Surat surat = new Surat();
                JSONObject allData = jsonArray.getJSONObject(position);
                surat.setIndex(allData.getInt("index"));
                surat.setLatin(allData.getString("latin"));
                surat.setOpening(allData.getString("opening"));
                surat.setClosing(allData.getString("closing"));
                surat.setArabic(allData.getString("arabic"));
                surat.setAyahCount(allData.getInt("ayah_count"));
                surat.setTranslation(allData.getString("translation"));
                suratList.add(surat);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        suratAdapter = new SuratAdapter(getContext(), suratList);
        recyclerView.setAdapter(suratAdapter);
    }
    private String loadJson(){
        StringBuilder stringBuilder = new StringBuilder();
        try {

            InputStream inputStream = Objects.requireNonNull(getActivity()).getAssets().open("surah-info.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

            bufferedReader.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
