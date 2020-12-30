package com.intern.assignment.content.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intern.assignment.content.ContentVO;

@Repository
public class ContentDAO {
	
	@Autowired
	private SqlSession mybatis;
	
	public void insertContent(ContentVO vo) {
		mybatis.insert("ContentDAO.insertContent", vo);
		mybatis.commit();
	}
	
	public void updateContent(ContentVO vo) {
		mybatis.update("ContentDAO.updateContent", vo);
		mybatis.commit();
	}
	
	public void deleteContent(ContentVO vo) {
		mybatis.delete("ContentDAO.deleteContent", vo);
		mybatis.commit();
	}
	
//	public ContentVO getContent(ContentVO vo) {
//		return (ContentVO) mybatis.selectOne("ContentDAO.getContent", vo);
//	}
	
	public List<ContentVO> getContentList(ContentVO vo){
		return mybatis.selectList("ContentDAO.getContentList", vo);
	}

}
