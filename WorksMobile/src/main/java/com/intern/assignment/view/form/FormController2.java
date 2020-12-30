package com.intern.assignment.view.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.assignment.form.FormService;
import com.intern.assignment.form.FormVO;

//@Controller
public class FormController2 {
	/*
	@Autowired
	private FormService formService;
	
	@RequestMapping(value="/insertForm.do")
	public String insertForm(FormVO vo) {
		formService.insertForm(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/updateForm.do")
	public String updateForm(FormVO vo) {
		formService.updateForm(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/deleteForm.do")
	public String deleteForm(FormVO vo) {
		formService.deleteForm(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/getForm.do")
	public String getForm(FormVO vo, Model model) {
		model.addAttribute("form", formService.getForm(vo));
		return "getForm.jsp";
	}
	
	@RequestMapping("/getFormList.do")
	public String getFormList(FormVO vo, Model model) {
		model.addAttribute("formList", formService.getFormList(vo));
		return "getFormList.jsp";
	}
	*/

}
