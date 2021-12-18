--****************************************************************************************
--SELECT STATEMENT
--****************************************************************************************
---------------------------회원--------------------------
SELECT * FROM MEMBER;
SELECT * FROM MEMBER_STATUS;
SELECT * FROM MEMBER_GRADE;

---------------------------회원주소록--------------------------
SELECT * FROM ADDRESS;

---------------------------상품--------------------------

SELECT * FROM PRODUCT;
SELECT * FROM PRODUCT_CATEGORY;
SELECT * FROM RECOMMENDED_PRODUCT;
SELECT * FROM PRODUCT_IMG;
SELECT * FROM PRODUCT_STATUS;


SELECT * FROM PRODUCt;



---------------------------주문--------------------------

SELECT * FROM ORDER_1;
SELECT * FROM ORDER_STATUS;

--------------------------배송--------------------------

SELECT * FROM DELIVERY;
SELECT * FROM DELIVERY_STATUS;

---------------------------결제--------------------------

SELECT * FROM PAYMENT;
SELECT * FROM PAYMENT_STATUS;

---------------------------게시판--------------------------

SELECT * FROM BOARD;
SELECT * FROM BD_STATUS;
SELECT * FROM BOARD_CATEGORY;
---------------------------댓글--------------------------

SELECT * FROM REPLY;
SELECT * FROM REPLY_STATUS;

--****************************************************************************************
--INSERT STATEMENT
--****************************************************************************************

---------------------------회원--------------------------
SELECT * FROM MEMBER;
SELECT * FROM MEMBER_STATUS;
SELECT * FROM MEMBER_GRADE;

---------------------------회원주소록--------------------------
SELECT * FROM ADDRESS;

---------------------------상품--------------------------

INSERT INTO PRODUCT (PRODUCT_NO,PRODUCT_NM,PRODUCT_PRICE,DISCOUNT,STOCK,"상품카테고리번호") values (num,'?',num,num,num,fk);
select * from product;
SELECT * FROM PRODUCT_CATEGORY;
select * from product_status;

select * from product_img;

SELECT * FROM RECOMMENDED_PRODUCT;
INSERT INTO PRODUCT_IMG (PRODUCT_IMG_NO,PRODUCT_IMG_PATH,PRODUCT_IMG_NM,PRODUCT_IMG_LEVEL,PRODUCT_NO) values (num,'?','?','?',num);


---------------------------주문--------------------------

SELECT * FROM ORDER_1;
SELECT * FROM ORDER_STATUS;

--------------------------배송--------------------------

SELECT * FROM DELIVERY;
SELECT * FROM DELIVERY_STATUS;

---------------------------결제--------------------------

SELECT * FROM PAYMENT;
SELECT * FROM PAYMENT_STATUS;

---------------------------게시판--------------------------

SELECT * FROM BOARD;
SELECT * FROM BD_STATUS;
SELECT * FROM BOARD_CATEGORY;
---------------------------댓글--------------------------

SELECT * FROM REPLY;
SELECT * FROM REPLY_STATUS;
