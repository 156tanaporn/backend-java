package com.example.demo.controller;

import javax.swing.Spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public String greeting () {
		
		return "Hello spring boot";
	}

}
