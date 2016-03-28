package org.service.xml;

import org.model.Direction;
import org.model.Stop;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class StopHandler extends DefaultHandler {
	
	private List<Stop> stopList = null;
    private Stop stop = null;
    private List <Direction> directionList = null;
    private  Direction direction = null;
//    private String title;


    public StopHandler() {
        this.stopList = new ArrayList<>();
        this.directionList = new ArrayList<>();
    }

    private boolean isDirection = false;
 

    public List<Stop> getStopList() {
        return stopList;
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//e.g <route tag="192" title="192-Airport Rocket" color="ff0000" oppositeColor="ffffff" latMin="43.6303899" latMax="43.6859399" lonMin="-79.62016" lonMax="-79.53566">
        //      <stop tag="3169" title="Pearson Airport Terminal 3 (Arrivals Level Columns C8-C12)" lat="43.6859399" lon="-79.62016" stopId="14278"/>
        if (qName.equalsIgnoreCase("stop")) {
            String tag = attributes.getValue("tag");
            String title = attributes.getValue("title");
            String lat = attributes.getValue("lat");
            String lon = attributes.getValue("lon");
            String stopId = attributes.getValue("stopId");


            if (!isDirection) {
                stop = new Stop();
                stop.setTag(tag)
                        .setTitle(title)
                        .setLat(lat)
                        .setLon(lon)
                        .setStopId(stopId);
            }

        }

//        <direction tag="N____O_F00" title="Outbound to Ocean Beach via Downtown" name="Outbound" useForUI="true">
//            <stop tag="5240"/>
//            <stop tag="5237"/>
//        </direction>
        if (qName.equalsIgnoreCase("direction")) {
            direction = new Direction();
            direction.tag = attributes.getValue("tag");
            direction.title = attributes.getValue("title");
            direction.name = attributes.getValue("name");
            isDirection = true;
        }

        if (qName.equalsIgnoreCase("stop") && isDirection) {
            direction.directionStopList.add(attributes.getValue("tag"));
        }

	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("stop")){
            if (!isDirection) {
                stopList.add(stop);
            }

        }


        if (qName.equalsIgnoreCase("direction")) {
            isDirection = false;
            directionList.add(direction);
        }

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	}



}
