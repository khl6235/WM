# Day3

## 1-2. Model 1 아키텍처로 게시판 개발

#### Model 1 아키텍처 구조

  - Model 1 아키텍처는 **JSP와 JavaBeans**만 사용하여 웹 개발.
  - **Model 기능의 JavaBeans**

    - 데이터베이스 연동에 사용되는 자바 객체들. (+ DB에서 검색한 데이터가 저장되는 자바 객체.)
  
  - **Controller와 View 기능을 모두 처리하는 JSP 파일**
  
    - Controller 기능은 JSP 파일에 작성된 사용자의 요청 처리와 관련된 자바 코드
    - 사용자 입력 정보 추출, DB 연동 처리, 화면 내비게이션 등
    - 다양한 마크업 사용. HTML, CSS. => View 기능 담당
  
  - JSP 파일에 코드가 섞여서 역할 구분이 명확하지 않고, 디버깅과 유지보수 어렵.
  - **Model 2, MVC(Model View Controller) 아키텍처**가 더 유용 (실습은 Model 1 -> Model 2)


#### 로그인 기능 구현

  - login.jsp 파일로 로그인 화면을 생성
  - 입력 정보를 받아 존재하는 사용자인지 인증하는 login_proc.jsp 생성.
  
  > 포워드(Forward)와 리다이렉트(Redirect) 차이 <br>
  > 
  > 포워드 : RequestDispatcher를이용해 응답으로 사용할 JSP 화면으로 넘겨서, 포워드된 화면이 클라이언트에 전송되는 방식. <br>
  >     한 번의 요청과 응답으로 처리되어 속도 빠르지만 URL이 바뀌지 않아 응답의 출처 확인 불가
  > 
  > 리다이렉트 : 요청된 JSP에서 일단 브라우저로 응답 메시지 보냈다가 다시 서버로 재요청하는 방식. <br>
  >     응답이 들어온 파일로 URL이 바뀌지만, 두 번의 요청과 응답으로 처리되어 속도는 포워드보다 느림
  
  
#### 글 목록 검색 기능 구현

  - getBoardList.jsp 파일을 구현해 사용자가 입력한 검색 관련 정보를 추출.
  - BoardVO, BoardDAO 객체를 이용해 BOARD 테이블에 저장된 게시글 목록을 검색한다. 결과로 얻은 List<BoardVO> 객체로 게시글 목록 화면 구성
  - 게시글 제목에 하이퍼링크 설정 가능. **? 추가하고 쿼리 문자열 정보** 넘겨주었음.
  
#### 글 상세 기능 구현

  - 사용자가 클릭한 게시글 조회하고 상세 화면 제공하는 getBoard.jsp 파일 작성.
  