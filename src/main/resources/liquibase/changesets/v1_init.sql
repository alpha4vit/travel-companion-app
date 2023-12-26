create table if not exists users(
                                    id uuid primary key not null,
                                    username varchar(100) not null unique,
                                    password varchar(100) not null,
                                    bio varchar(500),
                                    phone_number varchar(20),
                                    email varchar(50) not null unique,
                                    avatar varchar,
                                    confirmation_code varchar(5),
                                    is_email_verified boolean default false
);

create table if not exists users_roles
(
    user_id uuid references users (id) not null,
    role    varchar(255)                 not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on update no action
);

create table if not exists transports(
                                         id bigint not null primary key generated by default as identity ,
                                         name varchar(100),
                                         user_id uuid references users(id) on delete cascade not null
);

create table if not exists posts(
                                    id uuid primary key not null,
                                    title varchar(100) not null ,
                                    description varchar(300),
                                    fee varchar(50),
                                    date_there varchar(50),
                                    date_back varchar(50),
                                    post_type varchar,
                                    user_id uuid references users(id) on delete cascade not null,
                                    transport_id bigint references transports(id) on delete cascade not null
);

create table if not exists request(
                                      id bigint not null primary key generated by default as identity ,
                                      comment varchar(300),
                                      requester_id uuid references users(id) on delete cascade not null ,
                                      post_id uuid references posts(id) on delete cascade not null
);

create table if not exists transports(
    id bigint primary key generated by default as identity ,
    name varchar(100) not null ,
    user_id uuid references users(id) on delete cascade not null
);

create table if not exists reviews(
    id bigint primary key generated by default as identity ,
    title varchar(100),
    description varchar(300),
    stars int,
    creator_id uuid references users(id) on delete cascade not null ,
    user_id uuid references users(id) on delete cascade not null
);


create table if not exists responses(
    id bigint primary key generated by default as identity ,
    comment varchar,
    user_id uuid references users(id) on delete cascade not null,
    post_id uuid references posts(id) on delete cascade not null
);

