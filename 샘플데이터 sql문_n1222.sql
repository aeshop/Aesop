--제품 테이블, 제품 참조 이미지 테이블에 샘플데이터 추가

select * from product order by product_no desc;
select * from product_img;
select * from product_category;

-- PRODUCT 테이블 샘플 데이터 추가 (10개씩)
--  샘플데이터 개수(LOOP), category번호, 제품수량과 제품상태코드 변경으로 원하는 카테고리에 등록 가능
--제품의 수량이 1 이상이면 1001로, 0이면 1002로(품절 정렬위해서)
BEGIN
    FOR I IN 1..20 LOOP
        INSERT INTO product
        VALUES(SEQ_product_NO.NEXTVAL,
                SEQ_product_NO.CURRVAL || '번째 제품_샘플데이터_'||(select category_nm from product_category where category_no2 = 312),
                10000,
                0, 0, 312,1002);
    END LOOP;
END;
/

commit;


--PRODUCT_IMG 테이블 더미 데이터 이미지 참조 추가 : LOOP의 I를 아까 생성했던 샘플데이터들 범위만큼 지정
BEGIN
    FOR I IN 341..360 LOOP
        INSERT INTO product_img
        VALUES(SEQ_product_img_NO.NEXTVAL,
                '/resources/images/product/','dummy.jpg',0,I);
    END LOOP;
END;
/
commit;



