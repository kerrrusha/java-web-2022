create database payment_system;

use payment_system;

create table role (
id int PRIMARY KEY AUTO_INCREMENT,
name text NOT NULL
) DEFAULT CHARSET = utf8mb4;

create table user (
id int PRIMARY KEY AUTO_INCREMENT,
first_name text NOT NULL,
last_name text,
phone text not null,
password text not null,
role_id int not null,
created_time timestamp default current_timestamp,
updated_time timestamp on update current_timestamp,
FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4;

create table money_account (
id int PRIMARY KEY AUTO_INCREMENT,
name nvarchar(150) default 'Standard Money Account',
owner_user_id int not null,
created_time timestamp default current_timestamp,
updated_time timestamp on update current_timestamp,
FOREIGN KEY (owner_user_id) REFERENCES user (id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4;

create table blocked_user (
id int PRIMARY KEY AUTO_INCREMENT,
blocked_user_id int NOT NULL,
blocked_by_user_id int NOT NULL,
created_time timestamp default current_timestamp,
FOREIGN KEY (blocked_user_id) REFERENCES user (id) ON DELETE CASCADE,
FOREIGN KEY (blocked_by_user_id) REFERENCES user (id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4;

create table blocked_money_account (
id int PRIMARY KEY AUTO_INCREMENT,
blocked_money_account_id int NOT NULL,
blocked_by_user_id int NOT NULL,
created_time timestamp default current_timestamp,
FOREIGN KEY (blocked_money_account_id) REFERENCES money_account (id) ON DELETE CASCADE,
FOREIGN KEY (blocked_by_user_id) REFERENCES user (id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4;

create table money_card (
id int PRIMARY KEY AUTO_INCREMENT,
money_account_id int not null,
balance bigint default 0,
created_time timestamp default current_timestamp,
updated_time timestamp on update current_timestamp,
FOREIGN KEY (money_account_id) REFERENCES money_account (id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4;

create table billing_status (
id int primary key auto_increment,
name text not null
) default charset = utf8mb4;

create table billing (
id int PRIMARY KEY AUTO_INCREMENT,
money_amount bigint not null,
from_money_card_id int not null,
to_money_card_id int not null,
billing_status_id int default 1,
created_time timestamp default current_timestamp,
updated_time timestamp on update current_timestamp,
FOREIGN KEY (from_money_card_id) REFERENCES money_card (id) ON DELETE CASCADE,
FOREIGN KEY (to_money_card_id) REFERENCES money_card (id) ON DELETE CASCADE,
FOREIGN KEY (billing_status_id) REFERENCES billing_status (id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4;

insert into role(name) values ('client'), ('admin');
select * from role;

insert into user(first_name, phone, password, role_id) values ('exampleName', '+380123456789', 'examplePassword', 1);
select * from user;

insert into money_account(name, owner_user_id) values ('exampleMoneyAccount', 1);
select * from money_account;

insert into money_card(money_account_id, balance) values (1, 100);
select * from money_card;

insert into billing_status(name) values ('prepared'), ('done');
select * from billing_status;

insert into billing(money_amount, from_money_card_id, to_money_card_id) values (50, 1, 1);
select * from billing;

drop database payment_system;