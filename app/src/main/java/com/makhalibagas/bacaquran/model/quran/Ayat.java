package com.makhalibagas.bacaquran.model.quran;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Ayat implements Parcelable {

	@SerializedName("ar")
	private String ar;

	@SerializedName("id")
	private String id;

	@SerializedName("nomor")
	private String nomor;

	@SerializedName("tr")
	private String tr;

	private boolean expanded;

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public void setAr(String ar){
		this.ar = ar;
	}

	public String getAr(){
		return ar;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNomor(String nomor){
		this.nomor = nomor;
	}

	public String getNomor(){
		return nomor;
	}

	public void setTr(String tr){
		this.tr = tr;
	}

	public String getTr(){
		return tr;
	}

	@Override
 	public String toString(){
		return 
			"Ayat{" + 
			"ar = '" + ar + '\'' + 
			",id = '" + id + '\'' + 
			",nomor = '" + nomor + '\'' + 
			",tr = '" + tr + '\'' + 
			"}";
		}

	public Ayat() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.ar);
		dest.writeString(this.id);
		dest.writeString(this.nomor);
		dest.writeString(this.tr);
	}

	protected Ayat(Parcel in) {
		this.ar = in.readString();
		this.id = in.readString();
		this.nomor = in.readString();
		this.tr = in.readString();
	}

	public static final Creator<Ayat> CREATOR = new Creator<Ayat>() {
		@Override
		public Ayat createFromParcel(Parcel source) {
			return new Ayat(source);
		}

		@Override
		public Ayat[] newArray(int size) {
			return new Ayat[size];
		}
	};
}