create database `inventory-service`;

create user inventory;

grant alter, create, create temporary tables, delete, drop, select, update on *.* to inventory@localhost;