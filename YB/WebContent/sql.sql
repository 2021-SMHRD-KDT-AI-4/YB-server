-- test

SELECT SHELTER_NAME, SHELTER_ADDR
FROM SHELTER_INFO
WHERE SHELTER_NAME = (
SELECT ADDRESS
FROM MEMBER
WHERE ID = (SELECT ID FROM NOSE_PRINT WHERE DOG_NOSE_PRINT = 'center_1271.jpg'));


SELECT NAME, TEL
FROM MEMBER
WHERE ID = (SELECT ID FROM NOSE_PRINT WHERE DOG_NOSE_PRINT = 'yeonji_1234.jpg');

SELECT picture, gender, kind
FROM BOARD
WHERE board_num IN (SELECT board_num2 FROM matching_result WHERE id = 'yeonji' );

SELECT picture, gender, kind
FROM BOARD 
			WHERE board_num IN (SELECT board_num2 FROM matching_result WHERE id = ? )
				
				
SELECT board_num2 FROM matching_result WHERE id = 'yeonji';

SELECT board_num, id, board_type, status, picture, gender, NVL(age,0) AS age, 
color, kind, NVL(missing_date,'NULL') AS missing_date, NVL(missing_time,'NULL') AS missing_time, 
NVL(weight,0) AS weight, NVL(notice,'NULL') AS notice, NVL(shelter,'NULL') AS shelter, city, place, tel, content 
FROM board;
						   	
						   	
SELECT MEMBER_CODE
FROM MEMBER
WHERE ID = 'yeonji';



-- DROP

DROP SEQUENCE BOARD_NUM_SEQ;
DROP SEQUENCE ADOPTION_ADP_NUM_SEQ;
drop sequence BOARD_BOARD_NUM_SEQ;
DROP SEQUENCE COMMENTS_COMMENT_NUM_SEQ;

DROP TABLE BOARD CASCADE constraints;
DROP TABLE ADOPTION CASCADE constraints;
DROP TABLE MEMBER CASCADE CONSTRAINT;

ALTER TABLE shelter_info RENAME COLUMN shelter_lng2 TO shelter_lng;
ALTER TABLE shelter_info RENAME COLUMN shelter_lng TO shelter_lat;

-- MEMBER 테이블
CREATE TABLE MEMBER(
  ID VARCHAR(50),
  PW VARCHAR(50) NOT NULL, 
  NAME VARCHAR(50) NOT NULL,
  MEMBER_CODE NUMBER NOT NULL,
  TEL NUMBER NOT NULL,
  ADDRESS VARCHAR(50) NOT NULL,
  CONSTRAINT MEMBER_ID_PK PRIMARY KEY(ID)
);


-- SHELTER_INFO
CREATE TABLE SHELTER_INFO(
	SHELTER_NAME VARCHAR(500) NOT NULL,
	SHELTER_ADDR VARCHAR(500) NOT NULL,
	SHELTER_LAT NUMBER,
	SHELTER_LNG NUMBER
)

-- BOARD 테이블
CREATE TABLE BOARD (
	BOARD_NUM NUMBER,
	ID VARCHAR(50) NOT NULL,
	BOARD_TYPE NUMBER NOT NULL,
	STATUS NUMBER NOT NULL,
	PICTURE VARCHAR(500) NOT NULL,
	GENDER VARCHAR(50) NOT NULL,
	AGE NUMBER,
	COLOR VARCHAR(500) NOT NULL,
	KIND VARCHAR(500) NOT NULL,
	WEIGHT NUMBER,
	NOTICE VARCHAR(50),
	MISSING_DATE VARCHAR(500),
	MISSING_TIME VARCHAR(500),
	SHELTER VARCHAR(500),
	CITY NUMBER NOT NULL,
	PLACE VARCHAR(500) NOT NULL,
	TEL VARCHAR(500) NOT NULL,
	CONTENT VARCHAR(500) NOT NULL,
	CONSTRAINT BOARD_NUM_PK PRIMARY KEY(BOARD_NUM),
	CONSTRAINT BOARD_ID_FK FOREIGN KEY (ID) REFERENCES MEMBER(ID)
);

