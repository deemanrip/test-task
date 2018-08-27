create table CUSTOMER (
  ID bigint not null,
  FIRST_NAME varchar(255) not null,
  PATRONYMIC varchar(255) not null,
  SURNAME varchar(255) not null,
  PHONE_NUMBER varchar(255) not null,

  constraint PK_CUSTOMER primary key (ID)
);

create table MECHANIC (
  ID bigint not null,
  FIRST_NAME varchar(255) not null,
  PATRONYMIC varchar(255) not null,
  SURNAME varchar(255) not null,
  HOURLY_PAYMENT double,

  constraint PK_MECHANIC primary key (ID)
);

create table CUSTOMER_ORDER (
  ID bigint not null,
  DESCRIPTION varchar(1000) not null,
  CUSTOMER_ID bigint not null,
  MECHANIC_ID bigint not null,
  CREATION_DATE date not null,
  WORK_COMPLETION_DATE date not null,
  COST double,
  ORDER_STATUS varchar(255) not null,

  constraint PK_CUSTOMER_ORDER primary key (ID),

  constraint FK_CUSTOMER_ORDER_MECHANIC foreign key (MECHANIC_ID) references MECHANIC (ID) on update no action on delete no action,
  constraint FK_CUSTOMER_ORDER_CUSTOMER foreign key (CUSTOMER_ID) references CUSTOMER (ID) on update no action on delete no action
);