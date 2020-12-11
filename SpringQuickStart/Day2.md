# Day2

## 1. 스프링 AOP

  - 비즈니스 컴포넌트 개발에서 가장 중요한 두 가지 원칙은 **낮은 결합도**와 **높은 응집도**
  - 스프링의 의존성 주입을 이용하면 비즈니스 컴포넌트를 구성하는 객체들의 결합도를 떨어뜨릴 수 있어서 의존관계를 쉽게 변경 가능
  - IoC - 결합도 vs. AOP - 응집도
  - AOP 이해하기
  
    - AOP(Aspect Oriented Programming)은 예외, 트랜잭션 등의 부가적인 코드들을 효율적으로 관리하는 데 주목
    - **관심 분리(Separation of Concerns)**
    
      - 횡단 관심 : 메소드마다 공통으로 등장하는 로깅, 예외, 트랜잭션 처리 등의 코드
      - 핵심 관심 : 사용자 요청에 따라 실제로 수행되는 핵심 비즈니스 로직
      - 두 관심을 완벽하게 분리할 수 있다면 간결하고 응집도 높은 코드 유지 가능. 하지만 독립적인 모듈로 분리해내기 어려움. => 스프링 AOP가 다소 극복
      
  - AOP 시작하기
  
    - 비즈니스 클래스 수정
    - AOP 라이브러리 추가. pom.xml에 dependency로 추가한다.
    - 네임스페이스 추가 및 AOP 설정
    - 스프링 AOP는 클라이언트가 **핵심 관심에 해당하는 비즈니스 메소드 호출 시, 횡단 관심에 해당하는 메소드를 적절하게 실행**해준다. 소스상 결합 발생 X.
    
<br>

## 2. AOP 용어 및 기본 설정

#### 용어 정리

  - `조인포인트(Joinpoint)`
  
    - 클라이언트가 호출하는 모든 비즈니스 메소드.
    - 포인트컷 대상, 포인트컷 후보 라고도 부름.(조인포인트 중에서 포인트컷이 선택되기 때문)
    
  - `포인트컷(Pointcut)`
  
    - 필터링된 조인포인트.
    - 수많은 비즈니스 메소드 중에서 원하는 특정 메소드에서만 횡단 관심에 해당하는 공통 기능을 수행시키기 위해서 포인트컷 필요
    - 메소드가 포함된 클래스와 패키지, 메소드 시그니처까지 정확히 지정 가능
    
  - `어드바이스(Advice)`
  
    - 횡단 관심에 해당하는 공통 기능의 코드
    - 독립된 클래스의 메소드로 작성.
    - 어드바이스로 구현된 메소드가 언제 동작할지 스프링 설정 파일을 통해서 지정 가능.
    - 어드바이스 동작 시점 : 'before', 'after', 'after-returning', 'after-throwing', 'around'
    
  - `위빙(Weaving)`
  
    - 포인트컷으로 지정한 핵심 관심 메소드가 호출될 때, 어드바이스에 해당하는 횡단 관심 메소드가 삽입되는 과정
    - 비즈니스 메소드 수정 안하고도 횡단 관심에 해당하는 기능을 추가/변경 가능
    - 방식 : 컴파일타임 위빙, 로딩타임 위빙, 런타임 위빙. (스프링에서는 런타임 위빙 방식만 지원)
    
  - `애스팩트(Aspect) 또는 어드바이저(Advisor)`
  
    - 포인트컷 + 어드바이스
    - 어떤 포인트컷 메소드에 대해서 어떤 어드바이스 메소드를 실행할 지 결정. 이에 따라 AOP 동작 방식이 결정됨
    
#### AOP 엘리먼트

  - AOP 관련 설정을 xml 방식, 어노테이션 방식으로 지원.
  - \<aop:config\> 엘리먼트
  
    - AOP 설정에서의 루트 엘리먼트.
    - 하위에는 \<aop:pointcut\>, \<aop:aspect\>.
    
  - \<aop:pointcut\> 엘리먼트
  
    - 포인트컷 지정하기 위해 사용.
    - \<aop:config\>, \<aop:aspect\>의 자식 엘리먼트로 사용 가능. (\<aop:aspect\> 하위에 설정된 포인트컷은 해당 \<aop:aspect\>에서만 사용)
    - 여러 개 정의할 수 있고 고유 id 할당해서 애스팩트 설정 시 포인트컷 참조.
    
  - \<aop:aspect\> 엘리먼트
  
    - 핵심 관시에 해당하는 포인트컷 메소드와 횡단 관심에 해당하는 어드바이스 메소드 결합하기 위해 사용
    - 이에 따라 위빙 결과 달라짐. 중요!!
    
  - \<aop:advisor\> 엘리먼트
  
    - 애스팩트와 같은 기능.
    - 트랜잭션 설정 등 몇몇 특수한 경우는 어드바이저만 사용해야 함.
    - 어드바이스 객체의 아이디를 모르거나 메소드 이름 확인할 수 없으면 애스팩트 설정 불가.
    
    
