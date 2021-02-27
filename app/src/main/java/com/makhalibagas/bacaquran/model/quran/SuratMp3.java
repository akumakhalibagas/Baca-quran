package com.makhalibagas.bacaquran.model.quran;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SuratMp3 implements Parcelable {

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("rukuk")
	private String rukuk;

	@SerializedName("nama")
	private String nama;

	@SerializedName("ayat")
	private int ayat;

	@SerializedName("urut")
	private String urut;

	@SerializedName("arti")
	private String arti;

	@SerializedName("asma")
	private String asma;

	@SerializedName("audio")
	private String audio;

	@SerializedName("type")
	private String type;

	@SerializedName("nomor")
	private String nomor;

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setRukuk(String rukuk){
		this.rukuk = rukuk;
	}

	public String getRukuk(){
		return rukuk;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setAyat(int ayat){
		this.ayat = ayat;
	}

	public int getAyat(){
		return ayat;
	}

	public void setUrut(String urut){
		this.urut = urut;
	}

	public String getUrut(){
		return urut;
	}

	public void setArti(String arti){
		this.arti = arti;
	}

	public String getArti(){
		return arti;
	}

	public void setAsma(String asma){
		this.asma = asma;
	}

	public String getAsma(){
		return asma;
	}

	public void setAudio(String audio){
		this.audio = audio;
	}

	public String getAudio(){
		return audio;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setNomor(String nomor){
		this.nomor = nomor;
	}

	public String getNomor(){
		return nomor;
	}

	@Override
 	public String toString(){
		return 
			"SuratMp3{" + 
			"keterangan = '" + keterangan + '\'' + 
			",rukuk = '" + rukuk + '\'' + 
			",nama = '" + nama + '\'' + 
			",ayat = '" + ayat + '\'' + 
			",urut = '" + urut + '\'' + 
			",arti = '" + arti + '\'' + 
			",asma = '" + asma + '\'' + 
			",audio = '" + audio + '\'' + 
			",type = '" + type + '\'' + 
			",nomor = '" + nomor + '\'' + 
			"}";
		}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keterangan);
        dest.writeString(this.rukuk);
        dest.writeString(this.nama);
        dest.writeInt(this.ayat);
        dest.writeString(this.urut);
        dest.writeString(this.arti);
        dest.writeString(this.asma);
        dest.writeString(this.audio);
        dest.writeString(this.type);
        dest.writeString(this.nomor);
    }

    public SuratMp3() {
    }

    protected SuratMp3(Parcel in) {
        this.keterangan = in.readString();
        this.rukuk = in.readString();
        this.nama = in.readString();
        this.ayat = in.readInt();
        this.urut = in.readString();
        this.arti = in.readString();
        this.asma = in.readString();
        this.audio = in.readString();
        this.type = in.readString();
        this.nomor = in.readString();
    }

    public static final Parcelable.Creator<SuratMp3> CREATOR = new Parcelable.Creator<SuratMp3>() {
        @Override
        public SuratMp3 createFromParcel(Parcel source) {
            return new SuratMp3(source);
        }

        @Override
        public SuratMp3[] newArray(int size) {
            return new SuratMp3[size];
        }
    };
}