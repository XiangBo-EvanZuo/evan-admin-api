package cn.evanzuo.admin.business.menu.mapper;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.zuo.common.entity.EvanUser;
import cn.evanzuo.admin.business.menu.VO.DeptListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Objects;


@Mapper
public interface DeptMapper extends BaseMapper<CommonMenuList> {
    @Select("select *\n" +
            "from tb_wang_user_dept as c\n" +
            "where if (\n" +
            "    c.cat_level = 1, c.cat_id in (\n" +
            "        select distinct pc.cat_id\n" +
            "        from pms_category pc\n" +
            "        join role_category_relation rcr on pc.cat_id = rcr.category_id\n" +
            "        join skin.tb_wang_role role on role.id = rcr.role_id\n" +
            "        where role_id = 1 and value in (${roleNames})\n" +
            "    ),\n" +
            "1 = 1)")
    List<DeptListVo> getRoleNames(@Param("roleNames") String roleNames);

    @Select("select *\n" +
            "from skin.tb_wang_user\n" +
            "left join menu.tb_wang_user_dept_relation twudr on tb_wang_user.id = twudr.user_id\n" +
            "where twudr.dept_id = #{deptId}")
    IPage<EvanUser> getRoleNamesPage(IPage page, @Param("deptId") String deptId);
}