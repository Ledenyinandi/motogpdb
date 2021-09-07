create table rider (
                       id identity not null primary key,
                       name varchar(150),
                       bike_number integer,
                       country_id long
);

create table country (
                         id identity not null primary key,
                         name varchar(150)
);

create table grand_prix (
                            id identity not null primary key,
                            name varchar(150),
                            country_id long,
                            winning_rider_id long
);