package org.service.xml;

import org.model.Direction;
import org.model.Stop;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PredictionHandler extends DefaultHandler {

    private List<Stop> stopList = null;
    private Stop stop = null;
    private List<Direction> directionList = null;
    private Direction direction = null;
    private Prediction prediction = null;
    private boolean isDirection = false;


    public PredictionHandler() {
        this.stopList = new ArrayList<>();
        this.directionList = new ArrayList<>();
    }

    private boolean isPrediction = false;


    public List<Stop> getStopList() {
        return stopList;
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }

    public class Prediction {
        public String agencyTitle;
        public String routeTitle;
        public String routeTag;
        public String stopTitle;
        public String stopTag;
        public Direction direction;
        public Date epochTime;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

//        <predictions agencyTitle="Toronto TTC" routeTitle="60-Steeles West" routeTag="60" stopTitle="Steeles Ave West At Martin Grove Rd" stopTag="844">
//          <direction title="East - 60 Steeles East towards Finch Station">
//              <prediction epochTime="1459047708899" seconds="860" minutes="14" isDeparture="false" affectedByLayover="true" branch="60" dirTag="60_0_60B" vehicle="1342" block="60_10_100" tripTag="30560746"/>
//              <prediction epochTime="1459048908899" seconds="2060" minutes="34" isDeparture="false" affectedByLayover="true" branch="60" dirTag="60_0_60B" vehicle="1350" block="60_5_50" tripTag="30560747"/>
//              <prediction epochTime="1459050108899" seconds="3260" minutes="54" isDeparture="false" affectedByLayover="true" branch="60" dirTag="60_0_60B" vehicle="1381" block="60_12_120" tripTag="30560748"/>
//          </direction>
//        </predictions>

        if ("predictions".equalsIgnoreCase(qName)) {
            prediction = new Prediction();
            prediction.agencyTitle = attributes.getValue("agencyTitle");
            prediction.routeTitle = attributes.getValue("routeTitle");
            prediction.routeTag = attributes.getValue("routeTag");
            prediction.stopTitle = attributes.getValue("stopTitle");
            prediction.stopTag = attributes.getValue("stopTag");
        }

        if ("direction".equalsIgnoreCase(qName)) {
            direction = new Direction();
            direction.title = attributes.getValue("title");
            isDirection = true;
        }

        if ("prediction".equalsIgnoreCase(qName)) {
            prediction.epochTime = new Date(Long.parseLong(attributes.getValue("epochTime")));
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("stop")) {
            if (!isPrediction) {
                stopList.add(stop);
            }


        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {

    }


}
