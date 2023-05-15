CREATE USER 'inventory'@'%' IDENTIFIED BY 'inventorypwd';
CREATE USER 'keycloak'@'%'  IDENTIFIED BY 'keycloakpwd';
CREATE USER 'order'@'%'     IDENTIFIED BY 'orderpwd';

create database `inventory-service`;
create database `keycloak`;
create database `order-service`;

grant create, delete, drop, select, update, insert, alter, index on inventory.* to inventory;
grant create, delete, drop, select, update, insert, alter, index on keycloak.* to keycloak;
grant create, delete, drop, select, update, insert, alter, index on order.* to order;

FLUSH PRIVILEGES;