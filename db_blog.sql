create table t_article
(
    id          int auto_increment
        primary key,
    b_id        varchar(50)  null,
    url         varchar(300) null,
    create_time varchar(50)  null,
    title       varchar(300) null
);

create table t_blogger
(
    id            varchar(50) not null
        primary key,
    article_count int         null,
    page_count    int         null
);

create table t_picture
(
    id   int auto_increment
        primary key,
    a_id int          null,
    url  varchar(300) null
);