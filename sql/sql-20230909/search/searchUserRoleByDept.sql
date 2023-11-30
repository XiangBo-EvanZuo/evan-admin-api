select twu.id,
       twu.username,
       cast(group_concat(twr.value) as char) as roles,
       nick_name,
       create_time,
       email,
       remark
from tb_wang_role
         left join tb_wang_user_role_relation twurr on tb_wang_role.id = twurr.user_id
         left join tb_wang_role twr on twurr.role_id = twr.id
         left join tb_wang_user twu on twurr.user_id = twu.id
         left join tb_wang_user_extra twue on twu.id = twue.user_id
         left join tb_wang_user_dept_relation twudr on twu.id = twudr.user_id
where twudr.dept_id = 1 and nick_name like concat('%', 'a', '%') and username like concat('%', '', '%')
group by twu.id;