```
개발에 사용 및 필요한 skill 및 이론은 skills에 정리

--- io.dustin

프로젝트 구조
 ㄴ apps
    ㄴ domain
        ㄴ api
            ㄴ controller
                - DomainController.java
            ㄴ usecase
                - ReadDomainUseCase.java
                - WriteDomainUseCase.java
                - DeleteDomainUseCase.java
                - ModifyDomainUseCase.java                
        ㄴ model
            ㄴ dto
                - DomainDto.java
            - DomainEntity.java    
        ㄴ repository
            ㄴ custom
                - CustomDomainRepository.java
            - DomainRepository.java
        ㄴ service 
            - ReadDomainService.java
            - WriteDomainService.java
    ㄴ common
        ㄴ advice
        ㄴ code
        ㄴ config
        ㄴ exception
        ㄴ model
        ㄴ properties
        ㄴ provider
        ㄴ repository
        ㄴ utils
        ㄴ validation 
        
    

유저
 - 회원가입 (구글, 카카오, 네이버, 깃)
 - 로그인 (jwt)
 - 로그아웃
 - 비밀번호 변경
 - 비밀번호 찾기
 - 닉네임 변경
 - 계정 비활성화
 - 계정 탈퇴

커뮤니티 사이트
 - 게시물 작성(사진, 동영상) 및 삭제
 - 댓글 및 대댓글 작성 및 삭제
 - 좋아요(추천)
 - 즐겨찾기(북마크)
 - 신고(게시물, 댓글)
 - 검색기능(조건부 검색 가능)
 - 제한(댓글 제한, 유저 제한)
 - 차단(유저)
 - 팔로우 
 
qna
 - 공통질문 작성
 - 1:1 문의
 - 1:1 답변
 
어드민
 - 신고 처리
 - 광고 (올리기, 변경)
 - 유저 정지
 - qna 관리
 - 공지 관리

--- 기본 기능 ---

게시물 작성 o
게시물 수정 o
게시물 삭제 o
게시물 조회(all) o
게시물 상세조회(단일) o

댓글 작성 o 
댓글 수정 o
댓글 삭제 o
댓글 조회(all) o
대댓글 조회 o

공지 작성 o
공지 수정 o
공지 삭제 o
공지 조회(all) o
공지 상세조회(단일) o

질문 작성 o
질문 수정 o
질문 삭제 o
질문 조회(all) o
질문 상세조회(단일) o

답변 작성 o
답변 수정 o
답변 삭제 o

좋아요 o
좋아요 취소 o

북마크 o
북마크 취소 o

팔로우 o
팔로우 취소 o

유저 차단 o
유저 차단 해제 o

게시물 댓글 제한 o 

```
