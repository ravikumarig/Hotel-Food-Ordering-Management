-------------------------------------------------------------------------------------
CREATE TABLE fp.rigcustomer (
	email varchar(30) NOT NULL unique primary key,
    name varchar(30) not null,
    password varchar(30) not null 
)
ENGINE=InnoDB
COMMENT='customer login details';
-------------------------------------------------------------------------------------

CREATE TABLE fp.rigmanager (
	email varchar(30) NOT NULL unique primary key,
    name varchar(30) not null,
    password varchar(30) not null 
)
ENGINE=InnoDB
COMMENT='manager login details';
-------------------------------------------------------------------------------------

CREATE TABLE fp.rigfood_item (
	item_id integer NOT NULL unique primary key,
    item_name varchar(30) not null,
    cost float not null,
	category varchar(30) not null
)
ENGINE=InnoDB
COMMENT='Food Menu';

insert into fp.rigfood_item values(1,'Supreme Nachos',8.00,'Starters');
insert into fp.rigfood_item values(2,'Potato Skins',7.50,'Starters');
insert into fp.rigfood_item values(3,'Beef Chili',4.95,'Soup');
insert into fp.rigfood_item values(4,'Red Ale Cheddar',4.95,'Soup');
insert into fp.rigfood_item values(5,'Angus Beef Burger',9.50,'Burgers');
insert into fp.rigfood_item values(6,'Mushroom Swiss Burger',10.25,'Burgers');
insert into fp.rigfood_item values(7,'House Salad',4.50,'Salads');
insert into fp.rigfood_item values(8,'Greek Salad',7.95,'Salads');

-------------------------------------------------------------------------------------

CREATE TABLE fp.rigorder (
	order_id BIGINT NOT NULL primary key,
	email varchar(30) NOT NULL,
	item_id integer NOT NULL,
    item_name varchar(30) NOT NULL,
	quantity integer NOT NULL,
    cost float NOT NULL,
	status ENUM('active','inactive') NOT NULL,
	trans_id BIGINT,
	constraint fk_user foreign key(email) references fp.rigcustomer(email),
	constraint fk_item foreign key(item_id) references fp.rigfood_item(item_id),
	constraint fk_billTrans foreign key(trans_id) references fp.rigbill(trans_id)
)
ENGINE=InnoDB
COMMENT='Order Table';


-------------------------------------------------------------------------------------

CREATE TABLE fp.rigbill (
	trans_id BIGINT NOT NULL primary key,
	email varchar(30) NOT NULL,
    final_cost float NOT NULL,
	status ENUM('billed','unbilled'),
	constraint fk_user_billtable foreign key(email) references fp.rigcustomer(email)
)
ENGINE=InnoDB
COMMENT='Bill Table';





















































































