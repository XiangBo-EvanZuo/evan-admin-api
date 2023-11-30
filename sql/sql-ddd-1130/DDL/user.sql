create table if not exists tb_wang_auth_url
(
    id        int auto_increment
    primary key,
    status    int default 1 null,
    module_id int           null,
    path      varchar(50)   null,
    deleted   int default 0 null,
    constraint id
    unique (id)
    )
    auto_increment = 1;

create table if not exists tb_wang_business_modules
(
    id   int auto_increment
    primary key,
    path varchar(100) default '' null,
    name varchar(100) default '' null,
    constraint id
    unique (id)
    )
    auto_increment = 1;

create table if not exists tb_wang_role
(
    id          int           not null,
    value       varchar(100)  null,
    role_name   varchar(100)  null,
    remarks     varchar(100)  null,
    role_status int default 1 null
    );

create table if not exists tb_wang_url_role_relation
(
    id      int auto_increment
    primary key,
    role_id mediumtext null,
    url_id  mediumtext null,
    constraint id
    unique (id)
    )
    auto_increment = 1;

create table if not exists tb_wang_user
(
    id                      int auto_increment
    primary key,
    username                varchar(100)  null,
    password                varchar(100)  null,
    mobile                  varchar(100)  null,
    pwd                     varchar(100)  null,
    deleted                 int default 0 null,
    version                 int           null,
    avatar_url              varchar(100)  null,
    enabled                 int default 1 null,
    credentials_non_expired int default 1 null,
    account_non_expired     int default 1 null,
    account_non_locked      int default 1 null,
    constraint id
    unique (id)
    )
    auto_increment = 1;

create table if not exists tb_wang_user_extra
(
    user_id     int auto_increment
    primary key,
    create_time datetime default CURRENT_TIMESTAMP null,
    email       varchar(50)                        null,
    nick_name   varchar(50)                        null,
    remark      varchar(100)                       null,
    constraint user_id
    unique (user_id)
    )
    comment '用户额外信息表' auto_increment = 1;

create table if not exists tb_wang_user_role_relation
(
    id      mediumtext null,
    role_id int        null,
    user_id int        null
);

create table if not exists tb_wang_menu_category
(
    cat_id                bigint auto_increment comment '分类id'
    primary key,
    name                  char(50)                     null comment '分类名称',
    parent_cid            bigint                       null comment '父分类id',
    cat_level             int                          null comment '层级',
    show_status           tinyint     default 1        null comment '是否显示[0-不显示，1显示]',
    sort                  int         default 0        null comment '排序',
    component             varchar(50) default 'LAYOUT' null,
    path                  varchar(100)                 null,
    redirect              varchar(50)                  null,
    hide_children_in_menu int         default 0        null,
    icon                  varchar(50)                  null,
    title                 varchar(50)                  null,
    hide_menu             tinyint(1)  default 0        null,
    hide_breadcrumb       tinyint(1)  default 0        null,
    current_active_menu   varchar(50)                  null
    )
    comment '商品三级分类' auto_increment = 1444;

create index parent_cid
    on tb_wang_menu_category (parent_cid);


create table if not exists tb_wang_role_category_relation
(
    id          int auto_increment
    primary key,
    category_id int null,
    role_id     int null,
    constraint id
    unique (id)
    )
    auto_increment = 1;

create table if not exists tb_wang_user_dept
(
    cat_id      bigint auto_increment
    primary key,
    dept_name   varchar(50)                        null,
    parent_cid  bigint   default 1                 null,
    cat_level   int      default 1                 null,
    sort        int      default 0                 null,
    create_time datetime default CURRENT_TIMESTAMP null,
    remark      varchar(100)                       null,
    status      int      default 0                 null,
    constraint cat_id
    unique (cat_id)
    )
    auto_increment = 1;

create table if not exists tb_wang_user_dept_relation
(
    id      int auto_increment
    primary key,
    user_id int null,
    dept_id int null,
    constraint id
    unique (id)
    )
    auto_increment = 1;

