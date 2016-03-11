package org.service.xml;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import org.model.Agent;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AgentHandler extends DefaultHandler {
	
	private List<Agent> agentList = null;
    private Agent agent = null;
 

    public List<Agent> getAgentList() {
        return agentList;
    }
 


	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


		//e.g. <agency tag="art" title="Asheville Redefines Transit" regionTitle="North Carolina"/>
		if (qName.equalsIgnoreCase("agency")) {
            String tag = attributes.getValue("tag");
            String title = attributes.getValue("title");
            String regionTitle = attributes.getValue("regionTitle");
            agent = new Agent();
            agent.setTag(tag)
            	.setTitle(title)
            	.setRegionTitle(regionTitle);
            //initialize list
            if (agentList == null)
                agentList = new ArrayList<>();
        }

	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("agency")) 
            agentList.add(agent);
	}

	public void characters(char ch[], int start, int length) throws SAXException {

	}

}
