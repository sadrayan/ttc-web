package org.controller;

import org.model.Agent;
import org.model.Route;
import org.service.NextBusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {


	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


	@RequestMapping (value = "/")
	String index () {
		return "index";
	}



	@Autowired
	private NextBusService nextBusService;

//	@ModelAttribute("agentList")
//	@RequestMapping (value = "/agent")
//	ModelAndView agentList () {
//		logger.info("API-AGENT-START: " + System.currentTimeMillis());
//
//		List<Agent> agentList = nextBusService.getAgentList();
//
//		for (Agent agent : agentList)
//			System.out.println(agent.toString());
//
//		ModelAndView mav = new ModelAndView("index");
//		mav.addObject("agentList", agentList);
//
//		logger.info("API-AGENT-END: " + System.currentTimeMillis());
//		return mav;
//	}

}
