package com.makhalibagas.bacaquran.model.sholat

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Bagas Makhali on 6/30/2020.
 */
@Parcelize
class DataItem (
        @field:SerializedName("timings")
        val timings : Timings
): Parcelable
