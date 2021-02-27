package com.makhalibagas.bacaquran.model.doa;


public class Doa {


	private String arabic;


	private String translation;


	private String latin;


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

	public void setLatin(String latin){
		this.latin = latin;
	}

	public String getLatin(){
		return latin;
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
			"Response{" + 
			"arabic = '" + arabic + '\'' + 
			",translation = '" + translation + '\'' + 
			",latin = '" + latin + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}