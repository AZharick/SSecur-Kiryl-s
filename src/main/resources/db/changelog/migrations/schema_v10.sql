drop table if exists test_auths cascade;

create table test_auths (
    id serial primary key not null,
    username varchar(20),
    "password" varchar,
    "role" varchar(10)
);

insert into test_auths values (1, 'Alex', '$2a$05$eGB8HWTbWkA9f3G9sq6piekOPIV5v4TydLHszfEnWZJKdiysz8/E.', 'lord');
insert into test_auths values (2, 'Mary', 'Mary', 'wife');
insert into test_auths values (3, 'Byte', 'Byte', 'dog');