#### 포인트컷 표현식

  - 리턴타입 지정
  
  | 표현식 | 설명 |
  | ------ | --- |
  | * | 모든 리턴타입 허용 |
  | void | 리턴타입이 void인 메소드 선택 |
  | !void | 리턴타입이 void가 아닌 메소드 선택 |
  
  - 패키지 지정
  
  | 표현식 | 설명 |
  | ----- | --- |
  | com.springbook.biz | 정확히 이 패키지만 |
  | com.springbook.biz.. | 이 패키지 이름으로 시작하는 모든 패키지 선택 |
  | com.springbook..impl | com.springbook으로 시작하고 마지막이 impl로 끝나는 패키지 |
  
  - 클래스 지정
  
  | 표현식 | 설명 |
  | --- | --- |
  | BoardServiceImpl | 정확히 이 클래스만 |
  | *Imple | Impl로 끝나는 클래스만 |
  | BoardService+ | '+'가 붙으면 해당 클래스로부터 파생된 모든 자식 클래스 선택. 인터페이스 뒤에 '+'가 붙으면 해당 인터페이스를 구현한 모든 클래스 |
  
  - 메소드 지정
  
  | 표현식 | 설명 |
  | --- | --- |
  | *(..) | 가장 기본 설정. 모든 메소드 |
  | get*(..) | 메소드 이름이 get으로 시작하는 모든 메소드 |
  
  - 매개변수 지정
  
  | 표현식 | 설명 |
  | --- | --- |
  | (..) | 매개변수 개수, 타입에 제약 없음 |
  | (*) | 반드시 1개의 매개변수 가지는 메소드만 |
  | (com.springbook.user.UserVO) | 매개변수로 UserVO 가지는 메소드만. 클래스의 패키지 경로가 반드시 포함되어야 함 |
  | (!com.springbook.user.UserVO) | 매개변수로 UserVO 안가지는 메소드만 |
  | (Integer, ..) | 1개 이상의 매개변수 가지고, 첫 매개변수 타입이 정수 |
  | (Integer, *) | 반드시 2개의 매개변수 가지고, 첫 매개변수 타입이 정수 |
  
  
<br>

## 3. 어드바이스 동작 시점

  어드바이스 = 각 조인포인트에 삽입되어 동작할 횡단 관심에 해당하는 공통 기능.
  
  | 동작 시점 | 설명 |
  | --- | --- |
  | \<aop:before\> | 비즈니스 메소드 실행 전 동작 |
  | \<aop:after-returning\> | 비즈니스 메소드가 성공적으로 리턴되면 동작 |
  | \<aop:after-throwing\> | 비즈니스 메소드 실행 중 예외 발생하면 동작(try-catch의 catch에 해당) |
  | \<aop:after\> | 비즈니스 메소드 실행된 후, 무조건 실행(try-catch-finally의 finally에 해당) |
  |\<aop:around\> | 메소드 호출 자체를 가로채 비즈니스 메소드 실행 전후에 처리할 로직 삽입 가능 |
  
#### Before 어드바이스
  
  - 포인트컷으로 지정된 메소드 호출 시, 메소드가 실행되기 **전**에.
    
    ```xml
    <bean id="before" class="com.springbook.biz.common.BeforeAdvice" />

    <aop:config>
      <aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>

      <aop:aspect ref="before">
        <aop:before pointcut-ref="allPointcut" method="beforeLog"/>
      </aop:aspect>

    </aop:config>
    ```
      
#### After Returning 어드바이스

  - 포인트컷으로 지정된 메소드가 정상적으로 실행되고 나서, 메소드 수행 결과로 생성된 데이터를 **리턴**하는 시점에 동작
  - 결과 데이터를 이용하여 사후 처리 로직 추가할 때 사용
  
    ```xml
    <bean id="afterReturning" class="com.springbook.biz.common.AfterReturningAdvice" />

    <aop:config>
      <aop:pointcut id="getPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>

      <aop:aspect ref="before">
        <aop:after-returning pointcut-ref="getPointcut" method="afterLog"/>
      </aop:aspect>

    </aop:config>
    ```
    
