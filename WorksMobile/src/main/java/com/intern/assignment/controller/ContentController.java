package com.intern.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.assignment.content.ContentService;
import com.intern.assignment.content.ContentVO;

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value="/insertContent.do")
	public String insertContent(ContentVO vo) {
		contentService.insertContent(vo);
		return "";
	}
	
	@RequestMapping("/updateContent.do")
	public String updateContent(ContentVO vo) {
		contentService.updateContent(vo);
		return "";
	}
	
	@RequestMapping("/deleteContent.do")
	public String deleteContent(ContentVO vo) {
		contentService.deleteContent(vo);
		return "";
	}
	
//	@RequestMapping("/getContent.do")
//	public String getContent(ContentVO vo, Model model) {
//		model.addAttribute("content", contentService.getContent(vo));
//		return "";
//	}
	
	@RequestMapping("/getContentList.do")
	public String getContentList(ContentVO vo, Model model) {
		model.addAttribute("contentList", contentService.getContentList(vo));
		return "";
	}

}
