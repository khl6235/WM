# Day4

## 1. 어노테이션 기반 MVC 개발

  스프링은 **어노테이션 기바 설정**을 제공함으로써 과도한 xml 설정으로 인한 문제를 해결. (xml 설정 최소화)

#### 어노테이션 관련 설정

  - 스프링 MVC 에서 어노테이션을 사용하려면, 먼저 \<beans\> 루트 엘리먼트에 context 네임스페이스를 추가.
  - HandlerMapping, Controller, ViewResolver 클래스에 대한 bean 등록을 모두 삭제하고 **\<context:component-scan\>** 엘리먼트로 대체.
  
#### @Controller 사용하기

  - 기존에는 스프링 컨테이너가 Controller 클래스 생성하게 하려면 전부 xml에 \<bean\> 등록해야 했다.
  - 어노테이션을 사용하면 클래스 선언부 위에 **@Controller** 붙이면 됨. -> **\<context:component-scan\>으로 스프링 컨테이너가 컨트롤러 객체들 자동 생성.**
  - 어노테이션을 사용하지 않으면,
  
    - 모든 컨트롤러 클래스는 반드시 스프링에서 제공하는 Controller 인터페이스를 구현, handleRequest() 메소드를 Override 해서 매번 호출해야 함.
    - POJO 스타일의 클래스가 아니다!
    
#### @RequestMapping 사용하기

  - HandlerMapping 설정을 대체.
  - `@RequestMapping(value="/insertBoard.do")` 이면 "/insertBoard.do"라는 요청이 클라이언트로부터 왔을 때 어노테이션이 붙은 insertBoard() 메소드를 매핑하겠다는 설정.
  - value 속성은 생략 가능
  
#### 클라이언트 요청 처리

  - 대부분 Controller는 사용자의 입력 저오를 추출하여 VO 객체에 저장. 비즈니스 컴포넌트의 메소드 호출 시 VO 객체를 인자로 전달.
  - 하나하나 getParameter()로 추출하는 것을 보완하기 위해 **Command 객체**를 이용.
  - Command 객체는 **Controller 메소드 매개변수로 받은 VO 객체**라고 보면 됨.
  
    ```java
    @Controller
    public class InsertBoardController{

      @RequestMapping(value="/insertBoard.do")
      public void insertBoard(BoardVO vo) {
        System.out.println("글 등록 처리");

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.insertBoard(vo);
      }

    }
    ```
    
    - insertBoard()의 매개변수로 사용자가 입력한 값을 매핑할 BoardVO 클래스 선언하면, 커테이너가 insertBoard() 실행할 때 Command 객체 생성해서 넘겨준다.
    - 사용자가 입력한 값들을 Command 객체에 세팅해서 넘겨주므로, **사용자 입력 정보 추출, VO 객체 생성, 값 설정을 모두 컨테이너가 자동으로 처리**.
  
  - 서블릿 객체는 서블릿 컨테이너가 생성. with service(), doGet(), doPost().
  - HttpServletRequest, HttpServletResponse 객체도 서블릿 컨테이너가 생성해서 넘겨준다.
  - Form 태그 안의 파라미터 이름과 Command 객체의 Setter 메소드 이름이 일치해야 함
  
<br>

## 2. 어노테이션으로 게시판 프로그램 구현하기

  (실습)
  
#### 요청 방식에 따른 처리

  - method 속성
  
    - @RequestMapping을 이용하면 마치 Servlet처럼 클라이언트의 요청 방식(GET/POST)에 따라 수행될 메소드를 다르게 설정 가능
    - index.jsp 파일 작성해서 기본 GET 방식을 통해 확인 가능. 로그인 버튼을 클릭하면 POST 방식 요청으로 실행.
    
  - @ModelAttribute 사용
  
    - Command 객체의 이름을 변경하기 위해 사용.
    
#### Servlet API 사용

  - 사용자 입력 정보 추출을 위해 HttpServletRequest 대신 Command 객체를 사용했음. 하지만 사용자 입력값 추출에만 HttpServletRequest가 사용된 것은 아님.
  - **HttpServletRequest 객체가 제공하는 다양한 메소드를 이용하여 Controller를 구현해야 할 때는 HttpServletRequest 객체를 매개변수로 받아야 함.**
  
