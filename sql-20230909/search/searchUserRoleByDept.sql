select twu.id, twu.username, cast(group_concat(twr.value) as char) as roles
from skin.tb_wang_role
left join skin.tb_wang_user_role_relation twurr on tb_wang_role.id = twurr.user_id
left join skin.tb_wang_role twr on twurr.role_id = twr.id
left join skin.tb_wang_user twu on twurr.user_id = twu.id
left join skin.tb_wang_user_extra twue on twu.id = twue.user_id
left join menu.tb_wang_user_dept_relation twudr on twu.id = twudr.user_id
where twudr.dept_id = 1
group by twu.id;

