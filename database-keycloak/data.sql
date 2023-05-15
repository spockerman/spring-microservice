create database `keycloak`;

create user keycloak@localhost identified by 'keycloakpwd';

grant all privileges on keycloak.* to keycloak@localhost;

flush privileges;