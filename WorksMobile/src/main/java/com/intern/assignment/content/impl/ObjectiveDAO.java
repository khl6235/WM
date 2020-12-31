package com.intern.assignment.content.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intern.assignment.content.ObjectiveVO;

@Repository
public class ObjectiveDAO {

	@Autowired
	private SqlSession mybatis;
	
	public void insertObjective(ObjectiveVO vo) {
		mybatis.insert("ObjectiveDAO.insertObjective", vo);
		mybatis.commit();
	}
	
	public void updateObjective(ObjectiveVO vo) {
		mybatis.update("ObjectiveDAO.updateObjective", vo);
		mybatis.commit();
	}
	
	public void deleteObjective(ObjectiveVO vo) {
		mybatis.delete("ObjectiveDAO.deleteObjective", vo);
		mybatis.commit();
	}
	
//	public ContentVO getContent(ContentVO vo) {
//		return (ContentVO) mybatis.selectOne("ContentDAO.getContent", vo);
//	}
//	
//	public List<ContentVO> getContentList(ContentVO vo){
//		return mybatis.selectList("ContentDAO.getContentList", vo);
//	}

}
