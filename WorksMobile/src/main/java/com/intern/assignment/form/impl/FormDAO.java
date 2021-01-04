package com.intern.assignment.form.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intern.assignment.form.FormVO;

@Repository
public class FormDAO{
	
	@Autowired
	private SqlSession mybatis;
	
	public void insertForm(FormVO vo) {
		mybatis.insert("FormDAO.insertForm", vo);
//		mybatis.commit();
	}
	
	public void updateForm(FormVO vo) {
		mybatis.update("FormDAO.updateForm", vo);
//		mybatis.commit();
	}
	
	public void deleteForm(FormVO vo) {
		mybatis.delete("FormDAO.deleteForm", vo);
//		mybatis.commit();
	}
	
	public FormVO getForm(FormVO vo) {
		return (FormVO) mybatis.selectOne("FormDAO.getForm", vo);
	}
	
	public List<FormVO> getFormList(FormVO vo){
		return mybatis.selectList("FormDAO.getFormList", vo);
	}
	
	
	/*
	 * 
	 * 
	//sql
	private final String FORM_INSERT = "insert into form(userIdx, title) values(?, ?)";
	private final String FORM_UPDATE = "update form set userIdx=?, title=? where formIdx=?";
	private final String FORM_DELETE = "delete form where formIdx=?";
	private final String FORM_GET_LIST = "select * from form order by formIdx desc";
	private final String FORM_GET = "select * from form where formIdx = ?";
	private final String USER_FORM_INSERT = "insert into user_form(userIdx, formIdx) values(?,?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
//	BasicDataSource dataSource;
	
	//methods
	//create form
	public void insertForm(FormVO vo) {
		System.out.println("===> Spring JDBC insertForm()!!!");
		jdbcTemplate.update(FORM_INSERT, vo.getUserIdx(), vo.getTitle());
	}
	
	//update form
	public void updateForm(FormVO vo) {
		System.out.println("===> Spring JDBC updateForm()!!!");
		jdbcTemplate.update(FORM_UPDATE, vo.getUserIdx(), vo.getTitle(), vo.getFormIdx());
	}
	
	//delete form
	public void deleteForm(FormVO vo) {
		System.out.println("===> Spring JDBC deleteForm()!!!");
		jdbcTemplate.update(FORM_DELETE, vo.getFormIdx());
	}
	
	//read specific form
	public FormVO getForm(FormVO vo) {
		System.out.println("===> Spring JDBC getForm()!!!");
		Object[] args = {vo.getFormIdx()};
		
		return jdbcTemplate.queryForObject(FORM_GET, args, new FormRowMapper());
	}
		
	//read all forms list
	public List<FormVO> getFormList(FormVO vo) {
		System.out.println("===> Spring JDBC getFormList()!!!");
		
		return jdbcTemplate.query(FORM_GET_LIST, new FormRowMapper());		
	}
	
	
	*/
	
}


/*
class FormRowMapper implements RowMapper<FormVO>{
	public FormVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		FormVO form = new FormVO();
		form.setFormIdx(rs.getInt("FORMIDX"));
		form.setUserIdx(rs.getInt("USERIDX"));
		form.setTitle(rs.getString("TITLE"));
		form.setCreatedAt(rs.getDate("CREATEDAT"));
		
		return form;
	}
}

*/