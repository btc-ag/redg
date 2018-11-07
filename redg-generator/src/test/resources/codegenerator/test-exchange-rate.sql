-- Test

CREATE TABLE EXCHANGE_REF (
  ID   NUMBER(19)        NOT NULL, -- 1
  NAME VARCHAR2(30 CHAR) NOT NULL, -- Lea

  CONSTRAINT pk_EXCHANGE_REF PRIMARY KEY (ID)
);

CREATE TABLE EXCHANGE_RATE (
  ID              NUMBER(19)        NOT NULL, -- 11, 12
  REFERENCE_ID    NUMBER(19)        NOT NULL, -- 1, 1
  FIRST_NAME      VARCHAR2(50 CHAR), -- Flo1, Flo2
  PREV_FIRST_NAME VARCHAR2(50 CHAR), -- null, Flo1

  CONSTRAINT pk_EXCHANGE_RATE_PK PRIMARY KEY (ID),
  CONSTRAINT fk_EXCHANGE_RATE_COMPOSITE FOREIGN KEY (REFERENCE_ID, PREV_FIRST_NAME) REFERENCES EXCHANGE_RATE(REFERENCE_ID, FIRST_NAME),
  CONSTRAINT fk_EXCHANGE_REF_REFERING FOREIGN KEY (REFERENCE_ID) REFERENCES EXCHANGE_REF(ID)
);

-- alter table EXCHANGE_RATE add constraint FK_EXRATE_REF foreign key(REFERENCE_ID, PREV_END) references EXCHANGE_RATE(REFERENCE_ID, INTERVAL_END);


