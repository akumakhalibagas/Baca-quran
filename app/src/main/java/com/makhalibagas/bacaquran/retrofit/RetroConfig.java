package com.makhalibagas.bacaquran.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConfig {


    private static Retrofit retrofitAyat;
    private static Retrofit retrofitSholat;
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if (retrofitAyat == null){
            retrofitAyat = new Retrofit.Builder()
                    .baseUrl("https://al-quran-8d642.firebaseio.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitAyat;
    }

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/makhalibagas/Aplikasi-Baca-Quran/data/data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getRetrofitSholat(){
        if (retrofitSholat == null){
            retrofitSholat = new Retrofit.Builder()
                    .baseUrl("http://api.aladhan.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitSholat;
    }
}
