DROP SEQUENCE BOARD_NUM_SEQ;
DROP SEQUENCE ADOPTION_ADP_NUM_SEQ;

SELECT board_num, id, board_type, status, picture, gender, age, 
color, kind, NVL(missing_date,'NULL') AS missing_date, NVL(missing_time,'NULL') AS missing_time, 
weight, notice, shelter, city, place, tel, content 
FROM board WHERE ROWNUM <= 4


DROP TABLE BOARD CASCADE constraints;
DROP TABLE ADOPTION CASCADE constraints;

ALTER TABLE shelter_info RENAME COLUMN shelter_lng2 TO shelter_lng;
ALTER TABLE shelter_info RENAME COLUMN shelter_lng TO shelter_lat;


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
	CONSTRAINT BOARD_NUM_PK PRIMARY KEY(BOARD_NUM)
);

CREATE SEQUENCE BOARD_NUM_SEQ START WITH 1 INCREMENT BY 1;

SELECT board_num, id, board_type, status, picture, gender, age, 
color, kind, NVL(missing_date,'NULL') AS missing_date, NVL(missing_time,'NULL') AS missing_time,
weight, notice, shelter, city, place, tel, content 
FROM board;

INSERT INTO board(board_num, id, board_type, status, picture, gender, age, color, kind, weight, notice, shelter, place, tel, content) 
VALUES(BOARD_NUM_SEQ.nextval, 'root', 3, 0, 'A', 'M', 2021, '황색!!2!', '믹스견', 2, '20210805~20210820', '제이준동물병원', 
'전라북도 장수군 장계면 한들로 76  제이준동물병원', '010-9800-2212', '사람을 잘 따르며 명랑함')
WHERE NOT EXISTS (SELECT picture FROM board WHERE picture = 'A')

INSERT INTO board(board_num, id, board_type, status, picture, gender, age, color, kind, weight, notice, shelter, place, tel, content) 
SELECT BOARD_NUM_SEQ.nextval, 'root', 3, 0, 'C', 'M', 2021, '황색!!2!3', '믹스견', 2, '20210805~20210820', '제이준동물병원', 
'전라북도 장수군 장계면 한들로 76  제이준동물병원', '010-9800-2212', '사람을 잘 따르며 명랑함' FROM DUAL
WHERE NOT EXISTS (SELECT picture FROM board WHERE picture = 'C')

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

-- SHELTER_INFO
CREATE TABLE SHELTER_INFO(
	SHELTER_NAME VARCHAR(500) NOT NULL,
	SHELTER_ADDR VARCHAR(500) NOT NULL,
	SHELTER_LAT NUMBER,
	SHELTER_LNG NUMBER
)
