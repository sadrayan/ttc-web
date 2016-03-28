package org.service.xml;

import org.model.Route;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RouteHandler extends DefaultHandler {
	
	private List<Route> routeList = null;
    private Route route = null;
 

    public List<Route> getRouteList() {
        return routeList;
    }
 


	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


		//e.g    <route tag="5" title="5-Avenue Rd"/>
		if (qName.equalsIgnoreCase("route")) {
            String tag = attributes.getValue("tag");
            String title = attributes.getValue("title");
            route = new Route();
            route.setTag(tag)
            	.setTitle(title);

            //initialize list
            if (routeList == null)
                routeList = new ArrayList<>();
        }

	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("route"))
            routeList.add(route);
	}

	public void characters(char ch[], int start, int length) throws SAXException {

	}

}
