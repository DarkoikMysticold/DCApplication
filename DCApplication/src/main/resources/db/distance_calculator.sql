create database if not exists distance_calculator;
use distance_calculator;
create table if not exists cities
(
id int primary key auto_increment,
cityName varchar(50) not null unique,
latitude decimal (10,8) not null,
longitude decimal (10,8) not null
);

create table if not exists distance
(
fromCity int,
toCity int,
distance int unsigned,
foreign key(fromCity) references cities(id) on update cascade,
foreign key(toCity) references cities(id) on update cascade
);

insert into cities (cityName, latitude, longitude)
values 
('Moscow', 55.7522200, 37.6155600),
('Samara', 53.2000700, 50.1500000),
('Nojyabrsk', 63.1930900, 75.4372800);

insert into distance(fromCity, toCity, distance)
values
(1, 2, 105900),
(1, 3, 321500);

