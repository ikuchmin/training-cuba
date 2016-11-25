-- begin TRAINING_CITY
create table TRAINING_CITY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(4),
    BY_DEFAULT boolean,
    --
    primary key (ID)
)^
-- end TRAINING_CITY
-- begin TRAINING_CAR_SERVICE_CENTER
create table TRAINING_CAR_SERVICE_CENTER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    PHONE varchar(20),
    CITY_ID varchar(36) not null,
    ADDRESS varchar(255),
    OWNER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TRAINING_CAR_SERVICE_CENTER
-- begin TRAINING_CUSTOMER
create table TRAINING_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(255) not null,
    PHONE varchar(20) not null,
    EMAIL varchar(255),
    --
    primary key (ID)
)^
update TRAINING_CUSTOMER set DTYPE = 'training$Customer' where DTYPE is null ^
-- end TRAINING_CUSTOMER
-- begin TRAINING_INDIVIDUAL
create table TRAINING_INDIVIDUAL (
    ID varchar(36) not null,
    --
    PASSPORT_NO varchar(10) not null,
    --
    primary key (ID)
)^
-- end TRAINING_INDIVIDUAL
-- begin TRAINING_COMPANY
create table TRAINING_COMPANY (
    ID varchar(36) not null,
    --
    INN varchar(10) not null,
    --
    primary key (ID)
)^
-- end TRAINING_COMPANY
-- begin TRAINING_EMPLOYEE
create table TRAINING_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255) not null,
    LAST_NAME varchar(255) not null,
    BIRTH_DATE date,
    EMAIL varchar(255),
    SALARY double precision not null,
    CENTER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TRAINING_EMPLOYEE
-- begin TRAINING_REPAIR
create table TRAINING_REPAIR (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DESCRIPTION longvarchar,
    EMPLOYEE_ID varchar(36) not null,
    CENTER_ID varchar(36) not null,
    CUSTOMER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TRAINING_REPAIR
-- begin TRAINING_CUSTOMER_CAR_SERVICE_CENTER_LINK
create table TRAINING_CUSTOMER_CAR_SERVICE_CENTER_LINK (
    CUSTOMER_ID varchar(36) not null,
    CAR_SERVICE_CENTER_ID varchar(36) not null,
    primary key (CUSTOMER_ID, CAR_SERVICE_CENTER_ID)
)^
-- end TRAINING_CUSTOMER_CAR_SERVICE_CENTER_LINK
