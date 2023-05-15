create database `order-service`;

create user `order`;

grant alter, create, create temporary tables, delete, drop, select, update on *.* to `order`@localhost;
