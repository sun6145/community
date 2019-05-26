## 应用目标
使用springboot进行开发搭建一个web社区

```sql
-- auto-generated definition
create table USER
(
    ID           INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_6AEA4BA5_93BC_4D8F_BBA5_C2EE54198D30) auto_increment,
    NAME         VARCHAR(50),
    ACCOUNT_ID   VARCHAR(100),
    TOKEN        VARCHAR(36),
    GMT_MODIFIED BIGINT,
    GMT_CREATE   BIGINT,
    constraint USER_PK
        primary key (ID)
);


```