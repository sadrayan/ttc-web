package org.model;

public class Agent {
	

	private String tag;
	private String title;
	private String regionTitle;
	
	
	public String getTag() {
		return tag;
	}
	public Agent setTag(String tag) {
		this.tag = tag;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Agent setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getRegionTitle() {
		return regionTitle;
	}
	public Agent setRegionTitle(String regionTitle) {
		this.regionTitle = regionTitle;
		return this;
	}
	
	@Override
	public String toString() {
		return "Agent [tag=" + tag + ", title=" + title + ","
				+ " regionTitle=" + regionTitle + "]";
	}
	
	
	
	

}
