package com.makhalibagas.bacaquran.retrofit;

import com.makhalibagas.bacaquran.model.quran.Ayat;
import com.makhalibagas.bacaquran.model.quran.SuratMp3;
import com.makhalibagas.bacaquran.model.sholat.ResponseSholat;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @GET("data.json")
    Call<List<SuratMp3>> getAllSuratMp3();

    @GET("surat/{no}.json")
    Call<List<Ayat>> getAllAyat(@Path("no") String no);

    @GET("v1/timingsByCity")
    Call<ResponseSholat> getJadwalSholat( @Query("city") String city, @Query("country") String country);
}



