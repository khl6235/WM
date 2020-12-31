package com.intern.assignment.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.assignment.content.ObjectiveVO;

@Service("objectiveService")
public class ObjectiveServiceImpl {
	
	@Autowired
	private ObjectiveDAO objectiveDAO;

	public void insertObjective(ObjectiveVO vo) {
		objectiveDAO.insertObjective(vo);
	}
	
	public void updateObjective(ObjectiveVO vo) {
		objectiveDAO.updateObjective(vo);
	}
	
	public void deleteObjective(ObjectiveVO vo) {
		objectiveDAO.deleteObjective(vo);
	}
	
//	public ContentVO getContent(ContentVO vo) {
//		return contentDAO.getContent(vo);
//	}
	
//	public List<ContentVO> getContentList(ContentVO vo) {
//		return contentDAO.getContentList(vo);
//	}

}
