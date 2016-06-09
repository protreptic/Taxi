create table track_point (
    id integer primary key autoincrement,
    track_id integer,
    provider varchar(255),
    latitude double,
    longitude double,
    speed double,
    accuracy double
);