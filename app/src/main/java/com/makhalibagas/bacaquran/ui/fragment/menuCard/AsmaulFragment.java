package com.makhalibagas.bacaquran.ui.fragment.menuCard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.AsmaulAdapter;
import com.makhalibagas.bacaquran.model.asmaul.Asmaul;
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

public class AsmaulFragment extends Fragment implements View.OnClickListener {

    public AsmaulFragment() {
        // Required empty public constructor
    }

    private List<Asmaul> asmaulList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_asmaul, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rv_asmaul);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
                Asmaul asmaul = new Asmaul();
                JSONObject allData = jsonArray.getJSONObject(position);


                asmaul.setIndex(allData.getString("index"));
                asmaul.setLatin(allData.getString("latin"));
                asmaul.setArabic(allData.getString("arabic"));
                asmaul.setTranslationEn(allData.getString("translation_en"));
                asmaul.setTranslationId(allData.getString("translation_id"));
                asmaulList.add(asmaul);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(new AsmaulAdapter(getContext() ,asmaulList));

    }
    private String loadJson(){
        StringBuilder stringBuilder = new StringBuilder();
        try {

            InputStream inputStream = Objects.requireNonNull(getActivity()).getAssets().open("asmaul-husna.json");
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
