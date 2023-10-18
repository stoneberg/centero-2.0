# Centero 2.0

### 1. Centero2.0
- Centero2.0는 Centero2.0 백엔드 개발을 위한 repository입니다.
- Spring boot + myBatis(mysql) + Spring security + JWT 를 사용하고 있습니다.

### 2. 프로젝트 정보
```
1. 설치
    아래의 프로그램 설치가 선행되어야 합니다.
    - JDK 17
    - mysql 8.0.34(테스트 커밋)

2. 구조
    - common-api : 공통 API
    - centero-core : 공통 모듈 (security, login, exception, interceptor response code..)
    - ghg-api : ghg API, BATCH
    - netzero-api : netzero API, BATCH
```
### 3. 인증 token 정책

### 5. API docs by swagger
2개(ghg, netzero)의 swagger가 문서 존재합니다.(작업중)
- local 빌드할 경우 http://localhost:8080/swagger-ui.html

### 6. Commit log format
[모듈명][기능] 설명
- 예: [ghg][registry] 크레딧 발급