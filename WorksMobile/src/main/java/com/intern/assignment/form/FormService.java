package com.intern.assignment.form;

import java.util.List;

public interface FormService {
	
	public void insertForm(FormVO vo);
	public void updateForm(FormVO vo);
	public void deleteForm(FormVO vo);
	public FormVO getForm(FormVO vo);
	public List<FormVO> getFormList(FormVO vo);

}
