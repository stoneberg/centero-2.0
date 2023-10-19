-- DROP TABLES IF THEY EXIST
DROP TABLE IF EXISTS TB_USER_ROLE;
DROP TABLE IF EXISTS TB_ROLE;
DROP TABLE IF EXISTS TB_TOKEN;
DROP TABLE IF EXISTS TB_USER;
DROP TABLE IF EXISTS TB_LOGIN_LOGS;
DROP TABLE IF EXISTS TB_IP_BLACKLIST;
DROP TABLE IF EXISTS TB_BLACKLIST_ACCESS_LOGS;

-- TB_USER 테이블 생성
CREATE TABLE TB_USER
(
    USER_ID  BIGINT AUTO_INCREMENT PRIMARY KEY,
    EMAIL    VARCHAR(45)    NOT NULL UNIQUE,
    USERNAME VARCHAR(45)    NOT NULL UNIQUE,
    PASSWORD VARBINARY(255) NOT NULL -- 비밀번호를 VARBINARY 형태로 변경
);

-- TB_ROLE 테이블 생성
CREATE TABLE TB_ROLE
(
    ROLE_ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    ROLE_NAME VARCHAR(10) NOT NULL
);

-- TB_USER_ROLE 테이블 생성
CREATE TABLE TB_USER_ROLE
(
    USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,
    CONSTRAINT FK_USER_ROLE_USER FOREIGN KEY (USER_ID) REFERENCES TB_USER (USER_ID),
    CONSTRAINT FK_USER_ROLE_ROLE FOREIGN KEY (ROLE_ID) REFERENCES TB_ROLE (ROLE_ID)
);
-- 인덱스 생성
CREATE INDEX TB_USER_ROLE_USER_FK_IDX ON TB_USER_ROLE (USER_ID);
CREATE INDEX TB_USER_ROLE_ROLE_FK_IDX ON TB_USER_ROLE (ROLE_ID);

-- TB_TOKEN 테이블 생성(토큰에 대한 처리를 최소화하기 위해 REVOKED, TOKETYPE 삭제)
CREATE TABLE TB_TOKEN
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY,
    TOKEN     VARCHAR(255)       NOT NULL,
    USERNAME  VARCHAR(45) UNIQUE NOT NULL,
    ISSUED_AT TIMESTAMP          NOT NULL  -- 토큰 발급 시간
);
-- CREATE AN INDEX ON THE "USERNAME" COLUMN
CREATE INDEX TB_TOKEN_USERNAME_IDX ON TB_TOKEN(USERNAME);
-- 로그인 시도 및 성공 여부를 기록하기 위한 테이블
CREATE TABLE TB_LOGIN_LOGS
(
    ID            BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME      VARCHAR(255) NOT NULL,
    LOGIN_SUCCESS BOOLEAN      NOT NULL,
    LOGIN_TIME    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    IP_ADDRESS    VARCHAR(50)  NOT NULL,
    FAILURE_COUNT INT          DEFAULT 0
);
CREATE UNIQUE INDEX TB_LOGIN_LOGS_USERNAME_LOGIN_TIME_IDX ON TB_LOGIN_LOGS(USERNAME, LOGIN_TIME);

-- 로그인 접속 차단 IP 목록 테이블 생성
CREATE TABLE TB_IP_BLACKLIST
(
    ID         BIGINT AUTO_INCREMENT PRIMARY KEY,
    IP_ADDRESS VARCHAR(45) NOT NULL,
    REASON     VARCHAR(255),
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 로그인 접속 차단 IP 접속 로그 테이블 생성
CREATE TABLE TB_BLACKLIST_ACCESS_LOGS
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    IP_ADDRESS  VARCHAR(255) NOT NULL,
    USER_AGENT  VARCHAR(512),
    URI         VARCHAR(255),
    ACCESS_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
