# Day 1

## 1. 개발 환경 구축

JDK 설치 -> 이클립스 EE 설치 -> tomcat 서버 설치 및 연동 -> 데이터베이스 구축(h2) -> STS(Spring Tool Suite) 플러그인 설치

<br>

 ## 2. 프레임워크 개요
 
 #### 프레임워크 개념
 
  - "뼈대 혹은 틀". 아키텍처에 해당하는 골격 코드 제공.
  - 개발자에게 모든 것을 위임하지 않고, 애플리케이션의 기본 아키텍처는 프레임워크가 제공하고 그 뼈대에 살을 붙이는 작업만 개발자가.
  - 장점
  
    1. 빠른 구현 시간 : 개발자는 비즈니스 로직만 구현하면 됨
    2. 쉬운 관리 : 같은 프레임워크 적용된 App은 아키텍처 같아서 관리 쉬움. 유지보수도 효율적.
    3. 개발자들의 역량 획일화 : 숙련자/초심자의 차이가 적음. 개발 인력 효율적 구성 가능.
    4. 검증된 아키텍처의 재사용과 일관성 유지 : 별다른 검증 없이 SW 개발 가능. 시간이 지나도 유지보수 과정에서 아키텍처의 왜곡, 변형 없음.
    
 #### 스프링 프레임워크
 
  - 이전의 자바 기반 엔터프라이즈 App은 대부분 EJB(Enterprise Java Beans)로 개발되었다. -> 너무 복잡하고 WAS가 따로 필요!
  - 스프링은 `POJO(Plain Old Java Object)`를 사용하면서 EJB에서만 가능했던 작업까지 지원.
  - **POJO (Palin Old Java Object)**
  
    - 평범한 옛날 자바 객체
    - 규칙이 약함
    - Not POJO 클래스 : Servlet 클래스. 반드시 요구하는 규칙에 맞게 클래스를 만들어야 실행할 수 있는 것.
    
  - 특징
  
  1. 경량(Lightweight)
    
      - 여러 개의 모듈로 구성, 각 모듈은 하나 이상의 JAR 파일로 구성. JAR 파일 몇 개만 있으면 개발, 실행 가능하므로 배포도 빠르고 쉽다.
      - 클래스 구현하는 데 특별한 규칙이 없는 단순하고 가벼운 POJO 형태의 객체를 관리함.
      
  2. 제어의 역행(Inversion of Control)
    
      - App을 구성하는 객체 간의 **느슨한 결합(낮은 결합도)** 유지.
      - IoC 적용되기 전에는 객체 생성이나 객체간 의존관계를 개발자가 직접 자바 코드로 처리했음. 의존관계에 있는 객체 변경 시, 반드시 코드 수정.
      - IoC 적용되면 객체 생성이나 객체간 의존관계를 **컨테이너**가 대신 처리. 소스에 의존관계가 명시되지 않아 결합도 떨어지고 유지보수 편리.
      
  3. 관점지향 프로그래밍(Aspect Oriented Programming, AOP)
    
      - 핵심 비즈니스 로직과 그 안에 반복해서 등장하는 공통 로직을 분리함. **높은 응집도**.
      - 공통으로 사용하는 기능들을 **외부의 독립된 클래스로 분리**, 코드에 명시하지 않고 **선언적으로 처리하여 적용**
      
  4. 컨테이너(Container)
    
      - 특정 객체의 **생성과 관리**를 담당하며 객체 운용에 필요한 다양한 기능을 제공.
      - 일반적으로 서버 안에 포함되어 배포 및 구동.
      - Servlet 컨테이너(Servlet 객체 생성/관리), EJB 컨테이너(EJB 객체 생성/관리) 등
    

#### IoC (Inversion of Control) 컨테이너

  - 컨테이너는 자신이 관리할 클래스들이 등록된 xml 설정 파일을 로딩하여 구동한다. 클라이언트 요청이 들어오는 순간 객체 생성하고 생명주기 관리.
  - 결합도(Coupling)가 높은 프로그램
  
    - **결합도 = 하나의 클래스가 다른 클래스와 얼마나 많이 연결되어 있는지**. 결합도 높을수록 유지보수 어려움.
    - 결합도를 낮추기 위해 **다형성(Polymorphism) 이용.** (인터페이스 활용하는 TV 예제)
    - 결합도를 낮추기 위해 **디자인 패턴 이용.** (객체 생성을 캡슐화해서 느슨한 결합 상태로 만들어주는 Factory 패턴 적용)
    
<br>

## 3. 스프링 컨테이너 및 설정 파일

