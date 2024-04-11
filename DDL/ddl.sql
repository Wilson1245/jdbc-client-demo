drop table store;

create table store (
    id bigint primary key auto_increment,
    name varchar(64) not null ,
    address varchar(64) not null ,
    phone varchar(64) not null ,
    email varchar(64) not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

insert into store (id, name, address, phone, email)
values (1, 'store', 'taiwan', '0900000001', 'store@gmail.com');