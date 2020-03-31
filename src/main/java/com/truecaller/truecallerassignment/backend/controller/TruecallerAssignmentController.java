package com.truecaller.truecallerassignment.backend.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.truecaller.truecallerassignment.backend.entities.Tile;

@RestController
public class TruecallerAssignmentController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/hello-world")
	@ResponseBody
	public Tile sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
		return new Tile(4, 5, false);
	}
}