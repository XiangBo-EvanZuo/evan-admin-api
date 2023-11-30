
select
    twr.value as role_value,
    role_name,
    id,
    role_status as status,
    remarks as remark,
    cast(ifnull(menu, '') as char) as menu,
    cast(ifnull(url_list, '') as char) as url_list
from skin.tb_wang_role twr
         left join (
    select
        r.value,
        group_concat(cat_id) as menu
    from skin.tb_wang_role r
             left join menu.role_category_relation rcr on rcr.role_id = r.id
             left join menu.tb_wang_menu_category pc on pc.cat_id = rcr.category_id and pc.cat_level = 1

    group by r.value
) s on s.value = twr.value
         left join (select
                        r.value,
                        group_concat(url_id) as url_list
                    from skin.tb_wang_role r
                             left join skin.tb_wang_url_role_relation twurr on twurr.role_id = r.id
                             left join skin.tb_wang_auth_url twau on twurr.url_id = twau.id
                    group by r.value) b on b.value = twr.value;







select
    r.value,
    group_concat(url_id) as urls
from skin.tb_wang_role r
         left join skin.tb_wang_url_role_relation twurr on twurr.role_id = r.id
         left join skin.tb_wang_auth_url twau on twurr.url_id = twau.id
group by r.value
