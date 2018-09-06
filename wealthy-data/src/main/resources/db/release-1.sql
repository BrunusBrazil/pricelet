create database xpender;
use xpender;

create table acc_group(
	id int primary key auto_increment,
	description varchar(100) UNIQUE NOT NULL,
	user_id int
);

create table acc_subgroup(
	id int primary key auto_increment,
	description varchar(100) UNIQUE,    
    acc_group_id int,
	foreign key (acc_group_id) references acc_group (id),
	user_id int
);


create table xtransaction(
	id int primary key auto_increment,
	description varchar(1024),
    valor double,
    entrada boolean,
    datatrans datetime,  
    acc_group_id  int,
    acc_subgroup_id int,
	transaction_type varchar(50),
    create_date date,
    last_update date,
	user_id int,
	foreign key (acc_group_id) references acc_group (id),
	foreign key (acc_subgroup_id) references acc_subgroup (id)
);


create table user(
	id int primary key auto_increment,
 	name varchar(400),
	email varchar(60) not null,
	password varchar(12)	
);

create table forecast(
   id int primary key auto_increment,
   acc_group_id  int,
   acc_subgroup_id int,
   user_id int, 
   cashin boolean,
   period date not null,
   total double not null,
   foreign key (acc_group_id) references acc_group (id),
   foreign key (acc_subgroup_id) references acc_subgroup (id),
   foreign key (user_id) references user (id)
);

	