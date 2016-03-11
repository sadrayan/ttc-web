package org.service;

import org.model.Agent;
import org.service.xml.AgentHandler;
import org.service.xml.NextBusAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentService {

	@Autowired
	NextBusAdapter nextBusAdapter;

	private final String agentURL = "http://webservices.nextbus.com/service/publicXMLFeed?command=agencyList";
	
	public AgentService() {

	}

	public List agentList() {
		List<Agent> agentList = new ArrayList();

		AgentHandler handler = new AgentHandler();
		nextBusAdapter.parse(handler, agentURL);
		agentList = handler.getAgentList();

		for (Agent agent : agentList)
			if (agent.getTag() == "ttc")
				System.out.println(agent);

		return agentList;
	}
}