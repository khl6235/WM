package com.intern.assignment.form.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.assignment.form.FormService;
import com.intern.assignment.form.FormVO;

@Service
public class FormServiceImpl implements FormService{
	@Autowired
	private FormDAO formDAO;

	public void insertForm(FormVO vo) {
		formDAO.insertForm(vo);
	}
	
	public void updateForm(FormVO vo) {
		formDAO.updateForm(vo);
	}
	
	public void deleteForm(FormVO vo) {
		formDAO.deleteForm(vo);
	}
	
	public FormVO getForm(FormVO vo) {
		return formDAO.getForm(vo);
	}
	
	public List<FormVO> getFormList(FormVO vo) {
		return formDAO.getFormList(vo);
	}
}
