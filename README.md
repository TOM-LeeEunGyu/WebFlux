
    10/06
    도메인 코드 작성중

    10/07 
    usecase 코드 작성중 ... 이번 챕터에서는 코드를 전부 작성한 후 테스트를 진행 할 예정

    10/11
    코드 작성완료 및 service 부분 테스트 진행
    updateMugiTEST 에러 발생 (expectation "assertNext" failed (expected: onNext(); actual: onError(org.springframework.data.mapping.MappingException: Couldn't find PersistentEntity for type class java.lang.Record))
    레포지토리 정리중 10/06 이후 코드 모조리 삭제됨 -> 깃 reset으로 복구

    10/11
    updateMugiTEST 에러 해결 못함
    mugis by converter test 과정에서 에러 발생(NullPointerException)

    10/16
    updateMugiTEST 에러 해결(CustomMugiRepositoryImpl query.update(Mugi::class.java) 오타)
    mugis by converter test 에러 해결(컨버터를 설정하는 config 관련 문제)
    WriteUseCase 테스트 코드 작성 및 테스트 완료
    ReadUseCase 테스트 코드 작성 및 테스트 완료

    10/20
    controller test 코드 작성 및 테스트 완료
    controllerTest createMugiTEST 에러 발생 (Status expected:<201 CREATED> but was:<500 INTERNAL_SERVER_ERROR>, expectStatus().isCreated 부분에서 에러 발생)
    해결과정: 1. java version 변경(20->17)
            2. java.lang.IllegalStateException: Authentication is not possible over an unsafe connection. Please use SSL or specify 'rsaPublicKey' 에러 발생
            3. 2번에러 해결: db연결(왜 disconnected 됐는지 모르겠음)
    















































