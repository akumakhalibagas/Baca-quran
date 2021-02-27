package com.makhalibagas.bacaquran.utils;

import com.makhalibagas.bacaquran.model.quran.Surat;

import java.util.List;

public interface LoadSuratCallback {
    void preExecute();

    void postExecute(List<Surat> suratList);
}
