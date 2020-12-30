package com.intern.assignment.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.assignment.content.ContentService;
import com.intern.assignment.content.ContentVO;

@Service("contentService")
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentDAO contentDAO;

	public void insertContent(ContentVO vo) {
		contentDAO.insertContent(vo);
	}
	
	public void updateContent(ContentVO vo) {
		contentDAO.updateContent(vo);
	}
	
	public void deleteContent(ContentVO vo) {
		contentDAO.deleteContent(vo);
	}
	
//	public ContentVO getContent(ContentVO vo) {
//		return contentDAO.getContent(vo);
//	}
	
	public List<ContentVO> getContentList(ContentVO vo) {
		return contentDAO.getContentList(vo);
	}
}
