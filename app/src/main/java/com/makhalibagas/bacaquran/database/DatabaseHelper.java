package com.makhalibagas.bacaquran.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.ARABIC;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.JUMLAHAYAT;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.LATIN;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.SURAT_TABLE;
import static com.makhalibagas.bacaquran.database.DatabaseContract.SuratColumns.TRANSLATE;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final String SURAT_DATABASE_NAME = "suratdb";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, SURAT_DATABASE_NAME, null , DATABASE_VERSION);
    }


    private static final String SQL_CREATE_TABLE_SURAT = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)", SURAT_TABLE, _ID, ARABIC, LATIN, TRANSLATE, JUMLAHAYAT);

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SURAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SURAT_TABLE);
        onCreate(db);
    }
}
