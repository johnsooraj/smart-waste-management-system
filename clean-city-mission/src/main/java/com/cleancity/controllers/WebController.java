package com.cleancity.controllers;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}

	@RequestMapping("/map3")
	public String indexPage2(@PathParam("start") Object start, @PathParam("end") Object end) {
		return "locations";
	}
}
