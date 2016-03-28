package org.model;

public class Route {

	//	body copyright="All data copyright Toronto Transit Commission 2016.">
	//	<route tag="5" title="5-Avenue Rd"/>

	public String tag;
	public String title;
	
	
	public String getTag() {
		return tag;
	}
	public Route setTag(String tag) {
		this.tag = tag;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Route setTitle(String title) {
		this.title = title;
		return this;
	}

	
	@Override
	public String toString() {
		return "Route [tag=" + tag +
				", title=" + title + "]";
	}

}
