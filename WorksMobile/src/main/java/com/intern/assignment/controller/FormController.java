package com.intern.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.assignment.form.FormService;
import com.intern.assignment.form.FormVO;

@Controller
@RequestMapping("/form")
public class FormController {

	@Autowired
	private FormService formService;
	
	@RequestMapping("/list")
	public String getFormList(FormVO vo) {
		for(FormVO form : formService.getFormList(vo)) {
			System.out.println("---> "+ form.toString());
		}
		
		return "getFormList";
	}
}
