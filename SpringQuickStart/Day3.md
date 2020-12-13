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
  
<br>

## 3. Model 2 아키텍처로 게시판 개발

#### Model 2 아키텍처 구조

  - 시스템의 규모가 크고 기능이 복잡한 엔터프라이즈 시스템 개발하기에는 Model 2가 더 적합
  
    - 이유 : 자바 로직과 화면 디자인이 통합되어 유지보수가 어려움
    
  - Model 2 아키텍쳐 = **MVC 아키텍처**
  
    - **Controller**의 등장. Controller는 서블릿 클래스를 중심으로 구현한다.
    - Model 1에서 JSP가 담당했던 Controller 로직이 별도의 Controller 기능의 서블릿으로 옮겨짐.
    - => JSP 파일에 있는 자바 코드만 Controller로 옮기면 된다. JSP에는 View 부분만 남고, 자바 개발자는 Controller, Model만 관리하면 됨.
    
      | 기능 | 구성 요소 | 개발 주체 |
      | --- | --- | --- |
      | Model | VO, DAO 클래스 | 자바 개발자 |
      | View | JSP 페이지 | 웹 디자이너 |
      | Controller | Servlet 클래스 | 자바 개발자 또는 MVC 프레임워크 |
    
    
#### Controller 구현하기

  - 서블릿 생성 및 등록. DispatcherServlet 생성.
  - Controller 서블릿 구현
  
    - GET 방식 요청 처리하는 doGet(), POST 방식 요청 처리하는 doPost() 메소드 재정의. 어떤 방식이든 process() 메소드를 통해 클라이언트의 요청을 처리한다.
    - process()에서는 추출한 path 에 따라 분기 처리 로직이 실행된다.
    
    
<br>

## 4. MVC 프레임워크 개발

#### MVC 프레임워크 구조

  - 위 실습처럼 하나의 서블릿으로 Controller를 구현하면, 클라이언트의 모든 요청을 하나의 서블릿이 처리. -> 개발/유지보수 어려움
  
    | 클래스 | 기능 |
    | --- | --- |
    | DispatcherServlet | 유일한 서블릿 클래스. 모든 클라이언트의 요청을 가장 먼저 처리하는 Front Controller |
    | HandlerMapping | 클라이언트의 요청을 처리할 Controller 매핑 |
    | Controller | 실질적인 클라이언트의 요청 처리 |
    | ViewResolver | Controller가 리턴한 View 이름으로 실행될 JSP 경로 완성 |
    
    
#### MVC 프레임워크 구현

  1. Controller 인터페이스 작성<br>
    - 클라이언트 요청을 DispatcherServlet이 처리하는 일은 거의 없으며, 실질적인 요청 처리는 Controller에서.<br>
    - **모든 Controller들을 같은 타입으로 관리하기 위한 최상위 인터페이스**<br>
  
  2. LoginController 구현<br>
    - 로그인 처리 기능의 마지막은 이동할 화면을 리다이렉트하지 않고 리턴.<br>
    - handleRequest() 메소드가 확장자 없는 문자열을 리턴하면 자동으로 '.jsp'가 붙어서 처리됨.<br>
    
  3. HandlerMapping 클래스 작성<br>
    - 모든 Controller 객체들을 저장하고 있다가, 클라이언트의 요청이 들어오면 **요청을 처리할 특정 Controller를 검색**해줌.<br>
    - DispatcherServlet이 사용하는 객체이다.<br>
    - **Map 타입의 컬렉션**을 멤버변수로 가지고 있으면서 필요한 모든 Controller 객체들을 등록하고 관리.<br>
    - HashMap에 등록된 정보로 Controller 객체가 어떤 '.do' 요청과 매핑되는지 확인.<br>
    
  4. ViewResolver 클래스 작성<br>
    - Controller가 리턴한 View 이름에 접두사/접미사 결합해서 최종적으로 실행될 View 경로와 파일명 완성.<br>
    
  5. DispatcherServlet 수정<br>
    - init() 메소드 재정의. DispatcherServlet이 사용할 HandlerMapping과 ViewResolver 객체를 초기화.<br>
    - 클라이언트의 요청 path에 해당하는 Controller를 검색하기 위해 **HandlerMapping 객체의 getController() 호출**<br>
    - 검색된 Controller의 **handleRequest() 호출하여 요청에 해당하는 로직 처리**하고, **이동할 화면 정보를 리턴**<br>
    - 리턴받은 view 이름을 이용해 해당 화면으로 이동<br>
    
    
#### MVC 프레임워크 적용

  (실습)
  
#### EL/JSTL 이용한 JSP 화면 처리

  - JSP 파일에서 완전히 자바 코드를 제거하고 싶다면, JSP에 제공하는 EL과 JSTL을 이용
  
    > **EL(Expression Language)**
    > 
    > 기존의 표현식을 대체하는 표현 언어.<br>
    > <%= session.getAttribute("userName") %> -> ${userName}


    > **JSTL(JSP Standard Tag Library)**
    > 
    > JSP에서 사용해야 하는 if, for, switch 등의 자바 코드들을 태그 형태로 사용하도록 지원<br>
  
 
 
<br>

## 5. Spring MVC 구조

