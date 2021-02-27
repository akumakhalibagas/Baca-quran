package com.makhalibagas.bacaquran.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.makhalibagas.bacaquran.model.quran.SuratMp3;
import com.makhalibagas.bacaquran.retrofit.ApiService;
import com.makhalibagas.bacaquran.retrofit.RetroConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratViewModel extends ViewModel {

    private MutableLiveData<List<SuratMp3>> suratListMp3 = new MutableLiveData<>();

    public void loadDataSuratMp3(){
        ApiService api = RetroConfig.getInstance().create(ApiService.class);
        Call<List<SuratMp3>> call = api.getAllSuratMp3();
        call.enqueue(new Callback<List<SuratMp3>>() {
            @Override
            public void onResponse(Call<List<SuratMp3>> call, Response<List<SuratMp3>> response) {
                suratListMp3.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SuratMp3>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<SuratMp3>> getAllSuratMp3(){
        return suratListMp3;
    }

}
