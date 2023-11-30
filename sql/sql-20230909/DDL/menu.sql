create table if not exists menu.tb_wang_menu_category
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
    comment '商品三级分类' auto_increment = 1443;

create index parent_cid
    on menu.tb_wang_menu_category (parent_cid);

create table if not exists menu.tb_wang_role_category_relation
(
    id          int auto_increment
        primary key,
    category_id int null,
    role_id     int null,
    constraint id
        unique (id)
)
    auto_increment = 46;

create table if not exists menu.tb_wang_user_dept
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
    auto_increment = 1443;

create table if not exists menu.tb_wang_user_dept_relation
(
    id      int auto_increment
        primary key,
    user_id int null,
    dept_id int null,
    constraint id
        unique (id)
);


