-- Drop tables if they exist
DROP TABLE IF EXISTS tb_user_role;
DROP TABLE IF EXISTS tb_role;
DROP TABLE IF EXISTS tb_token;
DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_login_logs;
DROP TABLE IF EXISTS tb_ip_blacklist;
DROP TABLE IF EXISTS tb_blacklist_access_logs;

-- TB_USER 테이블 생성
CREATE TABLE tb_user
(
    user_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(45)    NOT NULL UNIQUE,
    username VARCHAR(45)    NOT NULL UNIQUE,
    password VARBINARY(255) NOT NULL -- 비밀번호를 VARBINARY 형태로 변경
);

-- TB_ROLE 테이블 생성
CREATE TABLE tb_role
(
    role_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(10) NOT NULL
);

-- TB_USER_ROLE 테이블 생성
CREATE TABLE tb_user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES tb_user (user_id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES tb_role (role_id)
);
-- 인덱스 생성
CREATE INDEX tb_user_role_user_fk_idx ON tb_user_role (user_id);
CREATE INDEX tb_user_role_role_fk_idx ON tb_user_role (role_id);

-- TB_TOKEN 테이블 생성(토큰에 대한 처리를 최소화하기 위해 revoked, tokeType 삭제)
CREATE TABLE tb_token
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    token     VARCHAR(255)       NOT NULL,
    username  VARCHAR(45) UNIQUE NOT NULL,
    issued_at TIMESTAMP          NOT NULL  -- 토큰 발급 시간
);
-- Create an index on the "username" column
CREATE INDEX tb_token_username_idx ON tb_token(username);
-- 로그인 시도 및 성공 여부를 기록하기 위한 테이블
CREATE TABLE tb_login_logs
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(255) NOT NULL,
    login_success BOOLEAN      NOT NULL,
    login_time    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_address    VARCHAR(50)  NOT NULL,
    failure_count INT          DEFAULT 0
);
CREATE UNIQUE INDEX tb_login_logs_username_login_time_idx ON tb_login_logs(username, login_time);

-- 로그인 접속 차단 IP 목록 테이블 생성
CREATE TABLE tb_ip_blacklist
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    ip_address VARCHAR(45) NOT NULL,
    reason     VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 로그인 접속 차단 IP 접속 로그 테이블 생성
CREATE TABLE tb_blacklist_access_logs
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    ip_address  VARCHAR(255) NOT NULL,
    user_agent  VARCHAR(512),
    uri         VARCHAR(255),
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
