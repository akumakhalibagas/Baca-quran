package com.makhalibagas.bacaquran.model.quran;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Surat implements Parcelable {

	@SerializedName("closing")
	private String closing;

	@SerializedName("ayah_count")
	private int ayahCount;

	@SerializedName("arabic")
	private String arabic;

	@SerializedName("translation")
	private String translation;

	@SerializedName("index")
	private int index;

	@SerializedName("latin")
	private String latin;

	@SerializedName("opening")
	private String opening;

	public Surat(int ayahCount, String arabic, String translation, int index, String latin) {
		this.ayahCount = ayahCount;
		this.arabic = arabic;
		this.translation = translation;
		this.index = index;
		this.latin = latin;
	}

	public void setClosing(String closing){
		this.closing = closing;
	}

	public String getClosing(){
		return closing;
	}

	public void setAyahCount(int ayahCount){
		this.ayahCount = ayahCount;
	}

	public int getAyahCount(){
		return ayahCount;
	}

	public void setArabic(String arabic){
		this.arabic = arabic;
	}

	public String getArabic(){
		return arabic;
	}

	public void setTranslation(String translation){
		this.translation = translation;
	}

	public String getTranslation(){
		return translation;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return index;
	}

	public void setLatin(String latin){
		this.latin = latin;
	}

	public String getLatin(){
		return latin;
	}

	public void setOpening(String opening){
		this.opening = opening;
	}

	public String getOpening(){
		return opening;
	}

	@Override
 	public String toString(){
		return 
			"SurahInfoItem{" + 
			"closing = '" + closing + '\'' + 
			",ayah_count = '" + ayahCount + '\'' + 
			",arabic = '" + arabic + '\'' + 
			",translation = '" + translation + '\'' + 
			",index = '" + index + '\'' + 
			",latin = '" + latin + '\'' + 
			",opening = '" + opening + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.closing);
		dest.writeInt(this.ayahCount);
		dest.writeString(this.arabic);
		dest.writeString(this.translation);
		dest.writeInt(this.index);
		dest.writeString(this.latin);
		dest.writeString(this.opening);
	}

	public Surat() {
	}

	protected Surat(Parcel in) {
		this.closing = in.readString();
		this.ayahCount = in.readInt();
		this.arabic = in.readString();
		this.translation = in.readString();
		this.index = in.readInt();
		this.latin = in.readString();
		this.opening = in.readString();
	}

	public static final Parcelable.Creator<Surat> CREATOR = new Parcelable.Creator<Surat>() {
		@Override
		public Surat createFromParcel(Parcel source) {
			return new Surat(source);
		}

		@Override
		public Surat[] newArray(int size) {
			return new Surat[size];
		}
	};
}