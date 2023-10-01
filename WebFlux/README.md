회사 선임님 깃에 올라가 있는 Spring Webflux(Kotilin)을 무작정 따라해보자

시작하기 앞서 webflux란 무엇인지 알아보자
WebFlux 란 client, server 에서 reactive 스타일의 어플리케이션의 개발을 도와주는 스프링 모듈입니다. WebFlux 는 기존의 Servlet API를 기반으로 구축된 웹 프레임 워크인 Spring WebMvc 를 대체할 수 있는 웹 프레임워크입니다.(라고 블로그에 적혀 있다)


1. spring Webmvc vs WebFlux

    webmvc

    Spring MVC는 기본적으로 블럭킹이고 동기방식을 사용합니다.
    사용자의 요청이 들어올 때 마다 Thread를 생성하여 처리합니다.
    보통은 요청 시 마다 스레드를 생성, 삭제해주면 일정한 리소스가 지속적으로 소모되므로 Thread를 미리 생성해 저장해 두는 Thread Pool 을  생성해 사용합니다.
    따라서 spring mvc같은 경우 요청이 들어오면 그 요청을 Queue 에 쌓고 순서에 따라서 Thread 를 하나 점유해 요청을 처리합니다.
    동시 다발적으로 스레드 수를 초과하는 요청이 발생한다면 계속해서 요청이 큐에 대기하게되는 Thread Pool Hell 현상이 발생할 수 있습니다.

   WebFlux를

   WebFlux를 이 요청을 처리하는 방식이 Event-Driven 방식이고 비동기 논블러킹 방식입니다.
    WebFlux는 이벤트 루프가 돌아서 요청이 발생할 경우 그것에 맞는 핸들러에게 처리를 위임하고 처리가 완료되면 callback 메소드 등을 통해 응답을 반환합니다.
    이 방식의 경우 요청이 처리될 때까지 기다리지 않기때문에 Spring MVC에 비해 사용자의 요청을 대량으로 받아낼 수 있다는 장점이 있는 것 입니다.
    (여기까지만 읽어보면 node.js 처럼 작동하는 것 같다)

2. spring WebFlux를 사용하는 이유

   비동기 - 논블럭킹 방식의 리액티브 개발에 사용됩니다.
   서버 프로그램이 효율적으로 동작해서, cpu, thread, memory에 자원을 낭비하지않고 효율적으로 동작하는 고성능 웹 어플리케이션을 개발하는 걸 목적으로 합니다.
   Toby 님의 세미나에서는 서비스간 호출이 많은 마이크로 서비스 아키텍처에 적합하다고 합니다.

3. WebFlux를 왜 만들었을까 ?

   (1) webflux 가 생긴 이유 중 하나는 "적은 양의 스레드" 와 "최소한의 하드웨어 자원" 으로 동시성을 핸들링하기 위해 만들어졌다고 합니다.
   (2) 함수형 프로그래밍이 webflux의 기반이 되었다고 합니다. Java5에서 Rest controllers나 unit test가 만들어지고, Java8에서는 함수형 API를 위한 Lambda 표현식이 추가되었습니다. 이는 논블로킹 어플리케이션 API의 토대가 되었습니다.
    ➡️ Servlet 3.1 이후부터는 Non-Blocking 을 지원하기는 하지만 일부분만 지원하기 때문에, WebFlux에서는 netty 와 같이 잘만들어진 async, non-blocking 서버를 사용한다고 합니다. 
    따라서 Spring WebFlux는 리엑터 라이브러리 (Reactor library) 와 넷티 (Netty) 를 기반으로 동작합니다.
    
    🙌🏻 WebFlux 의 또 다른 장점 (사용해볼만한 이유?)
        Toby 님의 Spring camp. "spring web flux" 컨퍼런스 영상에서는
    "꼭 비동기적 성능처리 뿐만 아니라, 함수형 코딩만으로도 webflux를 사용할말한 이유가 될 수 있다" 라고 말씀해주십니다. 
    
    WebFlux 함수형 스타일의 장점
    
    모든 웹 요청 처리 작업을 명시적인 코드로 작성하게 되어있다.
    기존의 Spring MVC 는 어노테이션에 의한 흐름 관례를 사실상 외워야 코드의 해석이 가능하다. (어노테이션의 명시적인 분석이 힘듬)
    함수형 타입의 WebFlux는 명시적인 코드로 작성하기 때문에 메소드 시그니처 관례와 타입체크가 불가능한 어노테이션에 의존하는 @MVC 스타일보다는 명확하다 여겨질 수 있습니다.
    정확한 타입 체크 가능
    함수 조합을 통한 편리한 구성, 추상화에 유리
    함수형 스타일이기 때문에 추상화와 모듈화에 유리합니다.
    그렇기때문에 MVC 보다는 확정에 유리하다는 장점이 존재합니다.
    테스트 작성의 편리함
    Spring MVC 는 Controller 나 Service 단위를 테스트하기위해서는 사실상 WebTest 를 진행해야하지만
    WebFlux는 핸들러 로직은 물론이고 요청 매핑과 리턴 값 처리까지 단위테스트로 작성 가능
    ➡️ 하지만 @MVC 방식에서 바로 WebFlux 로 이전하기에는 거부감이 있으니, 어노테이션을 사용하는 @MVC + webflux 의 비동기 및 논블러킹을 지원하는 방법도 Spring 에서는 지원한다고 합니다.
   (라고 블로그에서 말하고 있다. 하지만 아직까지 나에게 와닿지 않는다. 배달의 민족 가게노출 시스템에서 WebFlux를 사용한다고 한다  )


