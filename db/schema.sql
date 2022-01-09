CREATE TABLE accident (
  id serial primary key,
  name varchar(2000),
  text varchar(2000),
  address varchar(2000),
  type_id int references accident_type(id)
);


CREATE TABLE accident_type (
  id serial primary key,
  name varchar(2000)
);

CREATE TABLE accident_rule (
  id serial primary key,
  name varchar(2000)
);

insert into accident_type(name) values
                                      ('машина и пешеход'),
                                      ('машина и велосипед'),
                                      ('машина и светофор');

insert into accident_rule(name) values
                                      ('list 1'),
                                      ('list 2'),
                                      ('list 3');