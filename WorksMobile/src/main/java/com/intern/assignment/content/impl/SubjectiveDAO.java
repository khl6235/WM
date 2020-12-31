package com.intern.assignment.content.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intern.assignment.content.SubjectiveVO;

@Repository
public class SubjectiveDAO {
	
	@Autowired
	private SqlSession mybatis;
	
	public void insertSubjective(SubjectiveVO vo) {
		mybatis.insert("SubjectiveDAO.insertSubjective", vo);
		mybatis.commit();
	}
	
	public void updateSubjective(SubjectiveVO vo) {
		mybatis.update("SubjectiveDAO.updateSubjective", vo);
		mybatis.commit();
	}
	
	public void deleteSubjective(SubjectiveVO vo) {
		mybatis.delete("SubjectiveDAO.deleteSubjective", vo);
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
