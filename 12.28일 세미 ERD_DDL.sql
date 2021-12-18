
--Member 회원 테이블 컬럼, 코멘트
﻿

DROP TABLE "MEMBER";

CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"MEMBER_ID"	VARCHAR2(30)		NOT NULL,
	"MEMBER_PW"	VARCHAR2(30)		NOT NULL,
	"MEMBER_EMAIL"	VARCHAR2(50)		NOT NULL,
	"MEMBER_NAME"	VARCHAR2(30)		NOT NULL,
	"MEMBER_BIRTHDAY"	NUMBER		NOT NULL,
	"MEMBER_PHONE"	NUMBER		NOT NULL,
	"ENROLL_DT"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MEMBER_STATUS_NO"	NUMBER		NOT NULL,
	"MEMBER_GRADE_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원고유번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_ID" IS '회원아이디';

COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원비밀번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원이메일';

COMMENT ON COLUMN "MEMBER"."MEMBER_NAME" IS '회원이름';

COMMENT ON COLUMN "MEMBER"."MEMBER_BIRTHDAY" IS '회원생년월일';

COMMENT ON COLUMN "MEMBER"."MEMBER_PHONE" IS '회원 휴대폰 번호';

COMMENT ON COLUMN "MEMBER"."ENROLL_DT" IS '회원가입일자';

COMMENT ON COLUMN "MEMBER"."MEMBER_STATUS_NO" IS '회원상태고유번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_GRADE_NO" IS '회원멤버십고유번호';

--MEMBER_STATUS 회원상태 테이블 컬럼, 코멘트

DROP TABLE "MEMBER_STATUS";

CREATE TABLE "MEMBER_STATUS" (
	"MEMBER_STATUS_NO"	NUMBER		NOT NULL,
	"MEMBER_STATUS_NAME"	VARCHAR2(30)		NOT NULL
);

COMMENT ON COLUMN "MEMBER_STATUS"."MEMBER_STATUS_NO" IS '회원상태고유번호';

COMMENT ON COLUMN "MEMBER_STATUS"."MEMBER_STATUS_NAME" IS '회원상태명';

--MEMBER_GRADE 회원 등급 테이블 컬럼, 코멘트

DROP TABLE "MEMBER_GRADE";

CREATE TABLE "MEMBER_GRADE" (
	"MEMBER_GRADE_NO"	NUMBER		NOT NULL,
	"MEMBER_STATUS_NAME"	VARCHAR2(50)		NOT NULL,
	"MEMBER_STATUS_DISCOUNT"	NUMBER		NOT NULL
);
ALTER TABLE MEMBER_GRADE ADD (MEMBER_PURCHASE_AMOUNT NUMBER NOT NULL);
COMMENT ON COLUMN "MEMBER_GRADE"."MEMBER_GRADE_NO" IS '회원멤버십고유번호';

COMMENT ON COLUMN "MEMBER_GRADE"."MEMBER_STATUS_NAME" IS '회원멤버십등급명';

COMMENT ON COLUMN "MEMBER_GRADE"."MEMBER_STATUS_DISCOUNT" IS '회원등급별할인률';

--ORDER 주문 테이블 컬럼, 코멘트

DROP TABLE "ORDER";

CREATE TABLE "ORDER" (
	"ORDER_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"PRODUCT_NO"	NUMBER		NOT NULL,
	"ORDER_AMOUNT"	NUMBER	DEFAULT 1	NOT NULL,
	"ORDER_STATUS_CD"	NUMBER		NOT NULL,
	"DELIVERY_NO"	VARCHAR2(15)		NULL
);

COMMENT ON COLUMN "ORDER"."ORDER_NO" IS '주문 번호';

COMMENT ON COLUMN "ORDER"."MEMBER_NO" IS '회원고유번호';

COMMENT ON COLUMN "ORDER"."PRODUCT_NO" IS '상품번호';

COMMENT ON COLUMN "ORDER"."ORDER_AMOUNT" IS '상품수량';

COMMENT ON COLUMN "ORDER"."ORDER_STATUS_CD" IS '주문처리 상태 코드';

COMMENT ON COLUMN "ORDER"."DELIVERY_NO" IS '배송번호';

