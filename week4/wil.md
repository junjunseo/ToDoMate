# 4주차 WIL

## Repository 계층

- DB와 소통하며 데이터를 조작하는 계층
- 서비스 계층이 결정한 비즈니스 로직을 실제 DB에 적용
- CRUD
  - Create (생성)
  - Read (조회)
  - Update (수정)
  - Delete (삭제)

## Entity Manager를 이용한 Repository 구현

### 개요

- Entity
  - Todo
  - User
  - Follow
- Repository
  - 각 Entity에 대응하는 Repository
- 주요 메서드
  - 생성 : save
  - 조회 : findById, findAll, ...
  - 삭제 : delete, deleteById
  - 내용 수정 : updateContent

### 테스트 코드

- 메서드 별로 준비(given), 실행(when), 검증(then) 구조로 주어진 상황에 대해서 비즈니스 로직이 잘 실행되는지 확인

## 느낀 점

- 직접 EntityManager를 사용해 CRUD를 구현해보니 내부적으로 JPA가 어떻게 동작하는지 이해할 수 있었음
- 테스트 코드를 활용하여 복잡한 쿼리 없이 상태 변화를 확인할 수 있음을 알게 되었음