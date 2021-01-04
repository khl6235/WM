package com.intern.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.assignment.content.ObjectiveService;
import com.intern.assignment.content.ObjectiveVO;

@Controller
public class ObjectiveController {
	
	@Autowired
	private ObjectiveService objectiveService;
	
	@RequestMapping(value="/insertObjective.do")
	public String insertObjective(ObjectiveVO vo) {
		objectiveService.insertObjective(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/updateObjective.do")
	public String updateObjective(ObjectiveVO vo) {
		objectiveService.updateObjective(vo);
		return "getFormList.do";
	}
	
	@RequestMapping("/deleteObjective.do")
	public String deleteObjective(ObjectiveVO vo) {
		objectiveService.deleteObjective(vo);
		return "getFormList.do";
	}

}
