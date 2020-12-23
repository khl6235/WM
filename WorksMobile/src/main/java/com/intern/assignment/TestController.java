package com.intern.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.assignment.form.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService service;
	
	@RequestMapping("/selectNow.do")
	public void selectNow() {
		String result = service.selectNow();
		System.out.println(result);
	}
}
