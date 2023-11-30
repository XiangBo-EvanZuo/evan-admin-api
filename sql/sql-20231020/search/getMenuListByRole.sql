select *
from tb_wang_menu_category as c
where if (
    c.cat_level = 1, c.cat_id in (
            select distinct pc.cat_id
            from tb_wang_menu_category pc
                     join tb_wang_role_category_relation rcr on pc.cat_id = rcr.category_id
                     join tb_wang_role role on role.id = rcr.role_id
            where value in ('USER', 'ADMIN')
        ),
                  true)