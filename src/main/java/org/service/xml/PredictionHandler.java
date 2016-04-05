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
        this.directionList = new ArrayList<>();
    }

    private boolean isPrediction = false;


    public List<Stop> getStopList() {
        return stopList;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }


    public Prediction getPrediction() {
        return prediction;
    }

    public class Prediction {
        public String agencyTitle;
        public String routeTitle;
        public String routeTag;
        public String stopTitle;
        public String stopTag;
        public Date epochTime;
        public String seconds;
        public String minutes;
        public String isDeparture;

        @Override
        public String toString() {
            return "Prediction{" +
                    "agencyTitle='" + agencyTitle + '\'' +
                    ", routeTitle='" + routeTitle + '\'' +
                    ", routeTag='" + routeTag + '\'' +
                    ", stopTitle='" + stopTitle + '\'' +
                    ", stopTag='" + stopTag + '\'' +
                    ", epochTime=" + epochTime +
                    ", seconds='" + seconds + '\'' +
                    ", minutes='" + minutes + '\'' +
                    ", isDeparture='" + isDeparture + '\'' +
                    '}';
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

/*
        <body copyright="All data copyright Toronto Transit Commission 2016.">
            <predictions agencyTitle="Toronto Transit Commission" routeTitle="60-Steeles West" routeTag="60" stopTitle="Steeles Ave West At Turbine Dr" stopTag="4100">
              <direction title="East - 60 Steeles East towards Finch Station">
                <prediction epochTime="1459728723750" seconds="179" minutes="2" isDeparture="false" branch="60" dirTag="60_0_60B" vehicle="1319" block="60_8_80" tripTag="30847607"/>
                <prediction epochTime="1459729800372" seconds="1256" minutes="20" isDeparture="false" affectedByLayover="true" branch="60" dirTag="60_0_60B" vehicle="8536" block="60_15_152" tripTag="30847608"/>
              </direction>
            </predictions>
        </body>
*/
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
            prediction.seconds = attributes.getValue("seconds");
            prediction.minutes = attributes.getValue("minutes");
            prediction.isDeparture = attributes.getValue("isDeparture");
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("direction")) {
            if (!isDirection) {
                direction.predictionList.add(prediction);
                directionList.add(direction);
            }
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {

    }


}