#### After Throwing 어드바이스

  - 포인트컷으로 지정한 메소드가 실행되다가 **예외가 발생하는 시점**에 동작
  - 비즈니스 메소드에서 예외 발생 상황을 만들어줘야 함. 예시: `throw new IllegalArgumentException("등록 불가");`
  
    ```xml
    <bean id="afterThrowing" class="com.springbook.biz.common.AfterThrowingAdvice" />

    <aop:config>
      <aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>

      <aop:aspect ref="afterThrowing">
        <aop:after-throwing pointcut-ref="allPointcut" method="exceptionLog"/>
      </aop:aspect>

    </aop:config>
    ```
    
#### After 어드바이스

  - 예외 발생 여부에 상관없이 무조건 수행
  
    ```xml
    <bean id="afterThrowing" class="com.springbook.biz.common.AfterThrowingAdvice" />
    <bean id="after" class="com.springbook.biz.common.AfterAdvice" />

    <aop:config>
      <aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>

      <aop:aspect ref="afterThrowing">
        <aop:after-throwing pointcut-ref="allPointcut" method="exceptionLog"/>
      </aop:aspect>
      
      <aop:aspect ref="after">
        <aop:after pointcut-ref="allPointcut" method="finallyLog" />
      </aop:aspect>

    </aop:config>
    ```
    
    예외가 발생한 상황에서도 finallyLog()가 먼저 실행되고 exceptionLog()가 실행.
    
#### Around 어드바이스

  - 하나의 어드바이스가 비즈니스 메소드 실행 **전과 후에 모두 동작**하여 로직을 처리
  - 클라이언트의 메소드 호출을 가로채서 클라이언트의 비즈니스 메소드 실행 전에 사전처리 로직 수행하고, 비즈니스 메소드 실행 뒤에 사후 처리 로직 수행.
  
    ```java
    package com.springbook.biz.common;
    
    import org.aspectj.lang.ProceedingJoinPoint;
    
    public class AroundAdvice{
      public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("[BEFORE]: 비즈니스 메소드 수행 전에 처리할 내용");
        Object returnObj = pjp.proceed();
        System.out.println("[AFTER]: 비즈니스 메소드 수행 후에 처리할 내용");
        
        return returnObj;
      }
    }   
    ```
    
    ```xml
    <bean id="around" class="com.springbook.biz.common.AroundAdvice" />

    <aop:config>
      <aop:pointcut id="allPointcut" expression="execution(* com.springbook.biz..*Impl.*(..))"/>

      <aop:aspect ref="around">
        <aop:around pointcut-ref="allPointcut" method="aroundLog"/>
      </aop:aspect>

    </aop:config>
    ```
    
<br>

## 4. JoinPoint와 바인드 변수

  횡단 관심에 해당하는 어드바이스 메소드를 잘 구현하려면 클라이언트가 호출한 비즈니스 메소드의 정보 필요. 이를 이용할 수 있도록 JoinPoint 인터페이스 제공.
  
#### JoinPoint 메소드

  | 메소드 | 설명 |
  | --- | --- |
  | Signature getSignature() | 클라이언트가 호출한 메소드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체 리턴 |
  | Object getTarget() | 호출한 비즈니스 메소드를 포함하는 비즈니스 객체 리턴 |
  | Object[] getArgs() | 메소드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴 |
  
  - Around 어드바이스를 제외한 나머지 어드바이스에서는 JoinPoint를 사용해야 하고, Around 어드바이스에서는 ProceedingJoinPoint를 매개변수로 사용해야 함(proceed() 메소드 필요)
  - getSignature() 메소드가 리턴하는 Signature 객체를 이용하면 다양한 정보 얻을 수 있다. 아래 표 참고
  
    | 메소드명 | 설명 |
    | --- | --- |
    | String getName() | 메소드 이름 리턴 |
    | String toLongString() | 메소드의 리턴타입, 이름, 매개변수를 패키지 경로까지 포함하여 리턴 |
    | String toShortString() | 메소드 시그니처를 축약한 문자열로 리턴 |
  
  <br>
  
  - JoinPoint는 어드바이스의 종류에 따라 사용방법 다소 다름.
  - JoinPoint를 어드바이스 메소드 매개변수로 선언하면, 클라이언트가 비즈니스 메소드 호출할 때 스프링 컨테이너가 JoinPoint 객체 생성.
 
 
