-- connected by prior with pcodeCd condition
with recursive rec (CODE_CD, P_CODE_CD, LANG_CD, LVL) as
( select CODE_CD, P_CODE_CD, LANG_CD, 1
  from TB_CMM_CODE
  where P_CODE_CD = 'SD01'
  union all
  select t.CODE_CD, t.P_CODE_CD
       , concat(r.LANG_CD, ' > ', t.LANG_CD) 
       , LVL + 1
  from TB_CMM_CODE t, rec r
  where t.P_CODE_CD = r.CODE_CD
)
select *
from rec;

-- 기존공통코드 테이블내 다국어
insert into TB_CMM_LANG(LANG_CD, LOCALE_CD, DSP_TEXT, CODE_DESC, CRT_ID, UPD_ID)
select CONCAT('@CD_', LPAD(@ROWNUM:= @ROWNUM+1, 4, '0')) langCd, 'kr_KR', code_kor_nm dspText, code_nm codeDesc, 'mig', 'mig'
from (
select distinct Code_nm, code_kor_nm, code_eng_nm
from TB_CMM_CODE_BAK
) a
where ( @ROWNUM:=0 ) = 0;

insert into TB_CMM_LANG(LANG_CD, LOCALE_CD, DSP_TEXT, CODE_DESC, CRT_ID, UPD_ID)
select CONCAT('@CD_', LPAD(@ROWNUM:= @ROWNUM+1, 4, '0')) langCd, 'en_US', code_eng_nm dspText, code_nm codeDesc, 'mig', 'mig'
from (
select distinct Code_nm, code_kor_nm, code_eng_nm
from TB_CMM_CODE_BAK
) a
where ( @ROWNUM:=0 ) = 0;

-- 기존공통코드
INSERT into TB_CMM_CODE(DSP_ORDER, CODE_LVL, P_CODE_CD, CODE_CD, LANG_CD, ATTR1_VAL, ATTR2_VAL, ATTR3_VAL, ATTR4_VAL, ATTR5_VAL, CODE_DESC, USE_YN, EXP_FR_DT, EXP_TO_DT, CRT_ID, UPD_ID)
select a.Sort_Order, 99 level, IF(parent_code_id='Centero', 'BZ', parent_code_id) pcodeCd, a.code_id codeCd, l.LANG_CD langCd, Extension_Code_1 attr1val, Extension_Code_2 attr2val, Extension_Code_3 attr3val
, Extension_Code_4 attr4val, Extension_Code_5 attr5val, Code_NM codeDesc, use_yn, '2001-01-01', '9999-12-31', Create_ID , Update_ID
from TB_CMM_CODE_BAK a
   , TB_CMM_LANG l
where a.use_yn = 'Y'
  and Code_ID <> 'Centero'
  and l.LOCALE_CD = 'ko_KR'
  and l.DSP_TEXT = a.Code_KOR_NM
  and l.CODE_DESC = a.code_nm

with ttt as (
   SELECT p.CODE_CD
	 from TB_CMM_CODE c
	    , TB_CMM_CODE p
	where c.P_CODE_CD = p.CODE_CD and c.ATTR1_VAL is not null 
	  and p.ATTR1_JSON is null
	group by p.CODE_CD
)
update TB_CMM_CODE
   set ATTR1_JSON = '{"name":"속성1","type":"Text"}'
 where CODE_CD in (select CODE_CD from ttt)
 ;
with ttt as (
   SELECT p.CODE_CD
	 from TB_CMM_CODE c
	    , TB_CMM_CODE p
	where c.P_CODE_CD = p.CODE_CD and c.ATTR2_VAL is not null 
	  and p.ATTR2_JSON is null
	group by p.CODE_CD
)
update TB_CMM_CODE
   set ATTR2_JSON = '{"name":"속성2","type":"Text"}'
 where CODE_CD in (select CODE_CD from ttt)
 ;
 with ttt as (
   SELECT p.CODE_CD
	 from TB_CMM_CODE c
	    , TB_CMM_CODE p
	where c.P_CODE_CD = p.CODE_CD and c.ATTR3_VAL is not null 
	  and p.ATTR3_JSON is null
	group by p.CODE_CD
)
update TB_CMM_CODE
   set ATTR3_JSON = '{"name":"속성3","type":"Text"}'
 where CODE_CD in (select CODE_CD from ttt)
 ;
 with ttt as (
   SELECT p.CODE_CD
	 from TB_CMM_CODE c
	    , TB_CMM_CODE p
	where c.P_CODE_CD = p.CODE_CD and c.ATTR4_VAL is not null 
	  and p.ATTR4_JSON is null
	group by p.CODE_CD
)
update TB_CMM_CODE
   set ATTR4_JSON = '{"name":"속성4","type":"Text"}'
 where CODE_CD in (select CODE_CD from ttt)
 ;
  with ttt as (
   SELECT p.CODE_CD
	 from TB_CMM_CODE c
	    , TB_CMM_CODE p
	where c.P_CODE_CD = p.CODE_CD and c.ATTR5_VAL is not null 
	  and p.ATTR5_JSON is null
	group by p.CODE_CD
)
update TB_CMM_CODE
   set ATTR5_JSON = '{"name":"속성5","type":"Text"}'
 where CODE_CD in (select CODE_CD from ttt)
 ;
-- 권한
INSERT into TB_CMM_CODE(DSP_ORDER, CODE_LVL, P_CODE_CD, CODE_CD, LANG_CD, CODE_DESC, USE_YN, EXP_FR_DT, EXP_TO_DT, CRT_ID, UPD_ID)
select @ROWNUM:= @ROWNUM+1 DSP_ORDER , 2, 'SD02', role_id, Role_NM, role_desc, use_yn, '2001-01-01', '9999-12-31', Create_ID , Update_ID
from TB_CMM_ROLE
where use_yn = 'Y'
and Role_ID  not LIKE 'Test%'
and ( @ROWNUM:=0 ) = 0;

-- 프로그램
INSERT into TB_CMM_CODE(DSP_ORDER, CODE_LVL, P_CODE_CD, CODE_CD, LANG_CD, CODE_DESC, USE_YN, ATTR1_VAL, ATTR2_VAL, ATTR3_VAL, EXP_FR_DT, EXP_TO_DT, CRT_ID, UPD_ID)
select sort_order , 2, 'SD04', program_id, Program_NM, program_desc, use_yn, Controller, Action, PARAMETER,  '2001-01-01', '9999-12-31', Create_ID , Update_ID
from TB_CMM_PROGRAM
where use_yn = 'Y';

-- 메뉴
INSERT into TB_CMM_CODE(DSP_ORDER, CODE_LVL, P_CODE_CD, CODE_CD, LANG_CD, CODE_DESC, USE_YN, ATTR1_VAL, EXP_FR_DT, EXP_TO_DT, CRT_ID, UPD_ID)
select sort_order , 99 lvl, IF(Parent_Menu_ID='Centero', 'SD01', Parent_Menu_ID) ddd, menu_id, menu_NM, menu_desc, use_yn, Display_YN, '2001-01-01' fr, '9999-12-31'  tto, Create_ID , Update_ID
from TB_CMM_MENU m
where Use_YN = 'Y'
and Parent_Menu_ID <> '-'
and Menu_ID not in (
select menu_id from (
select menu_id, count(1) cnt from TB_CMM_MENU
group by menu_id
having count(1) > 1) ddd
) 
;