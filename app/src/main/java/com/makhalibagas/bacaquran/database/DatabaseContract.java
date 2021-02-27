package com.makhalibagas.bacaquran.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static class SuratColumns implements BaseColumns{
        public static final String SURAT_TABLE = "surat_table";

        public static final String ARABIC = "arabic";
        public static final String LATIN = "latin";
        public static final String TRANSLATE = "translate";
        public static final String JUMLAHAYAT = "jumlahayat";

    }
}