#### Controller의 리턴타입

  - String 으로 리턴 : 완벽한 View 이름을 문자열로 리턴
  - ModelAndView 로 리턴 : 검색된 Model 데이터와 View 이름을 모두 저장하여 리턴
  - String 사용하는 것이 더 간결하고 일관성 있으므로 대부분 String으로 통일.
  
#### 기타 어노테이션

  - @RequestParam 사용하기
  
    - Command 객체 이용하면 클라이언트에서 넘겨준 요청 파라미터 정보를 받아낼 수 있음. 이를 위해서는 요청 파라미터와 매핑될 변수와 Setter 메소드가 Command 클래스에 선언되어야 함.
    - HTTP 요청 파라미터 정보를 추출할 수 있는 어노테이션.
    - `value` : 화면으로부터 전달될 파라미터 이름
    - `defaultValue` : 화면으로부터 전달될 파라미터 정보가 없을 때, 설정할 기본값
    - `required` : 파라미터의 생략 여부
    
  - @ModelAttribute 사용하기
  
    - Command 객체 이름 변경뿐 아니라, View(JSP)에서 사용할 데이터 설정에도 사용 가능
    - @ModelAttribute가 설정된 메소드는 @RequestMapping 어노테이션이 적용된 메소드보다 먼저 호출됨.
    - 이 결과로 리턴된 객체는 자동으로 Model에 저장됨. 이를 View 페이지에서 사용 가능.
    
  - @SessionAttributes 사용하기
  
    - null 업데이트 : 파라미터 정보가 전달되지 않았을 때 해당 컬럼이 null로 수정되는 것 방지.
    - null 업데이트 방지 : 전달 못받은 정보가 null이 되지 않도록 할 수 있음.
    - `@SessionAttributes("board")`는 Model에 "board"라는 이름으로 저장되는 데이터가 있다면 그 데이터를 **세션에도 자동으로 저장하라**는 설정.
    
      - 메소드가 호출되면 컨테이너는 우선 @ModelAttribute 설정을 해석해서 세션에 해당 이름의 데이터가 있는지 확인한다.
      - 있으면 해당 객체를 세션에서 꺼내서 매개변수로 선언된 vo 변수에 할당
      - 사용자가 입력한 파라미터값을 vo 객체에 할당.
      
<br>

## 3. 프레젠테이션 레이어와 비즈니스 레이어 통합

  서버에 요청 전송 -> DispatcherServlet -> Controller에게 요청 전달 -> 매개변수 통해 전달된 DAO 객체 이용해서 로직 처리 <br>
  **Controller의 모든 메소드가 사용자 요청을 처리할 때 DAO 객체를 직접 이용하고 있다**. => **비즈니스 컴포넌트**를 이용해야 함!
  
#### 비즈니스 컴포넌트 사용

  Controller는 비즈니스 컴포넌트 이용해서 사용자의 요청 처리해야 하며, 컴포넌트가 제공하는 Service 인터페이스를 이용해야 함.
  
  - DAO 클래스 교체하기
  
    - Controller 메소드에서 DAO 메소드 호출하면 안되는 이유 : **유지보수 과정에서 DAO 클래스를 다른 클래스로 쉽게 교체하기 위해서**
    - 그냥 DAO 메소드 호출해 쓰면, BoardDAO 클래스를 사용하는 Controller가 여러 개라면 모두 일일이 매개변수를 수정해야 해서 유지보수가 어렵다.
    - **인터페이스**를 통해서 비즈니스 컴포넌트를 이용하면, 컴포넌트의 구현 클래스를 수정하거나 대체해도 클라이언트는 수정 불필요.
    - **@Autowired**로 의존성 주입.
    
  - AOP 설정 적용하기
  
    - Controller 메소드에서 DAO 메소드 호출하면 안되는 이유 : **AOP 적용**
    - 횡단 관심에 해당하는 어드바이스가 동작하려면 반드시 **Service 구현 클래스의 비즈니스 메소드가 실행**되어야 한다.
    - 대부분 비즈니스 컴포넌트는 인터페이스를 가지고 있으며, 인터페이스만 컴포넌트를 사용하는 클라이언트에 노출한다.
    - **인터페이스의 추상 메소드 호출**로 Service 구현 객체의 메소드 실행 가능.
    
  - 비즈니스 컴포넌트 의존성 주입하기
  
    - @Autowired 어노테이션을 사용하려면 의존성 주입 대상이 되는 객체가 반드시 메모리에 올라가야 함.
    - Controller보다 Service 구현 객체가 먼저 생성되어 있어야 함
    
    
