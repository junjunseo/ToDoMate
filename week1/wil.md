<<<<<<< HEAD
#Week 1 WIL

    1주차 학습 내용 정리
    -프로토콜 : 네트워크 안에서 요청과 응답을 보내는 규칙
    -웹에서는 HTTP라는 프로토콜 사용
    -API : Application Programming Interface, 어플리케이션과 소통하는 방법을 정의하 것
    -IntelliJ와 JDK 설정, Spring Boot 실행해보기

    TodoMate API 명세
    1. 사용자
    -회원가입       POST    /auth/sign_up
    -로그인         POST    /auth/login
    2. 할 일
    -할 일 생성     POST    /todo
    -할 일 조회     GET     /todo/{id}
    -할 일 수정     PUT     /todo/{id}
    -할 일 삭제     DELETE  /todo/{id}
    -할 일 체크     PATCH   /todo/{id}/check
    -할 일 체크 해제 PATCH   /todo/{id}/uncheck
    
    3. 친구
    -친구 찾기          GET     /users/search?nickname=닉네임
    -팔로우             POST    /friends/{userID}
    -언팔로우           DELETE  /friends/{userID}
=======
#Week 1 WIL

    1주차 학습 내용 정리
    -프로토콜 : 네트워크 안에서 요청과 응답을 보내는 규칙
    -웹에서는 HTTP라는 프로토콜 사용
    -API : Application Programming Interface, 어플리케이션과 소통하는 방법을 정의하 것
    -IntelliJ와 JDK 설정, Spring Boot 실행해보기

    TodoMate API 명세
    1. 사용자
    -회원가입       POST    /auth/sign_up
    -로그인         POST    /auth/login
    2. 할 일
    -할 일 생성     POST    /todo
    -할 일 조회     GET     /todo/{id}
    -할 일 수정     PUT     /todo/{id}
    -할 일 삭제     DELETE  /todo/{id}
    -할 일 체크     PATCH   /todo/{id}/check
    -할 일 체크 해제 PATCH   /todo/{id}/uncheck
    
    3. 친구
    -친구 찾기          GET     /users/search?nickname=닉네임
    -팔로우             POST    /friends/{userID}
    -언팔로우           DELETE  /friends/{userID}
>>>>>>> e7e9b59 (3주차 업데이트)
    -나의 친구 목록 조회  GET     /friends/{userID}/todo