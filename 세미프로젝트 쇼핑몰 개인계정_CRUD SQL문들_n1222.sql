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
SELECT * FROM V_ORDER_INFO WHERE MEMBER_NO = 26 ORDER BY order_no DESC;

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



-------------------------member sampledata-----------------------
select * from member;
update member set member_pw =  '02DeqFdlQnpEbQRZqnvpfK7a9sO9iISEV7Hzxntivto23dJ/i16UMur0ACYxwsfAxFwzfn5yarbQnsbAtkCJAg==' where member_id = 'user01';

		SELECT * FROM PRODUCT_CATEGORY ORDER BY CATEGORY_NO1 ASC;



commit;
alter table product_category 
drop constraint PK_PRODUCT_CATEGORY;

alter table product_category add constraint PK_PRODUCT_CATEGORY_2 primary key(CATEGORY_NO2); 

select  floor(category_no1/10)  from product_category;

--제품진열페이지 제품카테고리별로 진열하는 페이지네이션
	SELECT * FROM
		(
		SELECT ROWNUM rnum ,A.*
		FROM(
		SELECT p.* , c.category_nm,
		FROM product p
		JOIN product_category c ON(p.product_category = c.category_no2)
        WHERE c.category_no2 BETWEEN 300 AND 400
		ORDER BY p.pro_status_no,p.product_no DESC) A)
		WHERE rnum BETWEEN 1 AND 10;
        
        
        select * from product;
        delete from product where product_no = 61;
        
        
        SELECT * FROM
		(
		SELECT ROWNUM rnum ,A.*
		FROM(
		SELECT p.* , c.category_nm
		FROM product p
		JOIN product_category c ON(p.product_category = c.category_no2)
        WHERE c.category_no2 BETWEEN ? AND ?
		ORDER BY p.pro_status_no,p.product_no DESC) A)
		WHERE rnum BETWEEN ? AND ?;
 
 
 -- 평균점수까지 받아오는 쿼리문 
SELECT * FROM
	(
	SELECT ROWNUM rnum ,A.*
	FROM(      
	SELECT p.* , c.category_nm, v1.avg, v1.cnt
	FROM product p
	JOIN product_category c ON(p.product_category = c.category_no2)
	LEFT JOIN v_test1 v1 ON( p.product_no = v1.product_no)
	WHERE c.category_no2 BETWEEN 300 AND 399
	ORDER BY p.pro_status_no,p.product_no DESC) A)
	WHERE rnum BETWEEN 1 AND 13
    
    ;	
commit;
delete from product_img where product_no = 61;
delete from product where product_no = 61;
        delete from board where product_no = 101;
        commit;
                delete from product where product_no = 61;

        
        SELECT p.* , c.category_nm, b.board_title,b.product_score
		FROM product p
		JOIN product_category c ON(p.product_category = c.category_no2)
       left join board b on(p.product_no=b.product_no);

--별점 기록이 없어도 상품은 보여야 된다는 점에서 left join
--그러나 받고 나서 상품을 가리키는 하나로 묶여야 한다 


delete from order_1 where product_no = 101;
commit;



select avg from v_test1;

create or replace view v_test1 as (select product_no, TO_CHAR (avg(product_score),'9.9') as avg,count(product_score) as cnt from board
where product_no is not null
group by  product_no);

SELECT p.* , c.category_nm, v1.avg, v1.cnt
FROM product p
jOIN product_category c ON(p.product_category = c.category_no2)
left join v_test1 v1 on( p.product_no = v1.product_no);

	
	


        
        
SELECT PRODUCT_NO, COUNT(PRODUCT_SCORE),AVG(PRODUCT_SCORE) FROM BOARD GROUP BY PRODUCT_NO;

SELECT PRODUCT_NO, COUNT(PRODUCT_SCORE) FROM BOARD  GROUP BY PRODUCT_NO;
SELECT * FROM BOARD;





	SELECT * FROM
		(
		SELECT ROWNUM rnum ,A.*
		FROM(
		SELECT p.* , c.category_nm,
		FROM product p
		JOIN product_category c ON(p.product_category = c.category_no2)
        WHERE c.category_no2 BETWEEN 300 AND 400
		ORDER BY p.pro_status_no,p.product_no DESC) A)
		WHERE rnum BETWEEN 1 AND 10;

-- 별점 받아오는 쿼리문
SELECT * FROM (
SELECT ROWNUM RNUM,A.* FROM (
SELECT P.PRODUCT_NO, AVG(B.PRODUCT_SCORE), COUNT(B.PRODUCT_SCORE) FROM PRODUCT P
LEFT JOIN BOARD B ON (P.PRODUCT_NO  = B.PRODUCT_NO)
WHERE P.product_category BETWEEN 300 AND 399
GROUP BY P.PRODUCT_NO)A)
WHERE RNUM BETWEEN 1 AND 10;


--주문 내역 받아오는 쿼리문

