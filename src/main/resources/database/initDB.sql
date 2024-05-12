create table if not exists category
(
    id        bigserial
        primary key,
    name      varchar(255),
    parent_id bigint
        references Category
            on delete cascade
);

create table if not exists  transaction
(
    id          bigserial
        primary key,
    amount      double precision default 0 not null,
    category_id bigint          default 0 not null
        references Category
            on delete set default,
    name        varchar(255),
    month       integer          default 1 not null
);

create table if not exists mcc
(
    id          serial
        primary key,
    mcc         integer,
    category_id bigint
        references Category
);