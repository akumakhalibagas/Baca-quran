package com.makhalibagas.bacaquran.model.sholat

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSholat(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataItem,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable