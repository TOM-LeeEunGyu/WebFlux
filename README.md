
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

    10/03
    controller 작성 및 테스트 코드 작성 완료
    어찌어찌 따라하긴 했지만 이해하지 못하는것 투성이다(동작원리에 대한 이해가 부족한듯)
    이해 후 다음 챕터로 넘아가도록 하자
    모르는 부분
    1. matrixVariable처리 관련 부분
    2. CustomUserRepositoryImpl update 관련 부분(assignments: MutableMap<SqlIdentifier, Any> 어떻게 작동하는지 모르겠음)

    10/21
    브랜치 이름 변경
    


















































