
create table TREE_ELEMENT (
  ID number(19) not null primary key,
  VALUE varchar2(50 char),
  PARENT_ID number(19) not null,

  constraint FK_TREE_ELEMENT_PARENT foreign key (PARENT_ID) REFERENCES TREE_ELEMENT(ID)
);