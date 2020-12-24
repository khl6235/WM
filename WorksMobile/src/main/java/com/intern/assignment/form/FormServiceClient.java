package com.intern.assignment.form;

import java.util.List;

import com.intern.assignment.form.impl.FormDAO;

public class FormServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FormDAO formDAO = new FormDAO();
		
		//1. run Spring container
//		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. lookup FormServiceImpl object from container
//		FormService formService = (FormService) container.getBean("formService");
		
		//3. test insert form
		FormVO vo = new FormVO();
		vo.setTitle("mybatis 제목");
		vo.setUserIdx(2);
//		formService.insertForm(vo);
		formDAO.insertForm(vo);
		
		
		//4. test get form list
//		List<FormVO> formList = formService.getFormList(vo);
		List<FormVO> formList = formDAO.getFormList(vo);
		for(FormVO form : formList) {
			System.out.println("---> "+ form.toString());
		}
		
		//5. terminate
//		container.close();

	}

}