--PRODUCT 상품 테이블 컬럼, 코멘트


DROP TABLE "PRODUCT";

CREATE TABLE "PRODUCT" (
	"PRODUCT_NO"	NUMBER		NOT NULL,
	"PRODUCT_NM"	VARCHAR2(100)		NOT NULL,
	"PRODUCT_PRICE"	NUMBER		NOT NULL,
	"DISCOUNT"	NUMBER		NULL,
	"STOCK"	NUMBER		NOT NULL,
	"상품카테고리번호"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "PRODUCT"."PRODUCT_NO" IS '상품번호';

COMMENT ON COLUMN "PRODUCT"."PRODUCT_NM" IS '상품명(용량 등 옵션도 명세되야함)';

COMMENT ON COLUMN "PRODUCT"."PRODUCT_PRICE" IS '상품 금액';

COMMENT ON COLUMN "PRODUCT"."DISCOUNT" IS '상품 할인율';

COMMENT ON COLUMN "PRODUCT"."STOCK" IS '남은 재고 수량';

COMMENT ON COLUMN "PRODUCT"."상품카테고리번호" IS '상품 카테고리 번호';

--PRODUCT_CATEGORY 상품카테고리 테이블 컬럼, 코멘트

DROP TABLE "PRODUCT_CATEGORY";

CREATE TABLE "PRODUCT_CATEGORY" (
	"CATEGORY_NO"	NUMBER		NOT NULL,
	"CATEGORY_NM"	VARCHAR2(50)		NOT NULL
);

COMMENT ON COLUMN "PRODUCT_CATEGORY"."CATEGORY_NO" IS '상품 카테고리 번호';

COMMENT ON COLUMN "PRODUCT_CATEGORY"."CATEGORY_NM" IS '상품 카테고리 ( EX: 스킨, 로션 등)';


--RECOMMENDED_PRODUCT 추천상품 테이블 컬럼, 코멘트


DROP TABLE "RECOMMENDED_PRODUCT";

CREATE TABLE "RECOMMENDED_PRODUCT" (
	"PRODUCT_NO"	NUMBER		NOT NULL,
	"PRODUCT_NM"	VARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "RECOMMENDED_PRODUCT"."PRODUCT_NO" IS '추천상품번호';

COMMENT ON COLUMN "RECOMMENDED_PRODUCT"."PRODUCT_NM" IS '추천 상품 이름';

--PRODUCT_IMG 상품이미지 테이블 컬럼, 코멘트


DROP TABLE "PRODUCT_IMG";

CREATE TABLE "PRODUCT_IMG" (
	"PRODUCT_IMG_NO"	NUMBER		NOT NULL,
	"PRODUCT_IMG_PATH"	VARCHAR2(200)		NOT NULL,
	"PRODUCT_IMG_NM"	VARCHAR2(30)		NOT NULL,
	"PRODUCT_IMG_LEVEL"	VARCHAR2(100)		NOT NULL,
	"PRODUCT_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "PRODUCT_IMG"."PRODUCT_IMG_NO" IS '상품이미지번호';

COMMENT ON COLUMN "PRODUCT_IMG"."PRODUCT_IMG_PATH" IS '상품이미지경로';

COMMENT ON COLUMN "PRODUCT_IMG"."PRODUCT_IMG_NM" IS '상품이미지이름';

COMMENT ON COLUMN "PRODUCT_IMG"."PRODUCT_IMG_LEVEL" IS '상품이미지레벨';

COMMENT ON COLUMN "PRODUCT_IMG"."PRODUCT_NO" IS '상품번호';


--DELIVERY_STATUS 배송상태 테이블 컬럼, 코멘트


DROP TABLE "DELIVERY_STATUS";

CREATE TABLE "DELIVERY_STATUS" (
	"DELIVERY_STATUS_CD"	NUMBER		NOT NULL,
	"DELIVERY_STATUS_NM"	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "DELIVERY_STATUS"."DELIVERY_STATUS_CD" IS '배송 상태 코드';

COMMENT ON COLUMN "DELIVERY_STATUS"."DELIVERY_STATUS_NM" IS '배송 상태명';

--ORDER_STATUS 주문상태 테이블 컬럼, 코멘트

DROP TABLE "ORDER_STATUS";

CREATE TABLE "ORDER_STATUS" (
	"ORDER_STATUS_CD"	NUMBER		NOT NULL,
	"ORDER_STATUS_NM"	VARCHAR(20)		NOT NULL
);

COMMENT ON COLUMN "ORDER_STATUS"."ORDER_STATUS_CD" IS '주문처리 상태 코드';

COMMENT ON COLUMN "ORDER_STATUS"."ORDER_STATUS_NM" IS '주문처리 상태명';

--BOARD 게시판 테이블 컬럼, 코멘트


DROP TABLE "BOARD";

CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	VARCHAR2(200)		NOT NULL,
	"BOARD_CONTENT"	CLOB		NOT NULL,
	"CREATE_DT"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MODIFY_DT"	DATE		NULL,
	"READ_COUNT"	NUMBER		NOT NULL,
	"PRODUCT_SCORE"	NUMBER		NULL,
	"BD_STATUS_CD"	NUMBER		NOT NULL,
	"CATAGORY_CD"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"PRODUCT_NO"	NUMBER		NULL
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글제목';

COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글내용';

COMMENT ON COLUMN "BOARD"."CREATE_DT" IS '작성일';

COMMENT ON COLUMN "BOARD"."MODIFY_DT" IS '마지막수정일';

COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "BOARD"."PRODUCT_SCORE" IS '별점(상품리뷰 게시판에서 사용)';

COMMENT ON COLUMN "BOARD"."BD_STATUS_CD" IS '게시글상태코드';

COMMENT ON COLUMN "BOARD"."CATAGORY_CD" IS '카테고리코드';

COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '회원고유번호';

COMMENT ON COLUMN "BOARD"."PRODUCT_NO" IS '상품번호';


--BD_STATUS 게시글 상태 테이블 컬럼, 코멘트

DROP TABLE "BD_STATUS";

CREATE TABLE "BD_STATUS" (
	"BD_STATUS_CD"	NUMBER		NOT NULL,
	"BD_STATUS_NM"	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "BD_STATUS"."BD_STATUS_CD" IS '게시글상태코드';

COMMENT ON COLUMN "BD_STATUS"."BD_STATUS_NM" IS '게시글상태이름';


--BOARD_CATEGORY 게시판 상태 테이블 컬럼, 코멘트

DROP TABLE "BOARD_CATEGORY";

CREATE TABLE "BOARD_CATEGORY" (
	"CATEGORY_CD"	NUMBER		NOT NULL,
	"CATEGORY_NM"	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "BOARD_CATEGORY"."CATEGORY_CD" IS '카테고리코드';

COMMENT ON COLUMN "BOARD_CATEGORY"."CATEGORY_NM" IS '카테고리이름';


--REPLY 댓글 테이블 컬럼, 코멘트

DROP TABLE "REPLY";

CREATE TABLE "REPLY" (
	"REPLY_NO"	NUMBER		NOT NULL,
	"REPLY_CONTENT"	VARCHAR2(1000)		NOT NULL,
	"REPLY_CREATE_DT"	DATE	DEFAULT SYSDATE	NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"REPLY_STATUS_CD"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "REPLY"."REPLY_NO" IS '댓글번호';

COMMENT ON COLUMN "REPLY"."REPLY_CONTENT" IS '댓글';

COMMENT ON COLUMN "REPLY"."REPLY_CREATE_DT" IS '댓글작성일';

COMMENT ON COLUMN "REPLY"."BOARD_NO" IS '게시글번호';

COMMENT ON COLUMN "REPLY"."REPLY_STATUS_CD" IS '댓글상태코드';

COMMENT ON COLUMN "REPLY"."MEMBER_NO" IS '회원고유번호';

--REPLY_STATUS 댓글 상태 테이블 컬럼, 코멘트


DROP TABLE "REPLY_STATUS";

CREATE TABLE "REPLY_STATUS" (
	"REPLY_STATUS_CD"	NUMBER		NOT NULL,
	"REPLY_STATUS_NM"	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "REPLY_STATUS"."REPLY_STATUS_CD" IS '댓글상태코드';

COMMENT ON COLUMN "REPLY_STATUS"."REPLY_STATUS_NM" IS '댓글상태이름';


--ADDRESS 주소록 테이블 컬럼, 코멘트


DROP TABLE "ADDRESS";

CREATE TABLE "ADDRESS" (
	"ADDRESS_NO"	NUMBER		NOT NULL,
	"ZIP_CODE"	NUMBER		NOT NULL,
	"MEMBER_ADDRESS"	VARCHAR2(100)		NOT NULL,
	"MEMBER_ADDRESS_SUB"	VARCHAR2(100)		NOT NULL,
	"DEFAULT_ADDRESS"	VARCHAR2(1)	DEFAULT 'N'	NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "ADDRESS"."ADDRESS_NO" IS '주소록 번호';

COMMENT ON COLUMN "ADDRESS"."ZIP_CODE" IS '우편번호';

COMMENT ON COLUMN "ADDRESS"."MEMBER_ADDRESS" IS '거주지';

COMMENT ON COLUMN "ADDRESS"."MEMBER_ADDRESS_SUB" IS '상세주소';

COMMENT ON COLUMN "ADDRESS"."DEFAULT_ADDRESS" IS '기본배송지 여부(Y/N)';

COMMENT ON COLUMN "ADDRESS"."MEMBER_NO" IS '회원고유번호';


--DELIVERY 배송 테이블 컬럼, 코멘트

DROP TABLE "DELIVERY";

CREATE TABLE "DELIVERY" (
	"DELIVERY_NO"	VARCHAR2(15)	NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"ZIP_CODE"	NUMBER		NOT NULL,
	"MEMBER_ADDRESS"	VARCHAR2(100)		NOT NULL,
	"MEMBER_ADDRESS_SUB"	VARCHAR2(100)		NULL,
	"RECEIVER_NAME"	NUMBER		NOT NULL,
	"RECEIVER_PHONE"	NUMBER		NOT NULL,
	"DELIVERY_DT"	DATE	DEFAULT SYSDATE	NOT NULL,
	"DELIVERY_STATUS_CD"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "DELIVERY"."DELIVERY_NO" IS '배송번호';

COMMENT ON COLUMN "DELIVERY"."MEMBER_NO" IS '회원고유번호';

COMMENT ON COLUMN "DELIVERY"."ZIP_CODE" IS '우편번호';

COMMENT ON COLUMN "DELIVERY"."MEMBER_ADDRESS" IS '주소';

COMMENT ON COLUMN "DELIVERY"."MEMBER_ADDRESS_SUB" IS '상세 주소';

COMMENT ON COLUMN "DELIVERY"."RECEIVER_NAME" IS '수령인명';

COMMENT ON COLUMN "DELIVERY"."RECEIVER_PHONE" IS '수령인 연락처';

COMMENT ON COLUMN "DELIVERY"."DELIVERY_DT" IS '주문일자';

COMMENT ON COLUMN "DELIVERY"."DELIVERY_STATUS_CD" IS '배송 상태 코드';

--PAYMENT 결제 테이블 컬럼, 코멘트

DROP TABLE "PAYMENT";

CREATE TABLE "PAYMENT" (
	"PAYMENT_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"DELIVERY_NO"	VARCHAR2(15)		NOT NULL,
	"PAYMENT_OPTION"	VARCHAR2(20)		NOT NULL,
	"PAYMENT_AMOUNT"	NUMBER		NOT NULL,
	"PAYMENT_STATE_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "PAYMENT"."PAYMENT_NO" IS '결제번호';

COMMENT ON COLUMN "PAYMENT"."MEMBER_NO" IS '회원번호';

COMMENT ON COLUMN "PAYMENT"."DELIVERY_NO" IS '배송번호';

COMMENT ON COLUMN "PAYMENT"."PAYMENT_OPTION" IS '결제수단';

COMMENT ON COLUMN "PAYMENT"."PAYMENT_AMOUNT" IS '결제금액';

COMMENT ON COLUMN "PAYMENT"."PAYMENT_STATE_NO" IS '결제상태번';

--PAYMENT_STATUS 결제상태 테이블 컬럼, 코멘트

DROP TABLE "PAYMENT_STATUS";

CREATE TABLE "PAYMENT_STATUS" (
	"PAYMENT_STATE_NO"	NUMBER		NOT NULL,
	"PAYMENT_STATE_NM"	VARCHAR2(100)		NOT NULL
);

COMMENT ON COLUMN "PAYMENT_STATUS"."PAYMENT_STATE_NO" IS '결제상태번호';

COMMENT ON COLUMN "PAYMENT_STATUS"."PAYMENT_STATE_NM" IS '결제상태';

-- 고유 키 제약조건

ALTER TABLE "MEMBER" ADD CONSTRAINT "PK_MEMBER" PRIMARY KEY (
	"MEMBER_NO"
);

ALTER TABLE "MEMBER_STATUS" ADD CONSTRAINT "PK_MEMBER_STATUS" PRIMARY KEY (
	"MEMBER_STATUS_NO"
);

ALTER TABLE "MEMBER_GRADE" ADD CONSTRAINT "PK_MEMBER_GRADE" PRIMARY KEY (
	"MEMBER_GRADE_NO"
);

ALTER TABLE "ORDER" ADD CONSTRAINT "PK_ORDER" PRIMARY KEY (
	"ORDER_NO"
);

ALTER TABLE "PRODUCT" ADD CONSTRAINT "PK_PRODUCT" PRIMARY KEY (
	"PRODUCT_NO"
);

ALTER TABLE "RECOMMENDED_PRODUCT" ADD CONSTRAINT "PK_RECOMMENDED_PRODUCT" PRIMARY KEY (
	"PRODUCT_NO"
);

ALTER TABLE "DELIVERY_STATUS" ADD CONSTRAINT "PK_DELIVERY_STATUS" PRIMARY KEY (
	"DELIVERY_STATUS_CD"
);

ALTER TABLE "ORDER_STATUS" ADD CONSTRAINT "PK_ORDER_STATUS" PRIMARY KEY (
	"ORDER_STATUS_CD"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
	"BOARD_NO"
);

ALTER TABLE "BD_STATUS" ADD CONSTRAINT "PK_BD_STATUS" PRIMARY KEY (
	"BD_STATUS_CD"
);

ALTER TABLE "BOARD_CATEGORY" ADD CONSTRAINT "PK_BOARD_CATEGORY" PRIMARY KEY (
	"CATEGORY_CD"
);

ALTER TABLE "REPLY" ADD CONSTRAINT "PK_REPLY" PRIMARY KEY (
	"REPLY_NO"
);

ALTER TABLE "REPLY_STATUS" ADD CONSTRAINT "PK_REPLY_STATUS" PRIMARY KEY (
	"REPLY_STATUS_CD"
);

ALTER TABLE "ADDRESS" ADD CONSTRAINT "PK_ADDRESS" PRIMARY KEY (
	"ADDRESS_NO"
);

ALTER TABLE "PRODUCT_CATEGORY" ADD CONSTRAINT "PK_PRODUCT_CATEGORY" PRIMARY KEY (
	"CATEGORY_NO"
);

ALTER TABLE "DELIVERY" ADD CONSTRAINT "PK_DELIVERY" PRIMARY KEY (
	"DELIVERY_NO"
);

ALTER TABLE "PAYMENT" ADD CONSTRAINT "PK_PAYMENT" PRIMARY KEY (
	"PAYMENT_NO"
);

ALTER TABLE "PAYMENT_STATUS" ADD CONSTRAINT "PK_PAYMENT_STATUS" PRIMARY KEY (
	"PAYMENT_STATE_NO"
);

ALTER TABLE "MEMBER" ADD CONSTRAINT "FK_MEMBER_STATUS_TO_MEMBER_1" FOREIGN KEY (
	"MEMBER_STATUS_NO"
)
REFERENCES "MEMBER_STATUS" (
	"MEMBER_STATUS_NO"
);

ALTER TABLE "MEMBER" ADD CONSTRAINT "FK_MEMBER_GRADE_TO_MEMBER_1" FOREIGN KEY (
	"MEMBER_GRADE_NO"
)
REFERENCES "MEMBER_GRADE" (
	"MEMBER_GRADE_NO"
);

ALTER TABLE "ORDER" ADD CONSTRAINT "FK_MEMBER_TO_ORDER_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "ORDER" ADD CONSTRAINT "FK_PRODUCT_TO_ORDER_1" FOREIGN KEY (
	"PRODUCT_NO"
)
REFERENCES "PRODUCT" (
	"PRODUCT_NO"
);

ALTER TABLE "ORDER" ADD CONSTRAINT "FK_ORDER_STATUS_TO_ORDER_1" FOREIGN KEY (
	"ORDER_STATUS_CD"
)
REFERENCES "ORDER_STATUS" (
	"ORDER_STATUS_CD"
);

ALTER TABLE "ORDER" ADD CONSTRAINT "FK_DELIVERY_TO_ORDER_1" FOREIGN KEY (
	"DELIVERY_NO"
)
REFERENCES "DELIVERY" (
	"DELIVERY_NO"
);

ALTER TABLE "PRODUCT" ADD CONSTRAINT "FK_PRODUCT_CATE_TO_PRO_1" FOREIGN KEY (
	"상품카테고리번호"
)
REFERENCES "PRODUCT_CATEGORY" (
	"CATEGORY_NO"
);

ALTER TABLE "RECOMMENDED_PRODUCT" ADD CONSTRAINT "FK_PRO_TO_RECOMDED_PRO_1" FOREIGN KEY (
	"PRODUCT_NO"
)
REFERENCES "PRODUCT" (
	"PRODUCT_NO"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BD_STATUS_TO_BOARD_1" FOREIGN KEY (
	"BD_STATUS_CD"
)
REFERENCES "BD_STATUS" (
	"BD_STATUS_CD"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BOARD_CATEGORY_TO_BOARD_1" FOREIGN KEY (
	"CATAGORY_CD"
)
REFERENCES "BOARD_CATEGORY" (
	"CATEGORY_CD"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_PRODUCT_TO_BOARD_1" FOREIGN KEY (
	"PRODUCT_NO"
)
REFERENCES "PRODUCT" (
	"PRODUCT_NO"
);

ALTER TABLE "REPLY" ADD CONSTRAINT "FK_BOARD_TO_REPLY_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);

ALTER TABLE "REPLY" ADD CONSTRAINT "FK_REPLY_STATUS_TO_REPLY_1" FOREIGN KEY (
	"REPLY_STATUS_CD"
)
REFERENCES "REPLY_STATUS" (
	"REPLY_STATUS_CD"
);

ALTER TABLE "REPLY" ADD CONSTRAINT "FK_MEMBER_TO_REPLY_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "ADDRESS" ADD CONSTRAINT "FK_MEMBER_TO_ADDRESS_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "DELIVERY" ADD CONSTRAINT "FK_MEMBER_TO_DELIVERY_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "DELIVERY" ADD CONSTRAINT "FK_DEL_STATUS_TO_DEL_1" FOREIGN KEY (
	"DELIVERY_STATUS_CD"
)
REFERENCES "DELIVERY_STATUS" (
	"DELIVERY_STATUS_CD"
);

ALTER TABLE "PAYMENT" ADD CONSTRAINT "FK_MEMBER_TO_PAYMENT_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);

ALTER TABLE "PAYMENT" ADD CONSTRAINT "FK_DELIVERY_TO_PAYMENT_1" FOREIGN KEY (
	"DELIVERY_NO"
)
REFERENCES "DELIVERY" (
	"DELIVERY_NO"
);

ALTER TABLE "PAYMENT" ADD CONSTRAINT "FK_PAYMENT_STATUS_TO_PAYMENT_1" FOREIGN KEY (
	"PAYMENT_STATE_NO"
)
REFERENCES "PAYMENT_STATUS" (
	"PAYMENT_STATE_NO"
);



ALTER TABLE "PRODUCT_IMG" ADD CONSTRAINT "PK_PRODUCT_IMG" PRIMARY KEY (
	"PRODUCT_IMG_NO"
);

ALTER TABLE "PRODUCT_IMG" ADD CONSTRAINT "FK_PRODUCT_TO_PRODUCT_IMG_1" FOREIGN KEY (
	"PRODUCT_NO"
)
REFERENCES "PRODUCT" (
	"PRODUCT_NO"
);


