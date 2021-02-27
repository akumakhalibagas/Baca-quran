package com.makhalibagas.bacaquran.model.pengajian

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Bagas Makhali on 6/28/2020.
 */
@Parcelize
data class Pengajian (
        val url:String,
        val name:String,
        val image:String
):Parcelable