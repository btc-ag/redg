
CREATE TABLE "TABLE" (
  ID   NUMBER(19)        NOT NULL PRIMARY KEY,
  NAME VARCHAR2(30 CHAR) NOT NULL
);

CREATE TABLE "GROUP" (
  ID NUMBER(19) NOT NULL PRIMARY KEY,
  "TABLE" NUMBER(19) NOT NULL,

  CONSTRAINT FK_GROUP_TABLE FOREIGN KEY (TABLE) REFERENCES "TABLE"(ID)
)