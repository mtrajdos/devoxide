insert into users (user_id, firstname, lastname, totalBasketPrice) values (USER_SEQ.nextval, 'Michal', 'Trajdos', '0.00');
insert into users (user_id, firstname, lastname, totalBasketPrice) values (USER_SEQ.nextval, 'Alfred', 'Kowalski', '0.00');
insert into users (user_id, firstname, lastname, totalBasketPrice) values (USER_SEQ.nextval, 'Simone', 'Simons', '0.00');

insert into items (item_id, itemName, itemDescription, itemPrice, itemStock) values (ITEM_SEQ.nextval, 'RTX 2080', 'Top of the line graphics card by NVIDIA packed with latest technology', '700.00', '50');
insert into items (item_id, itemName, itemDescription, itemPrice, itemStock) values (ITEM_SEQ.nextval, 'RTX 2070', 'More of a budget option if you still need raytracing but not the latest tech', '450.00', '50');
insert into items (item_id, itemName, itemDescription, itemPrice, itemStock) values (ITEM_SEQ.nextval, 'RTX 2070', 'Cheapest RTX option, still great performance', '330.00', '10');
insert into items (item_id, itemName, itemDescription, itemPrice, itemStock) values (ITEM_SEQ.nextval, 'Alienware AW2518HF', 'Stunning in performance 25 inch gaming monitor with 240Hz refresh rate', '220.00', '0');
insert into items (item_id, itemName, itemDescription, itemPrice, itemStock) values (ITEM_SEQ.nextval, 'Asus XG258Q', 'Thin bezel, high refresh rate - the perfect monitor for eSports and first-person shooters.', '400.00', '1');

insert into USERS_USERBASKETITEMS (USERS_USER_ID,  USERBASKETITEMS_ITEM_ID) values (1, 1);
insert into USERS_USERBASKETITEMS (USERS_USER_ID,  USERBASKETITEMS_ITEM_ID) values (1, 2);
insert into USERS_USERBASKETITEMS (USERS_USER_ID,  USERBASKETITEMS_ITEM_ID) values (1, 3);

insert into USERS_USERBASKETITEMS (USERS_USER_ID,  USERBASKETITEMS_ITEM_ID) values (2, 4);
insert into USERS_USERBASKETITEMS (USERS_USER_ID,  USERBASKETITEMS_ITEM_ID) values (2, 5);
