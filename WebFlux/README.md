회사 선임님 깃에 올라가 있는 Spring Webflux(Kotilin)을 무작정 따라해보자

ReactiveCrudRepository와 R2dbcRepository의 차이
ReactiveCrudRepository와 R2dbcRepository는 Spring Data R2DBC 프레임워크에서 사용되는 두 가지 다른 인터페이스입니다. 이 두 인터페이스는 R2DBC(Reactive Relational Database Connectivity)를 통해 데이터베이스와 상호 작용하는 데 도움을 주는데 사용됩니다.

ReactiveCrudRepository:

ReactiveCrudRepository는 Spring Data R2DBC에서 제공하는 일반적인 CRUD(Create, Read, Update, Delete) 작업을 수행하는 데 사용됩니다.
이 인터페이스는 데이터베이스 엔터티(예: 테이블)를 나타내는 Java 클래스와 함께 사용되며, 데이터베이스에서 엔터티를 생성, 읽기, 업데이트 및 삭제하는 데 필요한 메서드를 자동으로 생성합니다.
예를 들어, findById(), save(), deleteById() 등의 메서드를 포함합니다.
R2dbcRepository:

R2dbcRepository는 ReactiveCrudRepository와 비슷하지만, 더 낮은 수준의 추상화를 제공합니다.
이 인터페이스는 주로 사용자가 직접 SQL 쿼리를 작성하고 실행하려는 경우에 유용합니다.
@Query 어노테이션을 사용하여 사용자 정의 SQL 쿼리를 정의하고, R2dbcRepository를 확장하는 인터페이스에서 해당 메서드를 정의하여 실행할 수 있습니다.
즉, ReactiveCrudRepository는 더 간단한 CRUD 작업을 위한 일반적인 용도로 사용되며, R2dbcRepository는 직접 SQL 쿼리를 작성하고 실행해야 할 때 사용됩니다. 선택은 프로젝트 요구 사항과 선호도에 따라 달라질 수 있습니다.


Using Controller

    10/06
    챕터당 브랜치 생성
    main branch: 내용정리, 이해한 부분들, 해결과정 중심으로 기술한다

    10/07 
    usecase 코드 작성중 ... 이번 챕터에서는 코드를 전부 작성한 후 테스트를 진행 할 예정 





















































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