#### Before 어드바이스

  - 비즈니스 메소드 실행 전에 동작할 로직 구현을 하므로, **호출된 메소드 시그니처**만 알 수 있으면 다양한 사전처리 로직 구현 가능.
  - `jp.getSignature().getName()` , `jp.getArgs()` 
  
  
#### After Returning 어드바이스

  - 결과 데이터를 리턴할 때 동작하므로, 어떤 메소드가 어떤 값을 리턴했는지 알아야 사후처리 로직 구현 가능.
  - 매개변수로 JoinPoint 객체 뿐만 아니라 Object 타입의 변수도 선언. -> **바인드 변수**
  - 바인드 변수 : 비즈니스 메소드가 리턴한 결괏값을 바인딩할 목적으로 사용되고, 어떤 값이 리턴될지 몰라서 Object 타입으로 선언
  - 바인드 변수에 대한 매핑 설정도 스프링 설정 파일 .xml 에 추가해야 함. \<aop:after-returning\> 엘리먼트의 **returning 속성** 사용.
  
  
#### After Throwing 어드바이스

  - 예외가 발생할 때 동작하므로, 어떤 메소드에서 어떤 예외가 발생했는지 알아야 예외 처리 구현 가능.
  - 바인드 변수로 예외 객체를 받는다. 모든 예외 객체를 바인드할 수 있도록 최상위 타입인 Exception으로 선언.
  - 스프링 설정 파일에 추가. \<aop:after-throwing\> 엘리먼트의 **throwing 속성** 사용.
  
  
#### Around 어드바이스

  - **반드시 ProceedingJoinPoint 객체를 매개변수로** 받아야 함.
  - ProceedingJoinPoint 객체는 비즈니스 메소드를 호출하는 proceed() 메소드 가지고 있으며 JoinPoint 상속.
  
  
<br>

## 5. 어노테이션 기반 AOP

#### 어노테이션 기반 AOP 설정

  - 스프링 설정
  
    - xml에 가장 먼저 **\<aop:aspectj-autoproxy\>** 엘리먼트 선언.
    - AOP 관련 어노테이션들은 어드바이스 클래스에 설정.
    - 어노테이션을 컨테이너가 처리하게 하려면 **반드시 어드바이스 객체가 생성되어 있어야 한다**. xml에 \<bean\> 등록 또는 @Service 사용해서 검색되도록.
    
  - 포인트컷 설정
  
    - xml 설정에서 포인트컷 선언 시 \<aop:pointcut\> 사용.
    - @Pointcut 사용하며, 하나의 어드바이스 클래스 안에 여러 개의 포인트컷 선언 가능. 이를 식별하기 위해 참조 메소드 이용.(참조 메소드 : 빈 메소드)
    
  - 어드바이스 설정
  
    - 횡단 관심에 해당하는 어드바이스 메소드가 언제 동작할지 결정하여, 관련된 어노테이션을 메소드 위에 설정.
    - **반드시 어드바이스 메소드가 결합될 포인트컷을 참조**해야한다. 
    - 5가지 동작 시점.
    
  - 애스팩트 설정
  
    - @Aspect 이용.
    - @Aspect가 설정된 애스팩트 객체에는 **반드시 포인트컷과 어드바이스를 결합하는 설정이 있어야 함**.
    - 포인트컷 메소드와 어드바이스 메소드에 설정된 어노테이션에 의해 **위빙**이 처리됨.
    
#### 어드바이스 동작 시점

  - Before 어드바이스
  - After Returning 어드바이스
  - After Throwing 어드바이스
  - After 어드바이스
  - Around 어드바이스
  
  <br>
  
  - 외부 Pointcut 참조하기
  
    - 어노테이션 설정으로 변경하면서 어드바이스 클래스마다 포인트컷 설정이 포함됨.
    - 비슷하거나 같은 포인트컷이 반복 선언되는 것을 해결하고자 **포인트컷을 외부에 독립된 클래스에 따로 설정**하도록 함.
    - 외부 포인트컷을 참조하려면 `클래스 이름 + 참조 메소드 이름`의 조합으로 지정해야 한다.
    

<br>

## 6. 스프링 JDBC

#### 스프링 JDBC 개념

  - 비슷한 코드의 반복 작성이 비효율적임. DB 연동에 필요한 자바 코드를 대신 처리해주고 개발자는 실행되는 SQL 구문만 관리한다면 개발과 유지보수가 훨씬 편해질 것.
  - 스프링은 JDBC 기반의 DB 연동 프로그램을 쉽게 개발할 수 있도록 **JdbcTemplate 클래스**를 지원.
  
