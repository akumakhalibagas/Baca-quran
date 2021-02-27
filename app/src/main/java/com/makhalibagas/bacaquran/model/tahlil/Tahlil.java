package com.makhalibagas.bacaquran.model.tahlil;

import com.google.gson.annotations.SerializedName;

public class Tahlil {

	@SerializedName("arabic")
	private String arabic;

	@SerializedName("translation")
	private String translation;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;
	private boolean expanded;

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"arabic = '" + arabic + '\'' + 
			",translation = '" + translation + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}