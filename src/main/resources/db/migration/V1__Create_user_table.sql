create table USER
(
    ID           int auto_increment primary key ,
    NAME         VARCHAR(50),
    ACCOUNT_ID   VARCHAR(100),
    TOKEN        VARCHAR(36),
    GMT_MODIFIED BIGINT,
    GMT_CREATE   BIGINT,
    constraint USER_PK
        primary key (ID)
);