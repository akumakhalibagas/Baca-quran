package com.makhalibagas.bacaquran.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.makhalibagas.bacaquran.model.quran.Ayat;
import com.makhalibagas.bacaquran.model.quran.Surat;

public class Preferences {

    private static final String MYSHARED = "PREF";
    private static final String CLOSING = "closing";
    private static final String AYATCOUNT = "ayat";
    private static final String ARABIC = "arabic";
    private static final String TRANSLATION = "translation";
    private static final String INDEX = "index";
    private static final String LATIN = "latin";
    private static final String OPENING = "opening";

    private static final String ARAB = "arab";
    private static final String ID = "indo";
    private static final String NOMOR = "nomor";
    private static final String LATINAYAT = "latinayat";
    private static SharedPreferences sharedPreferences;

    public Preferences(Context context){
        sharedPreferences = context.getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);
    }

    public void setAyat(Ayat ayat){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ARAB, ayat.getAr());
        editor.putString(ID, ayat.getId());
        editor.putString(NOMOR, ayat.getNomor());
        editor.putString(LATINAYAT, ayat.getTr());
        editor.apply();
    }

    public void setSurat(Surat surat){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CLOSING, surat.getClosing());
        editor.putInt(AYATCOUNT, surat.getAyahCount());
        editor.putString(ARABIC, surat.getArabic());
        editor.putString(TRANSLATION, surat.getTranslation());
        editor.putInt(INDEX, surat.getIndex());
        editor.putString(LATIN, surat.getLatin());
        editor.putString(OPENING, surat.getOpening());
        editor.apply();
    }


    public Surat getSurat(){
        Surat surat = new Surat();
        surat.setClosing(sharedPreferences.getString(CLOSING, ""));
        surat.setAyahCount(sharedPreferences.getInt(AYATCOUNT, 0));
        surat.setArabic(sharedPreferences.getString(ARABIC, ""));
        surat.setTranslation(sharedPreferences.getString(TRANSLATION, ""));
        surat.setIndex(sharedPreferences.getInt(INDEX, 0));
        surat.setLatin(sharedPreferences.getString(LATIN, ""));
        surat.setOpening(sharedPreferences.getString(OPENING, ""));
        return surat;
    }

    public Ayat getAyat(){
        Ayat ayat = new Ayat();
        ayat.setAr(sharedPreferences.getString(ARAB, ""));
        ayat.setId(sharedPreferences.getString(ID, ""));
        ayat.setNomor(sharedPreferences.getString(NOMOR, ""));
        ayat.setTr(sharedPreferences.getString(LATINAYAT, ""));
        return ayat;
    }
}