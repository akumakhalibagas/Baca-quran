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
import android.widget.ProgressBar;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.DoaAdapter;
import com.makhalibagas.bacaquran.model.doa.Doa;
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

public class DoaFragment extends Fragment implements View.OnClickListener {

    public DoaFragment() {
        // Required empty public constructor
    }

    private ProgressBar progressBar;
    private List<Doa> doaList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doa, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rv_doa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button settings = view.findViewById(R.id.bt_settings);
        settings.setOnClickListener(this);
        Button back = view.findViewById(R.id.bt_back);
        back.setOnClickListener(this);
        writeJson();
        super.onViewCreated(view, savedInstanceState);
    }


    private void writeJson(){
        try {

            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJson()));
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for ( int position = 0; position < jsonArray.length(); position++){
                Doa doa = new Doa();
                JSONObject allData = jsonArray.getJSONObject(position);

                doa.setArabic(allData.getString("arabic"));
                doa.setTitle(allData.getString("title"));
                doa.setLatin(allData.getString("latin"));
                doa.setTranslation(allData.getString("translation"));
                doaList.add(doa);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(new DoaAdapter(getContext(), doaList));

    }
    private String loadJson(){
        StringBuilder stringBuilder = new StringBuilder();
        try {

            InputStream inputStream = Objects.requireNonNull(getActivity()).getAssets().open("daily-doa.json");
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
