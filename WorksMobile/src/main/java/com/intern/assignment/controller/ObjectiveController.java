package com.intern.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intern.assignment.content.ObjectiveService;

@Controller
public class ObjectiveController {
	
	@Autowired
	private ObjectiveService objectiveService;
	
	

}
