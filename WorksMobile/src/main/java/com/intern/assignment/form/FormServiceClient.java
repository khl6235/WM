package com.intern.assignment.form;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FormServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. run Spring container
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. lookup FormServiceImpl object from container
		FormService formService = (FormService) container.getBean("formService");
		
		//3. test insert form
		FormVO vo = new FormVO();
		vo.setTitle("test title~~~");
		vo.setUserIdx(2);
		formService.insertForm(vo);
		
		//4. test get form list
		List<FormVO> formList = formService.getFormList(vo);
		for(FormVO form : formList) {
			System.out.println("---> "+ form.toString());
		}
		
		//5. terminate
		container.close();

	}

}
