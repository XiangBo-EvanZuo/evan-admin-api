package cn.evanzuo.admin.business.menu.mapper;

import cn.evan.zuo.common.entity.CommonMenuList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MenuMapper extends BaseMapper<CommonMenuList> {
//
//    @Select("select distinct pc.* " +
//            "from tb_wang_menu_category pc " +
//            "join tb_wang_role_category_relation rcr on pc.cat_id = rcr.category_id " +
//            "join skin.tb_wang_role role on role.id = rcr.role_id " +
//            "where cat_level = 1 and role_name in (${roleNames})"
//    )


    @Select("select *\n" +
            "from tb_wang_menu_category as c\n" +
            "where if (\n" +
            "    c.cat_level = 1, c.cat_id in (\n" +
            "        select distinct pc.cat_id\n" +
            "        from tb_wang_menu_category pc\n" +
            "        join tb_wang_role_category_relation rcr on pc.cat_id = rcr.category_id\n" +
            "        join skin.tb_wang_role role on role.id = rcr.role_id\n" +
            "        where value in (${roleNames})\n" +
            "    ),\n" +
            "1 = 1)")
    List<CommonMenuList> getMenuListByRole(@Param("roleNames") String roleNames);
}