#### 스프링 IoC 설정하기

  - 스프링 컨테이너가 사용할 xml 파일은 STS를 이용해 간단하게 만들어서 객체 관리를 할 수 있다.
  - \<bean\> element를 사용하는데 클래스 하나당 하나의 \<bean\> 설정이 필요. 여기서 가장 중요한 것은 class 속성값!
  - 스프링 컨테이너의 종류
  
    - **BeanFactory** :
      - 스프링 설정 파일에 등록된 \<bean\> 객체를 생성, 관리하는 가장 기본적인 컨테이너 기능만 제공.
      - 컨테이너가 구동될 때 \<bean\> 객체를 생성하는 것이 아니라, **클라이언트의 요청(LookUp)에 의해서만 \<bean\> 객체가 생성**되는 지연 로딩 방식(Lazy Loading) 사용.
      - 일반적인 스프링 프로젝트에서 사용할 일 없음
  
    - **ApplicationContext** :
      - BeanFactory 제공 기능 외에도 트랜잭션 관리, 메시지 기반의 다구거 처리 등 다양한 기능 지원.
      - **컨테이너가 구동되는 시점에 \<bean\> 등록된 클래스들을 객체 생성**. 즉시 로딩 방식(pre-loading) 사용.
      - 웹 App 개발도 지원. 대부분의 스프링 프로젝트는 ApplicationContext 유형 컨테이너 이용.
      - GenericXmlApplicationContext - 파일 시스템이나 클래스 경로에 있는 xml 설정 파일을 로딩하여 구동하는 컨테이너
      - XmlWebApplicationContext - 웹 기반의 스프링 App을 개발할 때 사용하는 컨테이너
  
  
#### 스프링 xml 설정

  - \<beans\> 루트 엘리먼트
  
    - 스프링 설정 파일 이름은 상관없지만 \<beans\>를 루트 엘리먼트로 사용해야 함.
    - 시작 태그에 네임스페이스를 비롯한 xml 스키마 관련 정보가 설정된다.
  
  - \<import\> 엘리먼트
  
    - 모든 설정을 하나의 파일로 처리할 수도 있지만 비효율적. 기능별 여러 xml 파일로 나누어 설정하는 것이 더 효율적임.
    - 이렇게 분리하여 작성한 설정 파일들을 하나로 통합할 때 \<import\> 엘리먼트 사용.
  
  - \<bean\> 엘리먼트
  
    - 스프링 설정 파일에 클래스를 등록하려면 \<bean\> 엘리먼트 사용
    - id 속성은 생략 가능, **class 속성은 필수**.
    - id는 컨테이너로부터 \<bean\> 객체를 요청할 때 사용하므로 이름이 고유해야 함. CamelCase 주로 사용.
    - name 속성도 id와 같은 기능을 하지만 name은 자바 식별자 작성 규칙 따르지 않는 문자열도 허용(특수기호 등).
  
  - \<bean\> 엘리먼트 속성
  
    - **init-method** <br>
    스프링 설정 파일에 등록된 클래스를 객체 생성할 때 디폴트 생성자를 호출한다.<br>
    따라서 객체 생성 후 멤버변수 초기화 작업 필요하다면 init-method 속성으로.<br>
      ```xml
      <bean id="tv" class="polymorphism.SamsungTV" init-method="initMethod" />
      ```
      \<bean\> 등록된 클래스 객체 생성 후에 init-method 속성으로 지정된 initMethod() 메소드 호출. 여기서 멤버변수 초기화 처리.
      
    - **destroy-method** <br>
    스프링 컨테이너가 객체를 삭제하기 직전에 호출될 임의의 메소드를 지정할 수 있다.<br>
      ```xml
      <bean id="tv" class="polymorphism.SamsungTV" destroy-method="destroyMethod" />
      ```
      
    - **lazy-init** <br>
    ApplicationContext 이용해서 컨테이너 구동하면 `즉시 로딩(pre-loading)` 방식으로 동작.<br>
    어떤 \<bean\>은 자주 사용되지도 않으면서 메모리만 많이 차지.<br>
    따라서 컨테이너가 구동되는 시점이 아닌 **해당 \<bean\>이 사용되는 시점**에 객체 생성하도록 하는 init-lazy 속성.<br>
      ```xml
      <bean id="tv" class="polymorphism.SamsungTV" lazy-init="true" />
      ```
      값이 true이면 스프링 컨테이너는 해당 \<bean\>을 미리 생성하지 않고, 클라이언트가 요청하는 시점에 생성. 메모리의 효율적 관리.
  
    - **scope**<br>
    하나만 생성돼도 상관없는 객체들의 경우 자연스럽게 하나의 객체만 생성하도록 제어해야 하는데, 이 때 사용하는 싱글톤 패턴<br>
    자동으로 싱글톤 객체로 생성해주는 기능을 스프링 컨테이너가 제공. 스프링 컨테이너는 생성한 \<bean\>을 어느 범위에서 사용할 수 있는지 지정 가능.<br>
       ```xml
      <bean id="tv" class="polymorphism.SamsungTV" scope="singleton" />
      ```
      scope 속성은 기본이 singleton. 이는 해당 bean이 스프링 컨테이너에 의해 **단 하나만 생성되어 운용되도록** 함.<br>
      prototype으로 지정할 수도 있는데, 이는 해당 \<bean\>이 요청될 때마다 매번 새로운 객체 생성하여 반환.
  
