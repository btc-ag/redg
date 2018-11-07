-- as with all test SQL files, the schema created here does not confirm to best practices in database design
-- it simply uses somewhat relatable scenarios and creates schemas that test certain features of RedG


create table TABLE (
  ID number(19) not null primary key,
  NAME varchar2(50 char) not null
);