SELECT DELIVERY_DT, PRODUCT_IMG_PATH, PRODUCT_NM, ORDER_AMOUNT, D.DELIVERY_NO, PRODUCT_PRICE, ORDER_STATUS_NM FROM ORDER_1 O
JOIN MEMBER M ON(O.MEMBER_NO = M.MEMBER_NO)
JOIN PRODUCT P ON(O.PRODUCT_NO = P.PRODUCT_NO)
JOIN PRODUCT_IMG I ON(O.PRODUCT_NO = I.PRODUCT_NO)
JOIN DELIVERY D ON(o.delivery_no = D.delivery_no)
JOIN ORDER_STATUS USING(ORDER_STATUS_CD)
WHERE O.MEMBER_NO = 27
AND PRODUCT_IMG_LEVEL=0
ORDER BY 5 DESC, 1 DESC;

select * from ORDER_STATUS;


and ORDER_STATUS_NM in ('결제완료','결제취소','교환','반품')

SELECT DELIVERY_DT, PRODUCT_IMG_PATH, PRODUCT_NM, ORDER_AMOUNT, D.DELIVERY_NO, PRODUCT_PRICE, ORDER_STATUS_NM FROM ORDER_1 O
JOIN MEMBER M ON(O.MEMBER_NO = M.MEMBER_NO)
JOIN PRODUCT P ON(O.PRODUCT_NO = P.PRODUCT_NO)
JOIN PRODUCT_IMG I ON(O.PRODUCT_NO = I.PRODUCT_NO)
JOIN DELIVERY D ON(o.delivery_no = D.delivery_no)
JOIN ORDER_STATUS USING(ORDER_STATUS_CD)
WHERE O.MEMBER_NO = 27
and ORDER_STATUS_NM in ('결제취소','교환','반품')
AND PRODUCT_IMG_LEVEL=0
ORDER BY 5 DESC, 1 DESC;

select ORDER_STATUS_cd, order_status_nm 
from order_1
join order_status using (ORDER_STATUS_CD)
 where member_no=27;

select * from delivery;












	SELECT * FROM
	(
	SELECT ROWNUM rnum ,A.*
	FROM(      
	SELECT p.* , c.category_nm, v1.avg, v1.cnt
	FROM product p
	JOIN product_category c ON(p.product_category = c.category_no2)
	LEFT JOIN v_test1 v1 ON( p.product_no = v1.product_no)
	WHERE c.category_no2 BETWEEN 300 AND 399	
    ORDER BY p.pro_status_no,p.PRODUCT_PRICE * (1-p.DISCOUNT) ASC
 ) A)
	WHERE rnum BETWEEN 10 AND 20;



	ORDER BY p.pro_status_no,p.product_no DESC
     ) A)
     WHERE rnum BETWEEN 10 AND 20;
     
	ORDER BY p.pro_status_no,p.product_nm ASC
     ) A)
	WHERE rnum BETWEEN 10 AND 20;

    ORDER BY p.pro_status_no,p.PRODUCT_PRICE * (1-p.DISCOUNT) ASC
 ) A)
	WHERE rnum BETWEEN 10 AND 20;

    ORDER BY p.pro_status_no,p.PRODUCT_PRICE * (1-p.DISCOUNT) DESC
     ) A)
	WHERE rnum BETWEEN 10 AND 20;
    
    select * from product order by product_no desc; 
    
    update  product set pro_status_no = 1002 where  product_no = 141;
    commit;
    select * from order_1 where product_no =141;

delete from order_1 where product_no = 141;

delete from product where product_no = 141;

ORDER BY p.pro_status_no,p.product_no DESC : 등록일순
     ) A)
     WHERE rnum BETWEEN 10 AND 20;
     
	ORDER BY p.pro_status_no,p.product_nm DESC : 제목순
     ) A)
	WHERE rnum BETWEEN 10 AND 20;

    ORDER BY p.pro_status_no,p.PRODUCT_PRICE * (1-p.DISCOUNT) ASC : 낮은가격순
 ) A)
	WHERE rnum BETWEEN 10 AND 20;

    ORDER BY p.pro_status_no,p.PRODUCT_PRICE * (1-p.DISCOUNT) DESC : 높은 가격순
     ) A)
	WHERE rnum BETWEEN 10 AND 20;









    
    
8: pro_status_no :  상태코드
2 :product_no : 제품코드 : 최신순 
3: product_nm : 제목순






select o.* , d.* from order_1 o
join delivery d on (o.delivery_no = d.delivery_no)
where o.DELIVERY_NO = '20211225-000014'
and order_status_cd = 401;

--get은 delivery code를 req에 담아서 던져줌  


--이렇게 가져와서 LIST<order>에 다 넣고 움직여도 관계는 없음 
--아니면 하나의 서비스는 db와 두번 교신해서 order에 delevery넣는 데이터 필드 만들고 [0]번째에 넣고 반환 

-- delivery에 해당하는 order 들고 오기
select * from order_1 where DELIVERY_NO = ? and member_no = ?;

--delivery 정보 들고오기
select * from delivery where DELIVERY_NO = ? and member_no = ?;

--service메소드명 getOrderDetail 
--myPageController의 orderDetail에서 진행함




select * from delivery;

select * from order_1 where DELIVERY_NO = '20211225-000014';

select * from order_1;




















