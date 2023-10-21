
select
    module_id,
    group_concat(twau.id)
from skin.tb_wang_auth_url twau
         left join skin.tb_wang_business_modules twbm on twau.module_id = twbm.id
group by module_id;


select twau.module_id, group_concat(twau.id) from skin.tb_wang_business_modules
    left join tb_wang_auth_url twau on tb_wang_business_modules.id = twau.module_id
group by twau.module_id;


# right
select
    twau.id,
    twau.status,
    twbm.name as module_name,
    module_id,
    twau.path,
    concat(twbm.path, twau.path) as url
from skin.tb_wang_auth_url twau
         left join skin.tb_wang_business_modules twbm on twau.module_id = twbm.id
where if(null is not null , module_id = null, true);