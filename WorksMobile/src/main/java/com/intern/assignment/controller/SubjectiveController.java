package com.intern.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intern.assignment.content.SubjectiveService;

@Controller
public class SubjectiveController {
	
	@Autowired
	private SubjectiveService subjectiveService;

}
