create table "user" (
  id number(19) not null primary key,
  username varchar2(50 char) not null,
  email varchar2(100 char) not null
);

create table configuration (
  user_id number(19) not null,
  name varchar2(100 char) not null,
  value varchar2(200 char),
  
  primary key (user_id, name),
  constraint fk_configuration_user foreign key (user_id) references "user"(id) 
);