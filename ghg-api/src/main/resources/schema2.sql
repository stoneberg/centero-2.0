-- ghg.TB_ACT_ACCOUNT definition

CREATE TABLE `TB_ACT_ACCOUNT`
(
    `Account_ID`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Password`              varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Account_TP`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Name`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Name_ENG`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ORG_BIZ_Number`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ORG_Address1`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Address2`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ORG_City`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Country`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_State`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ORG_Zip`               varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Telephone`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Fax`               varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ORG_Email`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ORG_Website`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `BL_Contact_Name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BL_Address1`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BL_Address2`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `BL_City`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BL_Country`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BL_Currency`           varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `BL_State`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `BL_Zip`                varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BL_Telephone`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BL_Fax`                varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `BL_Email`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Job_Title`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ACT_Name`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Address1`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Address2`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ACT_City`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Country`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_State`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ACT_Zip`               varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Telephone`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Email`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Fax`               varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `Account_TimeZone`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Activate_DT`           datetime                                                               DEFAULT NULL,
    `Invoice_DT`            datetime                                                               DEFAULT NULL,
    `Delete_YN`             char(1)                                                       NOT NULL DEFAULT 'N',
    `Last_Login_DT`         datetime                                                               DEFAULT NULL,
    `Login_Fail_CNT`        int                                                                    DEFAULT NULL,
    `Login_Fail_DT`         datetime                                                               DEFAULT NULL,
    `Login_Lock_DT`         datetime                                                               DEFAULT NULL,
    `Account_Management_YN` char(1)                                                       NOT NULL DEFAULT 'Y',
    `Create_ID`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`             datetime                                                      NOT NULL,
    `Update_ID`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`             datetime                                                      NOT NULL,
    PRIMARY KEY (`Account_ID`),
    KEY `IX_TB_ACT_ACCOUNT` (`Account_TP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_ACT_ACCOUNT_ACTION definition

CREATE TABLE `TB_ACT_ACCOUNT_ACTION`
(
    `Account_ID`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Account_Action_Type`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Account_Action_DT`      datetime                                                     NOT NULL,
    `Account_Action_Remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Create_ID`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`Account_ID`, `Account_Action_Type`, `Account_Action_DT`),
    KEY `IX_TB_ACT_ACCOUNT_ACTION` (`Account_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_ACT_ACCOUNT_AGREEMENT definition

CREATE TABLE `TB_ACT_ACCOUNT_AGREEMENT`
(
    `Account_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Agreement_Type`    varchar(10)                                                  NOT NULL,
    `Agreement_DT`      datetime                                                     NOT NULL,
    `Agreement_Remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Create_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`Account_ID`, `Agreement_Type`, `Agreement_DT`),
    KEY `IX_TB_ACT_ACCOUNT_AGREEMENT` (`Account_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_ACT_ACCOUNT_AGREEMENT_TEST definition

CREATE TABLE `TB_ACT_ACCOUNT_AGREEMENT_TEST`
(
    `account_id`   varchar(50)    NOT NULL COMMENT '사용자ID',
    `terms_Id`     bigint         NOT NULL COMMENT '약관ID',
    `service_type` varchar(20)    NOT NULL COMMENT '서비스 타입',
    `version`      int            NOT NULL COMMENT '차수',
    `is_agreement` enum ('Y','N')          DEFAULT 'N' COMMENT '동의 여부',
    `agreement_dt` datetime                DEFAULT CURRENT_TIMESTAMP COMMENT '동의일시',
    `create_id`    bigint                  DEFAULT NULL COMMENT '생성자ID',
    `create_dt`    datetime                DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    `modify_id`    bigint                  DEFAULT NULL COMMENT '수정자ID',
    `modify_dt`    datetime                DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
    `isDeleted`    enum ('Y','N') NOT NULL DEFAULT 'N',
    PRIMARY KEY (`account_id`, `terms_Id`, `service_type`, `version`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='고객약관동의정보';


-- ghg.TB_ACT_ACCOUNT_FIND_CODE definition

CREATE TABLE `TB_ACT_ACCOUNT_FIND_CODE`
(
    `Org_Email`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Verification_Code` varchar(6)                                                   NOT NULL,
    `Create_DT`         datetime                                                     NOT NULL,
    PRIMARY KEY (`Org_Email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_ACT_ACCOUNT_SUB definition

CREATE TABLE `TB_ACT_ACCOUNT_SUB`
(
    `Account_ID`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Sub_Account_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Password`              varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `ACT_Job_Title`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ACT_Name`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Address1`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Address2`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ACT_City`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Country`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_State`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `ACT_Zip`               varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Telephone`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Email`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `ACT_Fax`               varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `Activate_DT`           datetime                                                               DEFAULT NULL,
    `Invoice_DT`            datetime                                                               DEFAULT NULL,
    `Delete_YN`             char(1)                                                       NOT NULL DEFAULT 'N',
    `Last_Login_DT`         datetime                                                               DEFAULT NULL,
    `Login_Fail_CNT`        int                                                                    DEFAULT NULL,
    `Login_Fail_DT`         datetime                                                               DEFAULT NULL,
    `Login_Lock_DT`         datetime                                                               DEFAULT NULL,
    `Account_Management_YN` char(1)                                                       NOT NULL DEFAULT 'N',
    `Create_ID`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`             datetime                                                      NOT NULL,
    `Update_ID`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`             datetime                                                      NOT NULL,
    PRIMARY KEY (`Sub_Account_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_ACT_TERMS definition

CREATE TABLE `TB_ACT_TERMS`
(
    `terms_id`      bigint             NOT NULL AUTO_INCREMENT COMMENT '약관ID',
    `service_type`  varchar(20)        NOT NULL COMMENT '서비스 타입 : GHG, NET',
    `version`       int                NOT NULL COMMENT '차수',
    `terms_type`    varchar(100)                DEFAULT NULL COMMENT '약관유형(Personal,Terms,WithDrawal)',
    `title`         varchar(200)                DEFAULT NULL COMMENT '약관명',
    `content`       mediumtext COMMENT '약관내용',
    `opened_at`     datetime                    DEFAULT NULL COMMENT '적용일시',
    `is_use`        enum ('Y','N')              DEFAULT NULL COMMENT '약관사용여부(Y:사용중, N:중지)',
    `is_required`   enum ('Y','N')              DEFAULT NULL COMMENT '필수 동의 약관 여부',
    `display_order` int                         DEFAULT NULL COMMENT '약관노출순서',
    `create_dt`     datetime                    DEFAULT NULL COMMENT '생성일시',
    `create_id`     bigint                      DEFAULT NULL COMMENT '생성자ID',
    `modify_dt`     datetime                    DEFAULT NULL COMMENT '수정일시',
    `modify_id`     bigint                      DEFAULT NULL COMMENT '수정자ID',
    `is_latest`     enum ('Y','N')     NOT NULL DEFAULT 'N' COMMENT '갱신약관이 없는 최신버전인지 유무',
    PRIMARY KEY (`terms_id`, `service_type`, `version`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='고객약관정보';


-- ghg.TB_CMM_ATTACHFILE definition

CREATE TABLE `TB_CMM_ATTACHFILE`
(
    `Attach_SEQ`           bigint                                                        NOT NULL,
    `Attach_Category`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Attach_Category_DIV`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Attach_Category_SEQ`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Attach_File_Group`    int                                                           NOT NULL DEFAULT '0',
    `Attach_FilePath`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Attach_RealFile_NM`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Attach_ServerFile_NM` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Attach_FileSize`      int                                                           NOT NULL,
    `Attach_FileType`      varchar(10)                                                   NOT NULL,
    `Use_YN`               char(1)                                                       NOT NULL DEFAULT '1',
    `Open_YN`              char(1)                                                       NOT NULL DEFAULT 'N',
    `Attach_Remarks`       varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci         DEFAULT NULL,
    `Create_ID`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`            datetime                                                      NOT NULL,
    `Update_ID`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`            datetime                                                      NOT NULL,
    PRIMARY KEY (`Attach_SEQ`),
    KEY `IX_TB_CMM_ATTACHFILE` (`Attach_Category`, `Attach_Category_DIV`, `Attach_Category_SEQ` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_BOARD definition

CREATE TABLE `TB_CMM_BOARD`
(
    `BRD_SEQ`       bigint                                                        NOT NULL,
    `BRD_ParentSEQ` bigint                                                                 DEFAULT NULL,
    `BRD_Category`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `BRD_Title`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `BRD_Contents`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NOT NULL,
    `Top_YN`        char(1)                                                       NOT NULL DEFAULT '0',
    `Top_End_DT`    char(10)                                                               DEFAULT NULL,
    `Read_CNT`      int                                                           NOT NULL DEFAULT '0',
    `Deleted_YN`    char(1)                                                       NOT NULL DEFAULT '0',
    `Create_ID`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`     datetime                                                      NOT NULL,
    `Update_ID`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `UPdate_DT`     datetime                                                      NOT NULL,
    PRIMARY KEY (`BRD_SEQ`),
    KEY `IX_TB_CMM_BOARD` (`BRD_Category`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_CODE definition

CREATE TABLE `TB_CMM_CODE`
(
    `CODE_CD`     varchar(50)  NOT NULL COMMENT '코드 아이디',
    `P_CODE_CD`   varchar(50)           DEFAULT NULL COMMENT '부모코드 아이디',
    `LANG_CD`     varchar(255) NOT NULL COMMENT '언어 코드',
    `CODE_LVL`    smallint     NOT NULL COMMENT '코드 레벨',
    `DSP_ORDER`   smallint     NOT NULL COMMENT '출력 순서',
    `USE_YN`      char(1)               DEFAULT NULL COMMENT '사용여부',
    `EXP_FR_DT`   date                  DEFAULT NULL COMMENT '코드 유효기간 시작일',
    `EXP_TO_DT`   date                  DEFAULT NULL COMMENT '코드 유효기간 종료일',
    `CODE_DESC`   varchar(2000)         DEFAULT NULL,
    `ATTR1_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성1 메타정보 JSON',
    `ATTR1_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성1 값',
    `ATTR2_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성2 메타정보 JSON',
    `ATTR2_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성2 값',
    `ATTR3_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성3 메타정보 JSON',
    `ATTR3_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성3 값',
    `ATTR4_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성4 메타정보 JSON',
    `ATTR4_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성4 값',
    `ATTR5_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성5 메타정보 JSON',
    `ATTR5_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성5 값',
    `ATTR6_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성6 메타정보 JSON',
    `ATTR6_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성6 값',
    `ATTR7_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성7 메타정보 JSON',
    `ATTR7_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성7 값',
    `ATTR8_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성8 메타정보 JSON',
    `ATTR8_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성8 값',
    `ATTR9_JSON`  varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성9 메타정보 JSON',
    `ATTR9_VAL`   varchar(255)          DEFAULT NULL COMMENT '속성9 값',
    `ATTR10_JSON` varchar(1000)         DEFAULT NULL COMMENT '하위 레벨의 속성10 메타정보 JSON',
    `ATTR10_VAL`  varchar(255)          DEFAULT NULL COMMENT '속성10 값',
    `CRT_ID`      varchar(50)  NOT NULL COMMENT '생성자 아이디',
    `CRT_TM`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    `UPD_ID`      varchar(50)  NOT NULL COMMENT '수정자 아이디',
    `UPD_TM`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
    PRIMARY KEY (`CODE_CD`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- ghg.TB_CMM_CODE_BAK definition

CREATE TABLE `TB_CMM_CODE_BAK`
(
    `Code_ID`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Parent_Code_ID`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Code_NM`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Code_KOR_NM`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Code_ENG_NM`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Extension_Code_1` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Extension_Code_2` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Extension_Code_3` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Extension_Code_4` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Extension_Code_5` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Use_YN`           char(1)                                                       NOT NULL DEFAULT 'Y',
    `Sort_Order`       smallint                                                      NOT NULL,
    `Create_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`        datetime                                                      NOT NULL,
    `Update_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`        datetime                                                      NOT NULL,
    PRIMARY KEY (`Parent_Code_ID`, `Code_ID`),
    KEY `IX_TB_CMM_CODE` (`Parent_Code_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_COMMENT definition

CREATE TABLE `TB_CMM_COMMENT`
(
    `CMT_SEQ`          bigint                                                         NOT NULL,
    `CMT_Parent_SEQ`   bigint                                                                  DEFAULT NULL,
    `CMT_Category`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `CMT_Category_DIV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `CMT_Category_SEQ` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `CMT_Contents`     varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Deleted_YN`       char(1)                                                        NOT NULL DEFAULT '0',
    `Create_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`        datetime                                                       NOT NULL,
    `Update_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`        datetime                                                       NOT NULL,
    PRIMARY KEY (`CMT_SEQ`),
    KEY `IX_TB_CMM_COMMENT` (`CMT_Parent_SEQ` DESC, `CMT_Category`, `CMT_Category_DIV`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_FAQ definition

CREATE TABLE `TB_CMM_FAQ`
(
    `FAQ_SEQ`          bigint                                                         NOT NULL,
    `FAQ_Category`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `FAQ_Category_DIV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `FAQ_Question_KOR` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `FAQ_Question_ENG` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `FAQ_Answer_KOR`   varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `FAQ_Answer_ENG`   varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Deleted_YN`       char(1)                                                        NOT NULL DEFAULT 'N',
    `Sort_Order`       smallint                                                       NOT NULL DEFAULT '1',
    `Create_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`        datetime                                                       NOT NULL,
    `Update_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`        datetime                                                       NOT NULL,
    PRIMARY KEY (`FAQ_SEQ`),
    KEY `IX_TB_CMM_FAQ` (`FAQ_Category`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_LANG definition

CREATE TABLE `TB_CMM_LANG`
(
    `LANG_CD`   varchar(50) NOT NULL COMMENT '언어 코드',
    `LOCALE_CD` varchar(50) NOT NULL COMMENT '로케일',
    `DSP_TEXT`  varchar(255)         DEFAULT NULL COMMENT '출력 텍스트',
    `CODE_DESC` varchar(1000)        DEFAULT NULL COMMENT '코드 설명',
    `CRT_ID`    varchar(50) NOT NULL COMMENT '생성자 아이디',
    `CRT_TM`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    `UPD_ID`    varchar(50) NOT NULL COMMENT '수정자 아이디',
    `UPD_TM`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;


-- ghg.TB_CMM_LOG definition

CREATE TABLE `TB_CMM_LOG`
(
    `Sequence`         int                                                           NOT NULL COMMENT 'gfdgfdg',
    `Timestamp`        datetime                                                      NOT NULL COMMENT '발생시간',
    `LogLevel`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '로그 레벨',
    `ShortMessage`     varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '간단 메세지',
    `DetailMessage`    longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '자세한 메세지',
    `ApplicationName`  varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    `ServerIP`         varchar(50)                                                    DEFAULT NULL,
    `ServerHostName`   varchar(256)                                                   DEFAULT NULL,
    `UserID`           varchar(30)                                                    DEFAULT NULL COMMENT 'tyyy',
    `ClientIP`         varchar(50)                                                    DEFAULT NULL,
    `ControllerName`   varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    `ActionMethodName` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    PRIMARY KEY (`Sequence`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_LOG_SQL definition

CREATE TABLE `TB_CMM_LOG_SQL`
(
    `SQL_SEQ`          bigint    NOT NULL COMMENT 'SEQ',
    `REQUEST_URL`      varchar(2000)  DEFAULT NULL,
    `QUERY_ID`         varchar(200)   DEFAULT NULL,
    `QUERY_DATA`       longtext COMMENT 'SQL QUERY NAME (SP NAME)',
    `QUERY_PARAMETERS` longtext COMMENT 'SQL QUERY FULL STATEMENT (JSON STRING)',
    `DATABASE_NAME`    varchar(32)    DEFAULT NULL COMMENT '해당 쿼리의 DATABASE 이름',
    `USER_ID`          varchar(50)    DEFAULT NULL COMMENT '회원 아이디',
    `SERVER_IP`        varchar(100)   DEFAULT NULL,
    `DURATION`         double         DEFAULT NULL,
    `CREATE_DATE`      timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DATABASE에 INSERT된 날짜',
    PRIMARY KEY (`SQL_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='SQL 쿼리 레이어 로깅';


-- ghg.TB_CMM_MAIL_QUEUE definition

CREATE TABLE `TB_CMM_MAIL_QUEUE`
(
    `Queue_IDX`         bigint                                                        NOT NULL,
    `BIZ_Division1`     varchar(50)                                                   NOT NULL,
    `BIZ_Division2`     varchar(50)                                                   NOT NULL,
    `BIZ_RefSeq`        varchar(50)                                                   NOT NULL,
    `From_DisplayName`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `From_EmailAddress` varchar(50)                                                   NOT NULL,
    `To_EmailAddress`   longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NOT NULL,
    `Cc_EmailAddress`   longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `Bcc_EmailAddress`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `Email_Subject`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Email_Body`        longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `Status`            varchar(10)                                                   NOT NULL DEFAULT 'Waiting',
    `Fail_Message`      longtext,
    `Create_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`         datetime                                                      NOT NULL,
    `Update_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`         datetime                                                      NOT NULL,
    PRIMARY KEY (`Queue_IDX`),
    KEY `IX_TB_CMM_MAIL_QUEUE` (`BIZ_Division1`, `BIZ_Division2`, `BIZ_RefSeq` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_MENU definition

CREATE TABLE `TB_CMM_MENU`
(
    `Menu_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Parent_Menu_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Menu_NM`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Menu_KOR_NM`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Menu_ENG_NM`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Program_ID`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `Menu_DESC`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Display_YN`     char(1)                                                       NOT NULL DEFAULT 'Y',
    `Use_YN`         char(1)                                                       NOT NULL DEFAULT 'Y',
    `Sort_Order`     smallint                                                      NOT NULL,
    `Create_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`      timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `Update_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`      timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`Menu_ID`, `Parent_Menu_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_PROGRAM definition

CREATE TABLE `TB_CMM_PROGRAM`
(
    `Program_ID`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Program_NM`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Controller`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Action`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Parameter`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Program_DESC` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Use_YN`       char(1)                                                       NOT NULL DEFAULT 'Y',
    `Sort_Order`   smallint                                                      NOT NULL,
    `Create_ID`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`    datetime                                                      NOT NULL,
    `Update_ID`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`    datetime                                                      NOT NULL,
    PRIMARY KEY (`Program_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_ROLE definition

CREATE TABLE `TB_CMM_ROLE`
(
    `Role_ID`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Role_NM`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Role_DESC` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Use_YN`    char(1)                                                       NOT NULL DEFAULT 'Y',
    `Create_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT` datetime                                                      NOT NULL,
    `Update_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT` datetime                                                      NOT NULL,
    PRIMARY KEY (`Role_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_ROLE_ACCOUNT_TYPE_MAPPING definition

CREATE TABLE `TB_CMM_ROLE_ACCOUNT_TYPE_MAPPING`
(
    `Role_ID`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Account_TP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_ID`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`  datetime                                                     NOT NULL,
    `Update_ID`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`  datetime                                                     NOT NULL,
    PRIMARY KEY (`Role_ID`, `Account_TP`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_ROLE_MENU_MAPPING definition

CREATE TABLE `TB_CMM_ROLE_MENU_MAPPING`
(
    `Role_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Menu_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Menu_Parent_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `CREATE`         tinyint(1)                                                   NOT NULL DEFAULT '0',
    `READ`           tinyint(1)                                                   NOT NULL DEFAULT '0',
    `UPDATE`         tinyint(1)                                                   NOT NULL DEFAULT '0',
    `DELETE`         tinyint(1)                                                   NOT NULL DEFAULT '0',
    `EXECUTE`        tinyint(1)                                                   NOT NULL DEFAULT '0',
    `PRINT`          tinyint(1)                                                   NOT NULL DEFAULT '0',
    `AUTH_W`         tinyint(1)                                                   NOT NULL DEFAULT '0',
    `AUTH_X`         tinyint(1)                                                   NOT NULL DEFAULT '0',
    `AUTH_Y`         tinyint(1)                                                   NOT NULL DEFAULT '0',
    `AUTH_Z`         tinyint(1)                                                   NOT NULL,
    `Create_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`      datetime                                                     NOT NULL,
    `Update_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`      datetime                                                     NOT NULL,
    PRIMARY KEY (`Role_ID`, `Menu_ID`, `Menu_Parent_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_CMM_SUPPORT definition

CREATE TABLE `TB_CMM_SUPPORT`
(
    `Support_SEQ`               bigint                                                         NOT NULL,
    `Support_Parent_SEQ`        bigint                                                       DEFAULT NULL,
    `Support_Category`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Category_DIV`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Create_First_NM`   varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Create_Last_NM`    varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Create_Email`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Create_Org_NM`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Create_Country_CD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Support_Title`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Support_Contents`          varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Support_Answer_YN`         char(1)                                                      DEFAULT NULL,
    `Support_Answer_DT`         char(1)                                                      DEFAULT NULL,
    `Support_Answer_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Delete_YN`                 char(1)                                                      DEFAULT NULL,
    `Create_DT`                 datetime                                                       NOT NULL,
    `Update_DT`                 datetime                                                       NOT NULL,
    `Update_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_EXT_PROJECT definition

CREATE TABLE `TB_EXT_PROJECT`
(
    `PJT_SEQ`                   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Parent_SEQ`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `GHG_Program`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Origin_PJT_SEQ`            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `Origin_GHG_Program`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Origin_GHG_Program_Value`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `VVB_ID`                    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `PJT_Applicable_Project`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Country`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Region`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Proponent`             char(1)                                                                 DEFAULT NULL,
    `PJT_Proponent_Value`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Title`                 varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Title_ENG`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Size`                  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Overview`              varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_Overview_ENG`          varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `PJT_BIZ_Scope`             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_BIZ_Scope_Value`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_BIZ_Scope_Etc`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Emission_Source`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Emission_Source_Value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Emission_Source_Etc`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `PJT_Methodology_SEQ`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `PJT_Period_From`           varchar(10)                                                    NOT NULL,
    `PJT_Period_To`             varchar(10)                                                    NOT NULL,
    `PJT_Location`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Status`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_RGT_TP`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Type`                  char(3)                                                        NOT NULL,
    `PJT_OpenComment_DT`        datetime                                                                DEFAULT NULL,
    `PJT_OpenComment_AddWeek`   int                                                                     DEFAULT '3',
    `PJT_Invoice_DT`            datetime                                                                DEFAULT NULL,
    `REF_PJT_CMT_SEQ`           bigint                                                                  DEFAULT NULL,
    `Delete_YN`                 char(1)                                                        NOT NULL DEFAULT 'N',
    `Create_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`                 datetime                                                       NOT NULL,
    `Update_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`                 datetime                                                       NOT NULL,
    PRIMARY KEY (`PJT_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_IF_DOCUMENT_SERVICE definition

CREATE TABLE `TB_IF_DOCUMENT_SERVICE`
(
    `Vertification_ID` bigint                                                         DEFAULT NULL,
    `Attach_SEQ`       bigint                                                       NOT NULL,
    `Document_Hash`    varchar(64)                                                    DEFAULT NULL,
    `Document_Status`  varchar(50)                                                    DEFAULT NULL,
    `Transition_Hash`  varchar(64)                                                    DEFAULT NULL,
    `Wait_Count`       int                                                            DEFAULT NULL,
    `RS_Method`        varchar(50)                                                    DEFAULT NULL,
    `RS_Code`          varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    DEFAULT NULL,
    `RS_ShortMessage`  varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `RS_DetailMessage` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `Create_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`        datetime                                                     NOT NULL,
    PRIMARY KEY (`Attach_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_IF_ERP_BILLING definition

CREATE TABLE `TB_IF_ERP_BILLING`
(
    `IF_SEQ`         bigint                                                       NOT NULL,
    `Invoice_SEQ`    bigint                                                       NOT NULL,
    `Invoice_Status` varchar(10)                                                  NOT NULL,
    `Rfc_Function`   varchar(50)                                                  NOT NULL,
    `Request_Data`   longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NOT NULL,
    `Response_Data`  varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Message_TP`     char(1)                                                        DEFAULT NULL,
    `Message`        varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Create_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`      datetime                                                     NOT NULL,
    `Update_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`      datetime                                                     NOT NULL,
    PRIMARY KEY (`IF_SEQ`),
    KEY `IX_TB_IF_ERP_BILLING` (`Invoice_SEQ` DESC, `Invoice_Status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_MTD_METHODOLOGY definition

CREATE TABLE `TB_MTD_METHODOLOGY`
(
    `MTD_SEQ`                    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Parent_SEQ`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `GHG_Program`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `VVB_ID`                     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Applicable_Project`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Country`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Region`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Title`                  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `MTD_Title_ENG`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `MTD_Overview`               varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `MTD_Overview_ENG`           varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `MTD_Expiration_Term`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Expiration_Term_Value`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Expiration_Term_Others` varchar(2)                                                              DEFAULT NULL,
    `MTD_BIZ_Scope`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '',
    `MTD_BIZ_Scope_Value`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '',
    `MTD_BIZ_Scope_Etc`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `MTD_Emission_Source`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `MTD_Emission_Source_Value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `MTD_Emission_Source_Etc`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `MTD_Status`                 varchar(20)                                                    NOT NULL,
    `MTD_RGT_TP`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Type`                   char(1)                                                        NOT NULL,
    `MTD_OpenComment_DT`         datetime                                                                DEFAULT NULL,
    `MTD_OpenComment_AddWeek`    int                                                            NOT NULL DEFAULT '3',
    `MTD_Invoice_DT`             datetime                                                                DEFAULT NULL,
    `Delete_YN`                  char(1)                                                        NOT NULL DEFAULT 'N',
    `Create_ID`                  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`                  datetime                                                       NOT NULL,
    `Update_ID`                  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`                  datetime                                                       NOT NULL,
    PRIMARY KEY (`MTD_SEQ`),
    KEY `IX_TB_MTD_Mothodology` (`GHG_Program` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_MTD_METHODOLOGY_CONCEPT definition

CREATE TABLE `TB_MTD_METHODOLOGY_CONCEPT`
(
    `MTD_SEQ`                    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Parent_SEQ`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `GHG_Program`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `VVB_ID`                     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Applicable_Project`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Country`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Region`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Title`                  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `MTD_Title_ENG`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `MTD_Overview`               varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `MTD_Overview_ENG`           varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `MTD_Expiration_Term`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Expiration_Term_Value`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Expiration_Term_Others` varchar(2)                                                              DEFAULT NULL,
    `MTD_BIZ_Scope`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '',
    `MTD_BIZ_Scope_Value`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '',
    `MTD_BIZ_Scope_Etc`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `MTD_Emission_Source`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `MTD_Emission_Source_Value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `MTD_Emission_Source_Etc`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `MTD_Status`                 varchar(20)                                                    NOT NULL,
    `MTD_RGT_TP`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `MTD_Type`                   char(1)                                                        NOT NULL,
    `MTD_OpenComment_DT`         datetime                                                                DEFAULT NULL,
    `MTD_OpenComment_AddWeek`    int                                                            NOT NULL DEFAULT '3',
    `MTD_Invoice_DT`             datetime                                                                DEFAULT NULL,
    `Delete_YN`                  char(1)                                                        NOT NULL,
    `Create_ID`                  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`                  datetime                                                       NOT NULL,
    `Update_ID`                  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`                  datetime                                                       NOT NULL,
    PRIMARY KEY (`MTD_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_PJT_PROJECT definition

CREATE TABLE `TB_PJT_PROJECT`
(
    `PJT_SEQ`                   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Parent_SEQ`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `GHG_Program`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `VVB_ID`                    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Applicable_Project`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Country`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Region`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Proponent`             char(1)                                                        NOT NULL,
    `PJT_Proponent_Value`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Title`                 varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Title_ENG`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Size`                  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Overview`              varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_Overview_ENG`          varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `PJT_BIZ_Scope`             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_BIZ_Scope_Value`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_BIZ_Scope_Etc`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Emission_Source`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Emission_Source_Value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Emission_Source_Etc`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `PJT_Methodology_SEQ`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Period_From`           varchar(10)                                                    NOT NULL,
    `PJT_Period_To`             varchar(10)                                                    NOT NULL,
    `PJT_Location`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Status`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_RGT_TP`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Type`                  char(3)                                                        NOT NULL,
    `PJT_OpenComment_DT`        datetime                                                                DEFAULT NULL,
    `PJT_OpenComment_AddWeek`   int                                                            NOT NULL DEFAULT '3',
    `PJT_Invoice_DT`            datetime                                                                DEFAULT NULL,
    `REF_PJT_CMT_SEQ`           bigint                                                                  DEFAULT NULL,
    `Delete_YN`                 char(1)                                                        NOT NULL DEFAULT 'N',
    `Create_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`                 datetime                                                       NOT NULL,
    `Update_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`                 datetime                                                       NOT NULL,
    PRIMARY KEY (`PJT_SEQ`),
    KEY `IX_TB_PJT_Project` (`GHG_Program`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_PJT_PROJECT_COMMENT definition

CREATE TABLE `TB_PJT_PROJECT_COMMENT`
(
    `PJT_CMT_SEQ`    bigint                                                        NOT NULL,
    `PJT_SEQ`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Status`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Comment`    varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `REF_Action_SEQ` bigint                                                                 DEFAULT '0',
    `Use_YN`         char(1)                                                       NOT NULL DEFAULT 'N',
    `Create_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`      datetime                                                      NOT NULL,
    PRIMARY KEY (`PJT_CMT_SEQ`),
    KEY `IX_TB_PJT_PROJECT_COMMENT` (`PJT_CMT_SEQ`, `PJT_Status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_PJT_PROJECT_CREDIT definition

CREATE TABLE `TB_PJT_PROJECT_CREDIT`
(
    `PJT_Credit_SEQ`    bigint                                                        NOT NULL,
    `PJT_Credit_NO`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_SEQ`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_MNT_SEQ`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Credit_From_DT`    varchar(10)                                                   NOT NULL,
    `Credit_To_DT`      varchar(10)                                                   NOT NULL,
    `Credit_Size`       bigint                                                        NOT NULL,
    `Credit_Buffer`     int                                                           NOT NULL,
    `SKCS_Credit_LVL`   char(1)                                                      DEFAULT NULL,
    `Buffer_Deposit_TP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Create_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`         datetime                                                      NOT NULL,
    `Update_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`         datetime                                                      NOT NULL,
    PRIMARY KEY (`PJT_Credit_SEQ`),
    KEY `IX_TB_PJT_PROJECT_CREDIT` (`PJT_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_PJT_PROJECT_MONITORING definition

CREATE TABLE `TB_PJT_PROJECT_MONITORING`
(
    `PJT_MNT_SEQ`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_SEQ`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_MNT_Status`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Issue_Credit_YN`   char(1)                                                      NOT NULL DEFAULT 'N',
    `Buffer_Credit_YN`  char(1)                                                      NOT NULL DEFAULT 'N',
    `Invoice_YN`        char(1)                                                      NOT NULL DEFAULT 'N',
    `Final_Confirm_YN`  char(1)                                                      NOT NULL DEFAULT 'N',
    `MNT_Credit_Number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci         DEFAULT NULL,
    `Use_YN`            char(1)                                                      NOT NULL DEFAULT 'Y',
    `Create_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`         datetime                                                     NOT NULL,
    `Update_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`         datetime                                                     NOT NULL,
    PRIMARY KEY (`PJT_MNT_SEQ`),
    KEY `IX_TB_PJT_PROJECT_MONITORING` (`PJT_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_PJT_PROJECT_MONITORING_COMMENT definition

CREATE TABLE `TB_PJT_PROJECT_MONITORING_COMMENT`
(
    `PJT_MNT_CMT_SEQ` bigint                                                        NOT NULL,
    `PJT_SEQ`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_MNT_SEQ`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_MNT_Status`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_MNT_Comment` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Use_YN`          char(1)                                                       NOT NULL DEFAULT 'Y',
    `Create_ID`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`       datetime                                                      NOT NULL,
    PRIMARY KEY (`PJT_MNT_CMT_SEQ`),
    KEY `IX_TB_PJT_PROJECT_MONITORING_COMMENT` (`PJT_SEQ`, `PJT_MNT_SEQ` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_PJT_PROJECT_PIPELINE definition

CREATE TABLE `TB_PJT_PROJECT_PIPELINE`
(
    `PJT_SEQ`                   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Parent_SEQ`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `GHG_Program`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `VVB_ID`                    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Applicable_Project`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Country`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Region`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Proponent`             char(1)                                                        NOT NULL,
    `PJT_Proponent_Value`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Title`                 varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Title_ENG`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Size`                  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Overview`              varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_Overview_ENG`          varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `PJT_BIZ_Scope`             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_BIZ_Scope_Value`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_BIZ_Scope_Etc`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Emission_Source`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Emission_Source_Value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Emission_Source_Etc`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `PJT_Methodology_SEQ`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Period_From`           varchar(10)                                                    NOT NULL,
    `PJT_Period_To`             varchar(10)                                                    NOT NULL,
    `PJT_Location`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci           DEFAULT NULL,
    `PJT_Status`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_RGT_TP`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `PJT_Type`                  char(3)                                                        NOT NULL,
    `PJT_OpenComment_DT`        datetime                                                                DEFAULT NULL,
    `PJT_OpenComment_AddWeek`   int                                                            NOT NULL DEFAULT '3',
    `PJT_Invoice_DT`            datetime                                                                DEFAULT NULL,
    `REF_PJT_CMT_SEQ`           bigint                                                                  DEFAULT NULL,
    `Delete_YN`                 char(1)                                                        NOT NULL DEFAULT 'N',
    `Create_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`                 datetime                                                       NOT NULL,
    `Update_ID`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Update_DT`                 datetime                                                       NOT NULL,
    PRIMARY KEY (`PJT_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_ACTION definition

CREATE TABLE `TB_RGT_ACTION`
(
    `Action_SEQ`          bigint                                                       NOT NULL,
    `Action_Category`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Action_Category_DIV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Action_Category_SEQ` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Action_Group`        int                                                          NOT NULL DEFAULT '0',
    `Action_Remarks`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Use_YN`              char(10)                                                     NOT NULL DEFAULT 'Y',
    `Create_ID`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`           datetime                                                     NOT NULL,
    PRIMARY KEY (`Action_SEQ`),
    KEY `IX_TB_RGT_ACTION` (`Action_Category`, `Action_Category_DIV`, `Action_Category_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_ACTION_LOG definition

CREATE TABLE `TB_RGT_ACTION_LOG`
(
    `Account_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Action_Log_TP`     varchar(50)                                                  NOT NULL,
    `Action_Log_DT`     datetime                                                     NOT NULL,
    `Action_Log_Remark` varchar(100)                                                 NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_EVALUATION definition

CREATE TABLE `TB_RGT_EVALUATION`
(
    `EVT_SEQ`          bigint                                                         NOT NULL,
    `EVT_Category`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `EVT_Category_DIV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `EVT_Category_SEQ` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `EVT_Comment`      varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `EVT_Remarks`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci            DEFAULT NULL,
    `Use_YN`           char(1)                                                        NOT NULL,
    `VVB_Check`        char(1)                                                        NOT NULL DEFAULT 'N',
    `Create_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Create_DT`        datetime                                                       NOT NULL,
    PRIMARY KEY (`EVT_SEQ`),
    KEY `IX_TB_RGT_EVALUATION` (`EVT_Category`, `EVT_Category_DIV`, `EVT_Category_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_FEE definition

CREATE TABLE `TB_RGT_FEE`
(
    `Fee_SEQ`        bigint                                                       NOT NULL,
    `Fee_Code_ID`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Fee_KRW_Amount` int                                                          NOT NULL,
    `Fee_USD_Amount` decimal(18, 2)                                               NOT NULL DEFAULT '0.00',
    `Fee_Unit`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Use_YN`         char(1)                                                      NOT NULL DEFAULT 'Y',
    `Create_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`      datetime                                                     NOT NULL,
    `Update_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`      datetime                                                     NOT NULL,
    PRIMARY KEY (`Fee_SEQ`),
    KEY `IX_TB_RGT_FEE` (`Fee_Code_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_INVOICE definition

CREATE TABLE `TB_RGT_INVOICE`
(
    `Invoice_SEQ`          bigint                                                       NOT NULL,
    `Invoice_Category`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Invoice_Category_DIV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Invoice_Category_SEQ` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Invoice_Account_ID`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Invoice_DT`           datetime                                                              DEFAULT NULL,
    `Invoice_Item`         varchar(50)                                                  NOT NULL,
    `Invoice_Currency`     varchar(10)                                                  NOT NULL,
    `Invoice_Amount`       varchar(20)                                                  NOT NULL,
    `Invoice_VAT`          varchar(10)                                                  NOT NULL,
    `Invoice_TOT_Amount`   varchar(20)                                                  NOT NULL,
    `Invoice_Sales`        varchar(20)                                                  NOT NULL DEFAULT '0',
    `Invoice_Status`       varchar(10)                                                  NOT NULL,
    `I_BELNR4`             varchar(24)                                                           DEFAULT NULL,
    `GJAHR`                int                                                                   DEFAULT NULL,
    `I_BELNR`              varchar(10)                                                           DEFAULT NULL,
    `AUGBL`                varchar(10)                                                           DEFAULT NULL,
    `Reject_Remarks`       varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci        DEFAULT NULL,
    `Discount_Remarks`     varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci        DEFAULT NULL,
    `Invoice_WBS`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Create_ID`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`            datetime                                                     NOT NULL,
    `Update_ID`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`            datetime                                                     NOT NULL,
    PRIMARY KEY (`Invoice_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_OBJECTION definition

CREATE TABLE `TB_RGT_OBJECTION`
(
    `OBJ_SEQ`           bigint                                                        NOT NULL,
    `OBJ_Category_SEQ`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `OBJ_Comment`       varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `OBJ_Processing_YN` char(1)                                                       NOT NULL DEFAULT 'N',
    `Create_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`         datetime                                                      NOT NULL,
    `Update_ID`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`         datetime                                                      NOT NULL,
    PRIMARY KEY (`OBJ_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_OPEN_COMMENT definition

CREATE TABLE `TB_RGT_OPEN_COMMENT`
(
    `OC_SEQ`               bigint                                                         NOT NULL,
    `OC_Parent_SEQ`        bigint                                                                  DEFAULT NULL,
    `OC_Category`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Category_SEQ`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Create_NM`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Create_Country_CD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Create_ORG_NM`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Create_Contact_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Create_Email`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `OC_Comment`           varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `OC_Create_PW`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Delete_YN`            char(1)                                                        NOT NULL DEFAULT 'N',
    `Create_DT`            datetime                                                       NOT NULL,
    `Update_DT`            datetime                                                       NOT NULL,
    PRIMARY KEY (`OC_SEQ`),
    KEY `IX_TB_RGT_OPEN_COMMENT` (`OC_Category`, `OC_Category_SEQ` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_OPINION definition

CREATE TABLE `TB_RGT_OPINION`
(
    `OPN_SEQ`             bigint                                                        NOT NULL,
    `OPN_Category`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `REF_Registry_SEQ`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `REF_Registry_Status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `OPN_Comment`         varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Deleted_YN`          char(1)                                                       NOT NULL DEFAULT 'N',
    `Create_ID`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`           datetime                                                      NOT NULL,
    `Update_ID`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`           datetime                                                      NOT NULL,
    PRIMARY KEY (`OPN_SEQ`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_SEQUENCE definition

CREATE TABLE `TB_RGT_SEQUENCE`
(
    `SEQ_ACT_ID`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `SEQ_Category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `SEQ_YYYY`     varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `SEQ_MM`       char(2)                                                      DEFAULT NULL,
    `SEQ_DD`       char(2)                                                      DEFAULT NULL,
    `SEQ_NO`       bigint                                                       NOT NULL,
    `SEQ_Remarks`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Update_DT`    datetime                                                     NOT NULL,
    PRIMARY KEY (`SEQ_ACT_ID`, `SEQ_Category`, `SEQ_YYYY`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_RGT_TAX_DEADLINE definition

CREATE TABLE `TB_RGT_TAX_DEADLINE`
(
    `DeadLine_YYYYYDD` char(7)                                                      NOT NULL,
    `Month_Deadline`   char(10)                                                     NOT NULL,
    `Create_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`        datetime                                                     NOT NULL,
    `Update_ID`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`        datetime                                                     NOT NULL,
    PRIMARY KEY (`DeadLine_YYYYYDD`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_TRD_TRADING_LEDGER definition

CREATE TABLE `TB_TRD_TRADING_LEDGER`
(
    `TRD_SEQ`             bigint                                                        NOT NULL,
    `TRD_Parent_SEQ`      bigint                                                                 DEFAULT NULL,
    `PJT_SEQ`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_MNT_SEQ`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Credit_SEQ`      bigint                                                        NOT NULL,
    `TRD_TP`              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `PJT_Credit_NO`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `PJT_ORG_Credit_NO`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Credit_Proponent_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Credit_Size`         bigint                                                        NOT NULL,
    `TRD_Remarks`         varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci         DEFAULT NULL,
    `Use_YN`              char(1)                                                       NOT NULL DEFAULT 'Y',
    `Delete_YN`           char(1)                                                       NOT NULL DEFAULT 'N',
    `Create_ID`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Create_DT`           datetime                                                      NOT NULL,
    `Update_ID`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `Update_DT`           datetime                                                      NOT NULL,
    PRIMARY KEY (`TRD_SEQ`),
    KEY `IX_TB_TRD_TRADING_LEDGER` (`PJT_ORG_Credit_NO` DESC),
    KEY `IX_TB_TRD_TRADING_LEDGER_1` (`PJT_Credit_SEQ` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_TRD_TRADING_PURCHASE_REQUEST definition

CREATE TABLE `TB_TRD_TRADING_PURCHASE_REQUEST`
(
    `PR_SEQ`          bigint                                                         NOT NULL,
    `SLS_SEQ`         bigint                                                         NOT NULL,
    `Request_ID`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `Request_Remarks` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_Date`     datetime                                                       NOT NULL,
    PRIMARY KEY (`PR_SEQ`),
    KEY `IX_TB_TRD_TRADING_PURCHASE_REQUEST` (`SLS_SEQ` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- ghg.TB_TRD_TRADING_SALES definition

CREATE TABLE `TB_TRD_TRADING_SALES`
(
    `SLS_SEQ`            bigint                                                       NOT NULL,
    `TRD_SEQ`            bigint                                                       NOT NULL,
    `Price_Nego_YN`      char(1)                                                      NOT NULL DEFAULT 'N',
    `Price_Currency`     char(3)                                                               DEFAULT NULL,
    `Price_TON`          bigint                                                                DEFAULT NULL,
    `Credit_Seller`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Credit_Buyer`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL,
    `Contract_Currency`  char(3)                                                               DEFAULT NULL,
    `Contract_Price`     bigint                                                                DEFAULT NULL,
    `Contract_Remarks`   varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci        DEFAULT NULL,
    `Invoice_Confirm_DT` datetime                                                              DEFAULT NULL,
    `Credit_Transfer_DT` datetime                                                              DEFAULT NULL,
    `Sales_Status`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_ID`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Create_DT`          datetime                                                     NOT NULL,
    `Update_ID`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `Update_DT`          datetime                                                     NOT NULL,
    PRIMARY KEY (`SLS_SEQ`),
    KEY `IX_TB_TRD_TRADING_SALES` (`TRD_SEQ` DESC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;