#### 비즈니스 컴포넌트 로딩

  - 2-Layered 아키텍처
  
    - `src/main/resources` 에는 비즈니스 레이어에 해당하는 설정 파일 **applicationContext.xml**.
    - `/WEB-INF/config` 에는 프레젠테이션 레이어에 해당하는 설정 파일 **presentation-layer.xml**.
    - DispatcherServlet이 생성되어 presentation-layer.xml 파일을 읽고 스프링 컨테이너를 구동하면 Controller 객체들이 메모리에 생성됨.
    - 그 전에 applicationContext.xml을 읽어 비즈니스 컴포넌트들을 메모리에 생성해야 함! -> **ContextLoaderListener**
    
  - ContextLoaderListener 등록
  
    - Listener는 Servlet이나 Filter 클래스와 마찬가지로 web.xml에 등록.
    - ContextLoaderListener 클래스는 서블릿 컨테이너가 web.xml 파일을 읽어서 구동될 때, **자동으로 메모리에 생성**
    - **클라이언트의 요청이 없어도 Pre-Loading 되는 객체**
    
  - 스프링 컨테이너와의 관계(순서)
  
    - 톰캣 서버를 처음 구동하면 web.xml 파일을 로딩, 서블릿 컨테이너 구동
    - 서블릿 컨테이너는 web.xml에 등록된 ContextLoaderListener 객체를 생성
    - ContextLoaderListener 객체는 applicationContext.xml을 로딩, 스프링 컨테이너 구동(**Root 컨테이너**)
    - Service 구현 클래스, DAO 객체들이 메모리에 생성
    - 사용자가 ".do" 요청을 서버에 전달하면, 서블릿 컨테이너는 DispatcherServlet 객체 생성
    - DispatcherServlet은 presentation-layer.xml 로딩하여 두 번째 스프링 컨테이너 구동
    - 두 번째 스프링 컨테이너가 Controller 객체를 메모리에 생성
    
    <br>
    
    - ContextLoaderListener, DispatcherServlet이 각각 XmlWebApplicationContext 생성 -> 역할/기능이 다름
    
      - ContextLoaderListener가 생성하는 **Root 컨테이너(부모)**.
      - DispatcherServlet이 생성하는 컨테이너는 Root 컨테이너가 생성한 객체를 이용(자식)
      - 부모가 생성한 비즈니스 객체를 자식이 생성한 Controller에서 참조 가능
      
      
<br>

## 4. 검색 기능 추가 구현

  (실습)
  
<br>

## 5. 파일 업로드

#### 파일 업로드 처리

  - CommonsMultipartResolver 클래스는 id, name 등이 정해져있다. (Resolver 로 끝나는 클래스들은 더 신경 써야함)
  - **setUploadFile()** 메소드가 호출되려면 **MultipartFile** 객체가 스프링 컨테이너에 의해 먼저 생성되어 있어야 한다.
  - MultipartFile 객체
  
    - 클라이언트가 업로드한 파일에 대한 모든 정보가 저장된다.
    - 이 객체만 가지고 있으면 원하는 위치에 해당 파일 업로드 가능
    - MultipartFile 인터페이스가 제공하는 주요 메소드
    
      | 메소드 | 설명 |
      | --- | --- |
      | String getOriginalFilename() | 업로드한 파일명을 문자열로 리턴 |
      | void transferTo(File destFile) | 업로드한 파일을 destFile에 저장 |
      | boolean isEmpty() | 업로드한 파일 존재 여부 리턴(없으면 true 리턴) |
      
      
#### 예외 처리

  - 어노테이션 기반의 예외 처리
  
    - @ControllerAdvice 와 @ExceptionHandler 사용.
    
  - XML 기반의 예외 처리
  
    - CommonExceptionHandler처럼 예외 처리 클래스를 별도 구현하지 않아도 돼서 좀더 쉬운 방법. xml 설정만 처리하면 됨.
    
<br>

## 6. 다국어 처리
