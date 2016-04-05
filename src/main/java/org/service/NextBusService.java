package org.service;

import org.model.Agent;
import org.model.Direction;
import org.model.Route;
import org.model.Stop;
import org.service.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NextBusService {

	private static final Logger logger = LoggerFactory.getLogger(NextBusService.class);

	@Autowired
	NextBusAdapter nextBusAdapter;

	private final String agentURL = "http://webservices.nextbus.com/service/publicXMLFeed?command=agencyList";
	private String routeListURL = "http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=<agencyTag>";
	private String stopListURL = "http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=<agencyTag>&r=<routeTag>";
//	private String preditionURL = "http://webservices.nextbus.com/service/publicXMLFeed?command=" +
//			"predictions&a=<agencyTag>&" +
//			"stopId=<stopId>&" +
//			"routeTag=<routeTag>&" +
//			"useShortTitles=true";

	private String predictionURL = "http://webservices.nextbus.com/service/publicXMLFeed?command=predictions" +
			"&a=<agencyTag>" +
			"&r=<routeTag>" +
			"&s=<stopTag>" +
			"&useShortTitles=true";
	
	public NextBusService() {

	}

	public List <Agent> getAgentList() {
		List<Agent> agentList = new ArrayList <Agent>();

		AgentHandler handler = new AgentHandler();
		nextBusAdapter.parse(handler, agentURL);
		agentList = handler.getAgentList();

		for (Agent agent : agentList)
			logger.info(agent.toString());

		return agentList;
	}

	public List <Route> getRouteList (String agencyTag) {
		List <Route> routeList = new ArrayList<Route>();

		RouteHandler handler = new RouteHandler();

		nextBusAdapter.parse(handler, routeListURL.replace("<agencyTag>", agencyTag));
		routeList = handler.getRouteList();

		for (Route route : routeList)
			logger.info(route.toString());

		return routeList;
	}

	public List <Stop> getStopList (String agencyTag, String routeTag) {
		List <Stop> stopList = new ArrayList<Stop>();
		List <Direction> directionList;

		StopHandler handler = new StopHandler();

		nextBusAdapter.parse(handler, stopListURL.replace("<agencyTag>", agencyTag).replace("<routeTag>", routeTag));

		stopList = handler.getStopList();

		directionList = handler.getDirectionList();

		for (Stop stop : stopList)
			logger.info(stop.toString());

		for (Direction direction : directionList) {
			logger.info(direction.toString());
		}

		return stopList;
	}

	public List <Direction> getRouteDirectList (String agencyTag, String routeTag) {

		List <Stop> stopList = new ArrayList<Stop>();
		List <Direction> directionList;

		StopHandler handler = new StopHandler();

		nextBusAdapter.parse(handler, stopListURL.replace("<agencyTag>", agencyTag).replace("<routeTag>", routeTag));

		directionList = handler.getDirectionList();
		stopList = handler.getStopList();


		for (Direction direction : directionList) {
			for (Stop stop : stopList){
				if (direction.directionStopList.contains(stop.getTag())) {
					direction.stopList.add(stop);
				}
			}
		}

		for (Direction direction : directionList) {
			//logger.info(direction.toString());
			for (Stop stop : direction.stopList){
				//logger.info(stop.toString());
			}
		}

		return directionList;

	}


	public List <Stop> getRoutePredictionList (String agencyTag, String routeTag, String directionTag) {

		PredictionHandler predictionHandler = new PredictionHandler();
		List <Direction> directionList = getRouteDirectList(agencyTag, routeTag);
		List<Stop> stopList = new ArrayList<>();

		logger.info("Direction list size: " + directionList.size());

		for (Direction direction : directionList) {

			if (direction.tag.equalsIgnoreCase(directionTag)) {
				stopList = direction.stopList;
				logger.info("Direction stop list size: " + stopList.size());
				for (Stop stop : stopList) {
					//logger.info(stop.toString());
					nextBusAdapter.parse(predictionHandler, predictionURL.replace("<agencyTag>", agencyTag)
							.replace("<stopTag>", stop.getTag())
							.replace("<routeTag>", routeTag));

					//logger.info(predictionHandler.getPrediction().toString());
					stop.setPrediction (predictionHandler.getPrediction());
				}
			}
		}
		return stopList;
	}



}