CREATE SEQUENCE BOARD_NUM_SEQ START WITH 1 INCREMENT BY 1;

-- NOSE_PRINT 테이블

CREATE TABLE NOSE_PRINT(
  NOSE_PRINT_NUM NUMBER,
  ID VARCHAR(50) NOT NULL, 
  DOG_NAME VARCHAR(50) NOT NULL,
  DOG_NOSE_PRINT VARCHAR(500) NOT NULL,
  DOG_BREEDS VARCHAR(50) NOT NULL,
  DOG_GENDER VARCHAR(50) NOT NULL, 
  CONSTRAINT NOSE_PRINT_ID_FK FOREIGN KEY (ID) REFERENCES MEMBER (ID),
  CONSTRAINT NOSE_PRINT_NOSE_PRINT_NUM_PK PRIMARY KEY (NOSE_PRINT_NUM) 
);
CREATE SEQUENCE NOSE_PRINT_NOSE_PRINT_NUM_SEQ START WITH 1 INCREMENT BY 1;

-- MATCHING 테이블

CREATE TABLE MATCHING_RESULT(
  RESULT_NUM NUMBER,
  ID VARCHAR(50) NOT NULL,
  BOARD_NUM1 NUMBER NOT NULL,
  BOARD_NUM2 NUMBER NOT NULL,
  CONSTRAINT MATCHING_RESULT_RESULT_NUM_PK PRIMARY KEY (RESULT_NUM)
);
CREATE SEQUENCE MATCHING_RESULT_NUM_SEQ START WITH 1 INCREMENT BY 1;

-- COMMENTS 테이블
CREATE TABLE COMMENTS(
  COMMENTS_NUM NUMBER,
  BOARD_NUM NUMBER NOT NULL,
  ID VARCHAR2(50) NOT NULL,
  COMMENTS VARCHAR(500) NOT NULL,
  CONSTRAINT COMMENTS_COMMENTS_NUM_PK PRIMARY KEY (COMMENTS_NUM), 
  CONSTRAINT COMMENTS_COMMENTS_BOARD_NUM_FK FOREIGN KEY(BOARD_NUM) REFERENCES BOARD(BOARD_NUM)
  CREATE SEQUENCE COMMENTS_COMMENTS_NUM_SEQ START WITH 1 INCREMENT BY 1;
);

-- ADOPTION 테이블
CREATE TABLE ADOPTION(
  ADP_NUM NUMBER NOT NULL,
  ADP_STATUS NUMBER NOT NULL,
  ADP_PICTURE VARCHAR(500) NOT NULL,
  ADP_GENDER VARCHAR(50) NOT NULL,
  ADP_AGE NUMBER NOT NULL,
  ADP_COLOR VARCHAR(500) NOT NULL,
  ADP_KIND VARCHAR(500) NOT NULL,
  ADP_WEIGHT NUMBER NOT NULL,
  ADP_SHELTER VARCHAR(500) NOT NULL,
  ADP_ADDR VARCHAR(500) NOT NULL,
  ADP_NEUTER VARCHAR(50) NOT NULL,
  ADP_TEL VARCHAR(500) NOT NULL,
  ADP_CONTENT VARCHAR(500) NOT NULL,
  CONSTRAINT ADOPTION_ADP_NUM_PK PRIMARY KEY (ADP_NUM)
);

CREATE SEQUENCE ADOPTION_ADP_NUM_SEQ START WITH 1 INCREMENT BY 1;

-- PRED KIND 테이블

CREATE TABLE PRED_KIND (
  ID VARCHAR(50),
  TIME DATE  NOT NULL,
  FILENAME VARCHAR(500) NOT NULL,
  PRED_KIND VARCHAR(50) NOT NULL,
  CONSTRAINT PRED_KIND_ID_PK PRIMARY KEY (ID)
);

