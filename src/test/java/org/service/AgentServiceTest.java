package org.service;

import org.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Direction;
import org.model.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sonic on 3/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class AgentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AgentServiceTest.class);

    @Autowired
    private NextBusService nextBusService;

    @Test
    public void getDirectionTest () throws Exception {
      //  List<Direction> directionList = nextBusService.getRouteDirectList("ttc", "60");
    }

    @Test
    public void getStopTest () throws Exception {
        //List <Stop> stopList = nextBusService.getStopList("ttc", "60");
    }

    @Test
    public void getPredictionTest () throws Exception {
        logger.info("START");
        List <Stop> stopList = nextBusService.getRoutePredictionList("ttc", "60", "60_0_60D");

        for (Stop stop : stopList) {
            logger.info(stop.toString());
        }

    }


}
