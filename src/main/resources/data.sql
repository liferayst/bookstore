INSERT INTO BOOK_TYPE (ID, TYPE_DESC) VALUES (1, 'FICTION');
INSERT INTO BOOK_TYPE (ID, TYPE_DESC) VALUES (2, 'COMIC');
INSERT INTO BOOK_TYPE (ID, TYPE_DESC) VALUES (3, 'THRILLER');
INSERT INTO BOOK_TYPE (ID, TYPE_DESC) VALUES (4, 'HUMOR');
INSERT INTO BOOK_TYPE (ID, TYPE_DESC) VALUES (5, 'RELIGIOUS');

INSERT INTO BOOK(NAME,DESCRIPTION,AUTHOR,BOOK_TYPE,PRICE,ISBN) VALUES ('Book-1','Book-1 Desc','Book-1 Author',1,21.50,1111);
INSERT INTO BOOK(NAME,DESCRIPTION,AUTHOR,BOOK_TYPE,PRICE,ISBN) VALUES ('Book-2','Book-2 Desc','Book-2 Author',2,15.75,1122);
INSERT INTO BOOK(NAME,DESCRIPTION,AUTHOR,BOOK_TYPE,PRICE,ISBN) VALUES ('Book-3','Book-3 Desc','Book-3 Author',3,36.50,1133);
INSERT INTO BOOK(NAME,DESCRIPTION,AUTHOR,BOOK_TYPE,PRICE,ISBN) VALUES ('Book-4','Book-4 Desc','Book-4 Author',4,55.25,1144);



INSERT INTO DISCOUNT (ID, BOOK_TYPE,DISCOUNT,DISCOUNT_TYPE,START_DATE,IS_ACTIVE) VALUES (1, 1, 0.1,1,current_date,1);
INSERT INTO DISCOUNT (ID, BOOK_TYPE,DISCOUNT,DISCOUNT_TYPE,START_DATE,IS_ACTIVE) VALUES (2, 5, 0.2,1,current_date,1);
INSERT INTO DISCOUNT (ID, PROMOCODE,DISCOUNT,DISCOUNT_TYPE,START_DATE,IS_ACTIVE) VALUES (3, 'GET10OFF', 10,2,current_date,1);
INSERT INTO DISCOUNT (ID, PROMOCODE,DISCOUNT,DISCOUNT_TYPE,START_DATE,IS_ACTIVE) VALUES (4, 'GET20OFF', 15,2,current_date,1);