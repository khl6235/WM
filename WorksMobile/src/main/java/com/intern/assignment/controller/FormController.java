package com.intern.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intern.assignment.form.FormService;
import com.intern.assignment.form.FormVO;

@CrossOrigin(origins="*")
@Controller
//@RequestMapping("forms")
public class FormController {

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
	
//	@RequestMapping(value="/getFormList.do", method= {RequestMethod.GET, RequestMethod.POST})
	@RequestMapping(value="/forms", method = RequestMethod.GET)
//	public List<FormVO> getFormList(FormVO vo, Model model) {
	public String getFormList(FormVO vo) {
//		model.addAttribute("formList", formService.getFormList(vo));
		System.out.println(formService.getFormList(vo));
//		return formService.getFormList(vo);
		return "";
	}
	
}
