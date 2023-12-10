package cn.evan.admin.pay.infrastructure.repository.menu.mapper;

import cn.evan.admin.pay.infrastructure.repository.menu.UserMenuPO;
import cn.evan.zuo.common.entity.CommonMenuList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMenuMapper extends BaseMapper<CommonMenuList> {
    @Select("select *\n" +
            "from tb_wang_menu_category as c\n" +
            "where if (\n" +
            "    c.cat_level = 1, c.cat_id in (\n" +
            "        select distinct pc.cat_id\n" +
            "        from tb_wang_menu_category pc\n" +
            "        join tb_wang_role_category_relation rcr on pc.cat_id = rcr.category_id\n" +
            "        join tb_wang_role role on role.id = rcr.role_id\n" +
            "        where value in (${roleNames})\n" +
            "    ),\n" +
            "1 = 1)")
    List<UserMenuPO> getMenuListByRole(@Param("roleNames") String roleNames);
}
