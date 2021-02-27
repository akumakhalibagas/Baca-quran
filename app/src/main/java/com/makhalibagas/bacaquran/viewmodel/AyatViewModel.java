package com.makhalibagas.bacaquran.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.makhalibagas.bacaquran.model.quran.Ayat;
import com.makhalibagas.bacaquran.retrofit.ApiService;
import com.makhalibagas.bacaquran.retrofit.RetroConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AyatViewModel extends ViewModel {


    private MutableLiveData<List<Ayat>> ayatList = new MutableLiveData<>();

    public void loadDataAyat(String no){
        ApiService api = RetroConfig.getInstance().create(ApiService.class);
        Call<List<Ayat>> call = api.getAllAyat(no);
        call.enqueue(new Callback<List<Ayat>>() {
            @Override
            public void onResponse(Call<List<Ayat>> call, Response<List<Ayat>> response) {
                ayatList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Ayat>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Ayat>> getAllAyat(){
        return ayatList;
    }
}
