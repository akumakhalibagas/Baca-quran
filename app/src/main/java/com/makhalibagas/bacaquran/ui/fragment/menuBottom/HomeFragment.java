package com.makhalibagas.bacaquran.ui.fragment.menuBottom;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.makhalibagas.bacaquran.R;
import com.makhalibagas.bacaquran.adapter.ViewPagerAdapter;
import com.makhalibagas.bacaquran.database.SuratHelper;
import com.makhalibagas.bacaquran.model.quran.Surat;
import com.makhalibagas.bacaquran.ui.activity.SettingsActivity;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.AsmaulFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.DoaFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.Mp3Fragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.PengajianFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.SholatFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.SuratFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.TahlilFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.TasbihFragment;
import com.makhalibagas.bacaquran.ui.fragment.menuCard.WiridFragment;
import com.makhalibagas.bacaquran.utils.LoadSuratCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Bagas Makhali
 */

public class HomeFragment extends Fragment implements View.OnClickListener, LoadSuratCallback {

    private List<Surat> suratList = new ArrayList<>();
    private ViewPager vp_fav, vp_recommend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        setOnClick(view);
        SuratHelper suratHelper = SuratHelper.getInstance(getContext());
        suratHelper.open();

        vp_fav = view.findViewById(R.id.vp_favorite);
        vp_recommend = view.findViewById(R.id.viewpager);
        writeJson();


        if (savedInstanceState == null){
            new LoadAsyncSurat(suratHelper, this).execute();
        }

        super.onViewCreated(view, savedInstanceState);
    }



    private static class LoadAsyncSurat extends AsyncTask<Void, Void, List<Surat>> {
        private final WeakReference<SuratHelper> suratHelperWeakReference;
        private final WeakReference<LoadSuratCallback> loadSuratCallbackWeakReference;

        private LoadAsyncSurat(SuratHelper suratHelper, LoadSuratCallback loadSuratCallback){
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

        vp_recommend.setAdapter(new ViewPagerAdapter(suratList, getContext()));
    }
    private String loadJson(){
        StringBuilder stringBuilder = new StringBuilder();
        try {

            InputStream inputStream = Objects.requireNonNull(getActivity()).getAssets().open("surah-recommend.json");
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
    private void setOnClick(@NonNull View view) {
        CardView quran = view.findViewById(R.id.cv_quran);
        quran.setOnClickListener(this);
        CardView doa = view.findViewById(R.id.cv_doa);
        doa.setOnClickListener(this);
        CardView wirid = view.findViewById(R.id.cv_wirid);
        wirid.setOnClickListener(this);
        CardView asmaul = view.findViewById(R.id.cv_asmaul);
        asmaul.setOnClickListener(this);
        CardView tahlil = view.findViewById(R.id.cv_tahlil);
        tahlil.setOnClickListener(this);
        CardView tasbih = view.findViewById(R.id.cv_lainya);
        tasbih.setOnClickListener(this);
        Button menu = view.findViewById(R.id.bt_menu);
        menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        switch (v.getId()){
            case R.id.cv_quran:
                fragment = new SuratFragment();
                loadFragment(fragment);
                break;
            case R.id.cv_doa:
                fragment = new DoaFragment();
                loadFragment(fragment);
                break;
            case R.id.cv_wirid:
                fragment = new WiridFragment();
                loadFragment(fragment);
                break;
            case R.id.cv_asmaul:
                fragment = new AsmaulFragment();
                loadFragment(fragment);
                break;
            case R.id.cv_tahlil:
                fragment = new TahlilFragment();
                loadFragment(fragment);
                break;
            case R.id.cv_lainya:
                final Dialog view = new Dialog(Objects.requireNonNull(getActivity()));
                view.setContentView(R.layout.dialog_lainya);
                CardView quran = view.findViewById(R.id.cv_quran);
                quran.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new SuratFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });
                CardView doa = view.findViewById(R.id.cv_doa);
                doa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new DoaFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });
                CardView wirid = view.findViewById(R.id.cv_wirid);
                wirid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new WiridFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });
                CardView asmaul = view.findViewById(R.id.cv_asmaul);
                asmaul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new AsmaulFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });
                CardView tahlil = view.findViewById(R.id.cv_tahlil);
                tahlil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new TahlilFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });
                CardView tasbih = view.findViewById(R.id.cv_tasbih);
                tasbih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new TasbihFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });

                CardView mp3 = view.findViewById(R.id.cv_mp3);
                mp3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new Mp3Fragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });

                CardView sholat = view.findViewById(R.id.cv_sholat);
                sholat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new SholatFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });

                CardView pengajian = view.findViewById(R.id.cv_pengajian);
                pengajian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new PengajianFragment();
                        loadFragment(fragment);
                        view.dismiss();
                    }
                });
                view.show();
                break;
            case R.id.bt_menu:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;


        }
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void preExecute() {
    }

    @Override
    public void postExecute(List<Surat> suratList) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(suratList, getContext());
        vp_fav.setAdapter(viewPagerAdapter);
    }
}
