package org.model;

public class Stop {

//    <route tag="192" title="192-Airport Rocket" color="ff0000" oppositeColor="ffffff" latMin="43.6303899" latMax="43.6859399" lonMin="-79.62016" lonMax="-79.53566">
//      <stop tag="3169" title="Pearson Airport Terminal 3 (Arrivals Level Columns C8-C12)" lat="43.6859399" lon="-79.62016" stopId="14278"/>

	public String stopId;
	public String tag;
	public String title;
	public String lat;
	public String lon;

    public String getStopId() {
        return stopId;
    }

    public Stop setStopId(String stopId) {
        this.stopId = stopId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Stop setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Stop setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public Stop setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLon() {
        return lon;
    }

    public Stop setLon(String lon) {
        this.lon = lon;
        return this;
    }



    @Override
    public String toString() {
        return "Stop{" + "stopId='" + stopId + ", tag='" + tag +
                ", title='" + title + ", lat='" + lat +  ", lon='" + lon + '}';
    }





}
