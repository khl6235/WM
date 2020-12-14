package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		
		//1.Spring 컨테이너를 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring 컨테이너로부터 BoardServiceImpl 객체를 Lookup.
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		//3. 
		BoardVO vo = new BoardVO();
		vo.setTitle("�׽�Ʈ ����");
		vo.setWriter("�׽���");
		vo.setContent("�׽�Ʈ �����Դϴ�......");
		boardService.insertBoard(vo);
		
		//4. �� ��� �˻� ��� �׽�Ʈ
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for(BoardVO board : boardList) {
			System.out.println("---> "+board.toString());
		}
		
		//5. Spring �����̳� ����
		container.close();

	}

}
