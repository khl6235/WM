package com.intern.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.assignment.content.SubjectiveService;
import com.intern.assignment.content.SubjectiveVO;

@Controller
public class SubjectiveController {
	
	@Autowired
	private SubjectiveService subjectiveService;

	@RequestMapping(value="/insertSubjective.do")
	public String insertSubjective(SubjectiveVO vo) {
		subjectiveService.insertSubjective(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/updateSubjective.do")
	public String updateSubjective(SubjectiveVO vo) {
		subjectiveService.updateSubjective(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/deleteSubjective.do")
	public String deleteSuObjective(SubjectiveVO vo) {
		subjectiveService.deleteSubjective(vo);
		return "getFormList.do";
	}
}
