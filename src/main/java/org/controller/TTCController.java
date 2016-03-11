package org.controller;

import java.util.List;

import org.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TTCController {
	
	@Autowired
	private AgentService agentService;
	
	
	@RequestMapping (value = "/agent")
	String index (Model model) {
		List agentList = agentService.agentList();
        model.addAttribute("agentList", agentList);	
		return "index";
	}

	@RequestMapping (value = "/config")
	String ch (Model model) {
		List agentList = agentService.agentList();
		model.addAttribute("agentList", agentList);
		return "index";
	}


}
