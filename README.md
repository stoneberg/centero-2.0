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
    - centero-core : 공통 모듈 (security, login, exception, interceptor, response code..)
    - ghg-api : ghg API, BATCH
    - netzero-api : netzero API, BATCH
```
### 3. 인증 token 정책

### 4. API docs by swagger
3개(common, ghg, netzero)의 API swagger가 문서 존재합니다.
- HOST 파일 설정 선작업 필요
  - Document Resposity 문서 참조
    - 경로 : 01. 개발산출물\02. 개발\02. 설계\01. 프로세스\주요 프로세스 설계_20231027.pptx
- 접근 경로
  1. common : http://common-api.centero.kr:8080//swagger-ui.html
  1. ghg : http://ghg-api.centero.kr:8081/swagger-ui.html
  1. netzero : http://netzero-api.centero.kr:8080/swagger-ui.html

### 5. Commit log format
[모듈명][기능] 설명
- 예: [ghg][registry] 크레딧 발급

### 6. UTC 시간 처리 정책

#### 개요
Centero 2.0에서는 모든 백엔드 프로세스에 대해 협정 세계시(UTC)를 기준 시간으로 사용합니다. 이 정책은 다양한 시간대와 시스템 간 일관성을 보장하며, 지역 시간 차이에 의해 발생할 수 있는 문제를 제거합니다.



### 7. Redis Cache 사용법
- Cache로 지정된 데이터는 Redis에 저장됩니다.
- Cache로 지징된 데이터는 최초 한번만 DB에서 조회되며, 이후에는 Redis에서 조회됩니다.
- Cache로 데이터를 지정하기 위해서 Service Layer에서 메서드 위에 `@Cacheable` 어노테이션을 선언합니다.
- value는 Cache의 이름을 의미하며, key는 Cache의 key를 의미합니다.
- 매번 달라지는 인수를 가진 메서드의 경우, 메서드에 전달되는 인수의 값을 key로 사용할 수 있습니다.
  ```java
  @Cacheable(value = "menu", key = "#request.svcCd")
  public void cachedMenu(MenuDto.MenuRequest request) {
    // menu 조회 로직
  }
- 인수가 없는 메서드의 경우에도 key를 지정할 수 있습니다.
  ```java
  @Cacheable(value = "menu", key = "'all'")
  public void cachedMenu() {
    // menu 조회 로직
  }
- Redis Cache의 TTL은 1시간으로 설정되어 있습니다.
- TTL 값 변경 시 RedisCacheConfig.java 파일의 entryTtl 값을 변하면 됩니다.
- 그외 삭제 또는 갱시 시 @CacheEvict(value = "menu", key = "#request.svcCd"), @CachePut(value = "menu", key = "#request.svcCd") 사용하면 됩니다.
>>>>>>> 20231103
