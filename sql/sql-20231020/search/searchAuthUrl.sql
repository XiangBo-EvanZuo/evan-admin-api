select tb_wang_role.value
from tb_wang_role
         left join tb_wang_url_role_relation t3
                   on tb_wang_role.id = t3.role_id
         left join tb_wang_auth_url url
                   on t3.url_id = url.id
         left join tb_wang_business_modules twbm on url.module_id = twbm.id
where concat(twbm.path, url.path) = '/user/system/getUrlList';