<br>
  
## 4. 의존성 주입

#### 의존성 관리

  - 스프링의 의존성 관리 방법
  
    - 스프링 프레임워크의 가장 큰 특징 = 객체의 생성과 의존관계를 컨테이너가 자동으로 관리. (IoC 핵심 원리)
    - IoC를 Dependency Lookup, Dependency Injection 두 가지 형태로 지원.
    - **Dependency Lookup**
    
      - 컨테이너가 애플리케이션 운용에 필요한 객체를 생성하고, 클라이언트는 컨테이너가 생성한 객체를 검색(Lookup)하는 방식.
      - 실제 App 개발 과정에서는 사용 잘 안함.
      
    - **Dependency Injection**
    
      - 객체 사이의 의존관계를 스프링 설정 파일에 등록된 정보를 바탕으로 컨테이너가 자동 처리. 컨테이너가 직접 처리.
      - 의존성 설정 바꾸고 싶을 때 프로그램 코드 수정 없이 설정 파일 수정만.
      - Setter Injection & 생성자 Injection
      
  - 의존성 관계 = 객체간 결합 관계
  
    - 한 객체에서 다른 객체의 변수나 메소드 이용해야 한다면, 이용하려는 객체에 대한 객체 생성과 생성된 객체의 레퍼런스 정보 필요.
    - 의존관계에 있는 객체에 대한 객체 생성 코드를 직접 다른 객체 소스에서 명시하면 비효율적. 이를 해결하기 위해 의존성 주입(Dependency Injection) 필요.
    

#### 생성자 인젝션 이용하기

  - 스프링 컨테이너는 객체 생성 시 기본적으로 **매개변수가 없는 기본 생성자**를 호출.
  - 하지만 매개변수를 가지는 다른 생성자를 호출하도록 설정 가능 -> 생성자 인젝션을 처리
  - **생성자 인젝션 : 생성자의 매개변수로 의존관계에 있는 객체의 주소 정보 전달** 가능.
  
    ```xml
    <bean id="tv" class="polymorphism.SamsungTV">
      <constructor-arg ref="sony"></constructor-arg>
    </bean>

    <bean id="sony" class="polymorphism.SonySpeaker"></bean>
    ```
    
    - 생성자 인젝션을 위해서는 클래스 \<bean\> 등록 설정에서 사이에 \<constructor-arg\> 엘리먼트 추가. ref 속성으로 생성자 인자로 전달할 객체의 아이디 참조.
    - 기본적으로는 bean 등록된 순서대로 객체 생성하지만, 예제에는 생성자 인젝션으로 의존성 주입될 SonySpeaker가 먼저 객체 생성된다.
    
    
  - 다중 변수 매핑
  
    - 생성자 인젝션에서 초기화해야 할 멤버변수가 여러 개이면 한꺼번에 전달.
    
      ```xml
      <bean id="tv" class="polymorphism.SamsungTV">
        <constructor-arg ref="sony"></constructor-arg>
        <constructor-arg value="2700000"></constructor-arg>
      </bean>

      <bean id="sony" class="polymorphism.SonySpeaker"></bean>
      ```
      
      인자로 전달될 데이터가 \<bean\>으로 등록된 다른 객체일 때는 ref 속성. 고정된 문자열이나 정수같은 기본형 데이터면 value 속성.<br>
      생성자가 여러 개 오버로딩 되어있다면 0부터 시작하는 index 속성으로 매핑을 지정 가능.(index="0" ref="sony")
      
  
#### Setter 인젝션 이용하기

  - Setter 인젝션은 **Setter 메소드를 호출하여 의존성 주입**을 처리.
  - 생성자 인젝션보다 Setter 인젝션을 더 많이 사용하고, Setter 메소드가 제공되지 않는 클래스에 대해서만 생성자 인젝션을 사용.
  - Setter 인젝션 기본
  
    - Setter 메소드는 스프링 컨테이너가 자동으로 호출. 호출하는 시점은 \<bean\> 객체 생성 직후.
    - Setter 인젝션이 동작하려면 Setter 메소드뿐만 아니라 기본 생성자도 필요.
    - \<property\> 엘리먼트 사용. name 속성값이 호출하고자 하는 메소드 이름.
    
      ```xml
      <bean id="tv" class="polymorphism.SamsungTV">
         <property name="speaker" ref="apple"></property>
         <property name="price" value="2700000"></property>
      </bean>
      ```
      
  - p 네임스페이스 사용하기
