package org.controller;

import org.model.Agent;
import org.model.Direction;
import org.model.Route;
import org.model.Stop;
import org.service.NextBusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TTCController {

	@Autowired
	private NextBusService nextBusService;

	private static final Logger logger = LoggerFactory.getLogger(TTCController.class);

	@RequestMapping (value = "/agentList")
	List<Agent> getAgentList () {
		logger.info("API-AGENT-START: " + System.currentTimeMillis());

		List<Agent> agentList = nextBusService.getAgentList();

		for (Agent agent : agentList)
			System.out.println(agent.toString());

		logger.info("API-AGENT-END: " + System.currentTimeMillis());
		return agentList;
	}

	@RequestMapping (value = "/routeList")
	List  <Route>  getRouteList (@RequestParam(value="agentTag") String agentTag) {
		List  <Route> routeList = nextBusService.getRouteList(agentTag);
		return routeList;
	}

	@RequestMapping (value = "/stopList")
	List <Stop> getStopList (@RequestParam(value="agentTag") String agentTag,
							 @RequestParam(value="routeTag") String routeTag) {
		List <Stop> stopList = nextBusService.getStopList(agentTag, routeTag);
		return stopList;
	}

    @RequestMapping (value = "/directionStopList")
	List <Direction> getDirectionStopist (@RequestParam(value="agentTag") String agentTag,
                                          @RequestParam(value="routeTag") String routeTag) {
		List <Direction> directionList = nextBusService.getRouteDirectList(agentTag, routeTag);
		return directionList;
	}


	@RequestMapping (value = "/test")
	List  <Route>  test () {

		List <Agent> agentList = nextBusService.getAgentList();
		List <Route> routeList = nextBusService.getRouteList("60");
		List <Stop> stopList = nextBusService.getStopList("ttc", "60");

		return routeList;
	}






}
