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




---------------------------주문--------------------------

SELECT * FROM ORDER_1;
SELECT * FROM ORDER_STATUS;



--주문 정보 조회 VIEW

CREATE OR REPLACE VIEW v_order_info AS (
SELECT o.*,p.product_nm,p.product_price,p.discount,pi.product_img_path,pi.product_img_nm
FROM order_1 o 
JOIN product p ON(o.product_no = p.product_no)
JOIN product_img pi ON (p.product_no = pi.product_no)
WHERE o.order_status_cd = 400 AND pi.product_img_level=0
);
--사용법
SELECT * FROM V_ORDER_INFO WHERE MEMBER_NO = 9 ORDER BY order_no DESC;

--수량변경(up)

select * from order_status;

select * from order_1;

select * from order_1 where order_no = 2 or order_no = 3;


UPDATE order_1 SET order_status_cd = 400 WHERE member_no = 9;

commit;


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






-----------------update

--품절상품인지 아닌지 조사하는 쿼리문
update product 
set pro_status_no = (
    select decode(stock,0,1002,1001) 
    from product 
    where product_no = (select product_no from order_1 where order_no = 186)
) 
where product_no 
= (select product_no from order_1 where order_no = 186)
