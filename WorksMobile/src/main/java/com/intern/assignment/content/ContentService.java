package com.intern.assignment.content;

import java.util.List;


public interface ContentService {
	
	public void insertContent(ContentVO vo);
	public void updateContent(ContentVO vo);
	public void deleteContent(ContentVO vo);
//	public ContentVO getContent(ContentVO vo);
	public List<ContentVO> getContentList(ContentVO vo);

}
