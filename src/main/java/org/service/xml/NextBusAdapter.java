package org.service.xml;

import org.model.Agent;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

/**
 * Created by sonic on 3/10/16.
 */
@Component
public class NextBusAdapter {


    private SAXParserFactory saxParserFactory;
    public NextBusAdapter() {
        saxParserFactory = SAXParserFactory.newInstance();
    }

    public DefaultHandler parse (DefaultHandler handler, String url) {

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            saxParser.parse(new InputSource(new URL(url).openStream()), handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return handler;
    }
}
