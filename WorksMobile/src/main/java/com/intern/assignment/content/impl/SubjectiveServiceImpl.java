package com.intern.assignment.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.assignment.content.SubjectiveService;
import com.intern.assignment.content.SubjectiveVO;

@Service("subjectiveService")
public class SubjectiveServiceImpl implements SubjectiveService{
	
	@Autowired
	private SubjectiveDAO subjectiveDAO;

	public void insertSubjective(SubjectiveVO vo) {
		subjectiveDAO.insertSubjective(vo);
	}
	
	public void updateSubjective(SubjectiveVO vo) {
		subjectiveDAO.updateSubjective(vo);
	}
	
	public void deleteSubjective(SubjectiveVO vo) {
		subjectiveDAO.deleteSubjective(vo);
	}
	
//	public ContentVO getContent(ContentVO vo) {
//		return contentDAO.getContent(vo);
//	}
	
//	public List<ContentVO> getContentList(ContentVO vo) {
//		return contentDAO.getContentList(vo);
//	}

}
