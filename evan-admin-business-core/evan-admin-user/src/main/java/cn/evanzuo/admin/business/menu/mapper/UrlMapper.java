package cn.evanzuo.admin.business.menu.mapper;

import cn.evanzuo.admin.business.menu.entity.AuthUrl;
import cn.evanzuo.admin.business.menu.entity.BusinessModuleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UrlMapper extends BaseMapper<AuthUrl> {

    @Select("select\n" +
            "    twau.id,\n" +
            "    twau.status,\n" +
            "    twbm.name as module_name,\n" +
            "    concat(twbm.path, twau.path) as url,\n" +
            "    module_id,\n" +
            "    twau.path\n" +
            "from tb_wang_auth_url twau\n" +
            "    left join tb_wang_business_modules twbm on twau.module_id = twbm.id\n" +
            "where if(#{moduleId} is not null , module_id = #{moduleId}, true) and deleted = 0")
    IPage<AuthUrl> getUrlList(IPage page, @Param("moduleId") Integer moduleId);

    @Select("select id, path, name from tb_wang_business_modules\n")
    List<BusinessModuleEntity> getModuleList();

    @Select("update tb_wang_auth_url twau\n" +
            "    set path = ifnull(#{path}, twau.path),\n" +
            "        module_id = ifnull(${moduleId}, twau.module_id)\n" +
            "where id = #{id};")
    void updateAuthUrl(
            @Param("id") Integer id,
            @Param("moduleId") Integer moduleId,
            @Param("path") String path
    );

    @Select("insert into tb_wang_auth_url (module_id, path)\n" +
            "values (#{moduleId}, #{path})")
    void addAuthUrl(
            @Param("moduleId") Integer moduleId,
            @Param("path") String path
    );

    @Select("update tb_wang_auth_url\n" +
            "set deleted = 1\n" +
            "where id = #{id}")
    void deleteAuthUrl(@Param("id") Integer id);

    void updateRoleUrl(
            @Param("roleId") Integer roleId,
            @Param("list") List<Integer> roleUrlItems
    );

    void updateRoleMenu(
            @Param("roleId") Integer roleId,
            @Param("list") List<Integer> roleMenuItems
    );
}