package com.makhalibagas.bacaquran.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.makhalibagas.bacaquran.model.quran.Surat;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.ARABIC;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.JUMLAHAYAT;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.LATIN;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.SURAT_TABLE;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.TRANSLATE;

public class SuratHelper {

    private static final String DATABASE_TABLE = SURAT_TABLE;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static SuratHelper suratHelper;


    private SuratHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static SuratHelper getInstance(Context context){
        if (suratHelper == null){
            synchronized (SQLiteOpenHelper.class){
                if (suratHelper == null){
                    suratHelper = new SuratHelper(context);
                }
            }
        }
        return suratHelper;
    }


    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }


    public List<Surat> getFavoriteSurat(){
        List<Surat> suratList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Surat surat;
        if (cursor.getCount() > 0){
            do {
                surat = new Surat();
                surat.setIndex(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(_ID))));
                surat.setArabic(cursor.getString(cursor.getColumnIndexOrThrow(ARABIC)));
                surat.setLatin(cursor.getString(cursor.getColumnIndexOrThrow(LATIN)));
                surat.setTranslation(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));
                surat.setAyahCount(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(JUMLAHAYAT))));


                suratList.add(surat);
                cursor.moveToNext();

            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return suratList;
    }


    public long addFavorite(Surat surat){
        ContentValues values = new ContentValues();

        values.put(_ID, surat.getIndex());
        values.put(ARABIC, surat.getArabic());
        values.put(LATIN, surat.getLatin());
        values.put(TRANSLATE, surat.getTranslation());
        values.put(JUMLAHAYAT, surat.getAyahCount());

        return database.insert(DATABASE_TABLE, null , values);
    }

    public void deleteFavorite(String index){
        database.delete(SURAT_TABLE, _ID + "=" + index, null);
    }
    public boolean checkSurat(String id) {
        database = databaseHelper.getWritableDatabase();
        String selectString = "SELECT * FROM " + SURAT_TABLE + " WHERE " + _ID + " =?";
        Cursor cursor = database.rawQuery(selectString, new String[]{id});
        boolean checkMovie = false;
        if (cursor.moveToFirst()) {
            checkMovie = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            Log.d(TAG, String.format("%d records found", count));
        }
        cursor.close();
        return checkMovie;
    }
}
