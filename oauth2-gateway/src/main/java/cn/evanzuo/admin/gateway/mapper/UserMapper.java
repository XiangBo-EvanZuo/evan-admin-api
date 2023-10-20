package cn.evanzuo.admin.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<Object> {
    @Select("select tb_wang_role.value\n" +
            "from tb_wang_role\n" +
            "left join tb_wang_url_role_relation t3\n" +
            "    on tb_wang_role.id = t3.role_id\n" +
            "left join tb_wang_auth_url url\n" +
            "    on t3.url_id = url.id\n" +
            "left join tb_wang_business_modules twbm on url.module_id = twbm.id\n" +
            "where concat(twbm.path, url.path) = #{url};")
    List<String> getData(@Param("url") String url);
}