#### JdbcTemplate 클래스

  - 템플릿 메소드 패턴(복잡하고 반복되는 알고리즘을 캡슐화해서 재사용하는 패턴)이 적용된 클래스
  - 반복되는 DB 연동 로직은 JdbcTemplate 클래스의 템플릿 메소드가 제공. 개발자는 달라지는 SQL 구문과 설정값만.
  - DAO 클래스에서 JdbcTemplate 클래스가 제공하는 템플릿 메소드 호출 -> DB 연동 간단 처리
  - 내부적으로는 JDBC API 이용하여 실제 DB 연동 작업 수행.
  
#### 스프링 JDBC 설정

  - 라이브러리 추가. pom.xml에 dependency 설정 추가
  - DataSource 설정
  
    - JdbcTemplate 클래스가 JDBC API를 이용하여 DB 연동을 처리하려면 **반드시 데이터베이스로부터 커넥션을 얻어야** 한다.
    - JdbcTemplate 객체가 사용할 DataSource를 \<bean\> 등록하여 스프링 컨테이너가 생성하도록.
    - DataSource 인터페이스를 구현한 다양한 클래스 중, 실습에서는 Apache의 BasicDataSource 등록. 필요한 property를 Setter 인젝션으로 설정.
    
  - 프로퍼티 파일을 활용한 DataSource 설정
  
    - PropertyPlaceholderConfigurer 이용하면 외부 프로퍼티 파일 참조해서 DataSource 설정 가능.
    - config 폴더 생성하고 database.properties 파일에 property들을 작성.
    
    
#### JdbcTemplate 메소드

  - **update() 메소드**
  
    - INSERT, UPDATE, DELETE 구문 처리에 사용
    - SQL 구문에 **설정된 "?" 수만큼 값들을 차례대로 나열**하는 방식
    - `int update(String sql, Object... args)`
    
      ```java
      public void updateBoard(BoardVO vo){
        String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
        int cnt = jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
        System.out.println(cnt+"건 데이터 수정");
      }
      ```
      
    - Object 배열 객체에 SQL 구문에 설정된 "?" 수만큼의 값들을 세팅하여 **배열 객체를 두 번째 인자로 전달**하는 방식
    - `int update(String sql, Object[] args)`
    
      ```java
      public void updateBoard(BoardVO vo){
        String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
        Object[] args = {vo.getTitle(), vo.getContent(), vo.getSeq()};
        int cnt = jdbcTemplate.update(BOARD_UPDATE, args);
        System.out.println(cnt+"건 데이터 수정");
      }
      ```
    
  - **queryForInt() 메소드**
  
    - SELECT 구문으로 검색된 정숫값 리턴.
    - `int queryForInt(String sql)`, `int queryForInt(String sql, Object... args)`, `int queryForInt(String sql, Object[] args)`
    
      ```java
      public int getBoardTotalCount(BoardVO vo){
        String BOARD_TOT_COUNT = "select count(*) from board";
        int count = jdbcTemplate.queryForInt(BOARD_TOT_COUNT);
        System.out.println("전체 게시글 수 : "+count+"건");
      }
      ```
      
  - **queryForObject() 메소드**
  
    - SELECT 구문의 실행 결과를 특정 자바 객체(VO)로 매핑하여 리턴받을 때 사용.
    - 검색 결과가 없거나 검색 결과가 두 개 이상이면 예외 발생
    - 자바 객체(VO)로 매핑할 **RowMapper** 객체를 반드시 지정
    - `Object queryForObject(String sql)`, `Object queryForObject(String sql, RowMapper<T> rowMapper)`, `Object queryForObject(String sql, Object[] args, RowMapper<T> rowMapper)`
    
      ```java
      public BoardVO getBoard(BoardVO vo){
        String BOARD_GET = "select * from board where seq=?";
        Object[] args = {vo.getSeq()};
        return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
      }
      ```
      
    - 특정 VO 객체에 매핑하여 리턴하려면 RowMapper 인터페이스를 구현한 RowMapper 클래스가 필요. => RowMapper 클래스는 테이블당 하나씩 필요.
    - **mapRow() 메소드**로 어떻게 매핑할지 구현
    - RowMapper 객체를 queryForObject() 메소드의 매개변수로 넘겨주면, 컨테이너가 SQL 구문을 수행한 후 **자동으로 RowMapper 객체의 mapRow() 메소드를 호출**.
    
    
  - **query() 메소드**
  
    - SELECT 구문의 실행 결과가 목록일 때 사용. ( c.f. queryForObject() )
    - queryForObject() 메소드와 기본 사용법은 같다. 따라서 검색 결과를 VO 객체에 매핑하려면 RowMapper 객체를 사용.
    - `List query(String sql)`, `List query(String sql, RowMapper<T> rowMapper)`, `List query(String sql, Object[] args, RowMapper<T> rowMapper)`
    
      ```java
      public List<BoardVO> getBoardList(BoardVO vo){
        String BOARD_LIST = "select * from board order by seq desc";
        return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
      }
      ```
      
    - 검색된 데이터 ROW 수만큼 RowMapper 객체의 mapRow() 메소드가 실행되고, 매핑된 VO 객체 여러 개가 List 컬렉션에 저장되어 리턴.
    
    
