package org.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping (value = "/")
	String index () {
		return "index";
	}

	@RequestMapping (value = "/test")
	String test () {
		return "test";
	}

}