Using Controller

    09/28
    시작부터 막혔다 springboot postgre-2dbc 환경세팅이 잘 되지 않는다.. 우선 코드부터 따라해보자..(해결)

    09/29
    도메인, 공통모듈 개발
    
    09/30 
    테스트 코드 작성 중 rsaPublicKey 에러 발생 (해결: db연결이 오류였음)
    테스트 코드 작성 중 does not declare any static, non-private, non-final, nested classes annotated with @Configuration. 에러 발생(해결: @SpringBootTest(classes = [DustinApplication::class])

    10/01
    테스트 코드 작성중 <mysql-connection-1> Received an error message -> ErrorMessage(errorCode=1146, sqlState=#42S02, errorMessage=Table 'dustin.user' doesn't exist) (해결: 콘솔에서 user 테이블 생성)
    usecase 작성 및 usecase 테스트 
    ReadUserServiceTest 에러(해결: 오타)






















































(빠른 이해를 위해 netty와 node.js를 비교해 보았다)
Netty는 자바로 개발된 네트워크 애플리케이션 프레임워크로, 자바 언어를 기반으로 하는 서버 및 클라이언트 애플리케이션을 구축하는 데 사용됩니다. Netty의 주요 컴포넌트를 JavaScript 개발과 비교하여 설명하겠습니다.

Channel (채널):

Netty에서 Channel은 네트워크 연결을 나타내는 핵심 컴포넌트입니다.
자바스크립트에서는 Socket 객체가 비슷한 역할을 합니다. Socket을 사용하여 클라이언트와 서버 간의 데이터 통신을 수행할 수 있습니다.
Event Loop (이벤트 루프):

Netty는 이벤트 루프를 사용하여 비동기 이벤트 처리를 지원합니다.
자바스크립트에서 비슷한 개념은 Node.js의 이벤트 루프입니다. Node.js는 단일 스레드 이벤트 루프를 사용하여 비동기 작업을 처리합니다.
Handler (핸들러):

Netty에서 Handler는 데이터를 읽고 쓰는 작업을 처리하는데 사용됩니다.
자바스크립트에서 이와 유사한 역할을 하는 것은 데이터 이벤트 핸들러 또는 콜백 함수입니다. 예를 들어, Node.js에서 데이터를 읽고 쓰는 작업은 콜백 함수를 사용하여 처리됩니다.
Pipeline (파이프라인):

Netty에서 Pipeline은 데이터 처리 작업의 연속된 체인을 나타냅니다. 각 핸들러는 데이터를 처리하고 다음 핸들러로 전달할 수 있습니다.
자바스크립트에서 비슷한 개념은 데이터 처리를 연결하는 함수 체인 또는 프라미스 체인입니다. 데이터를 처리하고 다음 단계로 전달하는 방식으로 비슷한 동작을 수행할 수 있습니다.
Bootstrap (부트스트랩):

Netty에서 Bootstrap은 네트워크 애플리케이션을 초기화하고 시작하는 데 사용됩니다.
자바스크립트에서 초기화 및 서버 시작은 보통 프레임워크 또는 라이브러리에 따라 다르며, 예를 들어 Express.js를 사용하여 웹 서버를 부트스트랩할 수 있습니다.
Netty는 자바 기반의 고성능 네트워크 애플리케이션을 개발하기 위한 강력한 도구입니다. 자바스크립트는 브라우저와 서버 측 개발 모두에 사용되며, 서버 측에서는 Node.js를 사용하여 비슷한 기능을 수행할 수 있지만, 기술적인 차이와 언어적인 차이가 있을 수 있습니다.