#### DAO 클래스 구현

  DAO 클래스에서 JdbcTemplate 객체를 얻는 방법 2가지.
  
  - **JdbcDaoSupport 클래스 상속**
  
    - JdbcDaoSupport 클래스를 부모 클래스로 지정하면 getJdbcTemplate() 메소드 상속받을 수 있다. 그러면 JdbcTemplate 객체가 리턴되어 모든 메소드를 JdbcTemplate 객체로 구현 가능.
    - 그러기 위해서는 DataSource 객체를 가져야 함. `setDataSource()` 메소드 호출로 **데이터소스 객체를 의존성 주입**해야 함.
    
  - **JdbcTemplate 클래스 \<bean\> 등록, 의존성 주입**
  
    - xml에 JdbcTemplate 클래스를 bean 등록하고 **JdbcTemplate 객체에 DataSource 객체를 의존성 주입**으로 처리.
    - 그 다음, DAO 클래스에서는 @Autowired 어노테이션을 이용해 JdbcTemplate 타입의 객체를 의존성 주입 처리.
    
    
<br>

## 7. 트랜잭션 처리

  선언적 트랜잭션 처리 : 트랜잭션 처리를 컨테이너가 자동으로 처리하는 것. <br>
  xml 기반의 AOP 설정만 사용할 수 있고, 애스팩트 설정할 때도 \<aop:advisor\> 엘리먼트를 사용해야 함

#### 트랜잭션 네임스페이스 등록

  - tx 네임스페이스를 \<beans\> 루트 엘리먼트에 추가
  
#### 트랜잭션 관리자 등록

  - **트랜잭션 관리자 클래스**를 가장 먼저 등록. 어떤 기술로 데이터베이스 연동을 처리했느냐에 따라 관리자가 달라짐.
  - 모든 트랜잭션 관리자는 **PlatformTransactionManager** 인터페이스를 구현한 클래스들. -> commit(), rollback() 가짐
  - 실습에서는 xml에 DataSourceTransactionManager 클래스를 \<bean\> 등록한다.
  - bean 등록만으로 트랜잭션이 자동 관리되는 것은 아니며, 어드바이스가 실질적인 트랜잭션 관리를 함. (어드바이스: 비즈니스 메소드와 무관하게 공통 기능을 제공)
  
#### 트랜잭션 어드바이스 설정

  - **\<tx:advice\> 엘리먼트** 사용
  - 트랜잭션 관리 기능의 어드바이스는 직접 구현하지 않으며, 스프링 컨테이너가 \<tx:advice\> 설정을 참조하여 자동 생성
  - 즉, 트랜잭션 관리 어드바이스 객체의 클래스 이름, 메소드 확인 불가.
  - \<tx:advice\>가 가질 수 있는 속성 : 
  
    | 속성 | 의미 |
    | --- | --- |
    | name | 트랜잭션이 적용될 메소드 이름 지정 |
    | read-only | 읽기 전용 여부 지정(기본 false) |
    | no-rollback-for | 롤백하지 않을 예외 지정 |
    | rollback-for | 롤백할 예외 지정 |
    
#### AOP 설정을 통한 트랜잭션 적용

  - \<tx:advice\> 설정 때문에 스프링 컨테이너가 자동으로 클래스를 생성하므로 어드바이스 메소드 이름을 알 수 없다. 따라서 \<aop:aspect\> 엘리먼트 사용 불가.
  - \<aop:advisor\>도 aspect처럼 **포인트컷 + 어드바이스**.
  - 포인트컷으로 지정한 메소드가 호출될 때, txAdvice로 등록한 어드바이스가 동작하여 트랜잭션을 관리하도록 설정.
  
  
  
