package cn.evan.admin.user.domain.mapper;

import cn.evan.admin.user.sdk.feign.dto.DeptListDTO;
import cn.evan.admin.user.sdk.feign.dto.RoleListDTO;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.zuo.common.entity.EvanUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface DeptMapper extends BaseMapper<CommonMenuList> {
    @Select("select *\n" +
            "from tb_wang_user_dept as c\n" +
            "where if (\n" +
            "    c.cat_level = 1, c.cat_id in (\n" +
            "        select distinct pc.cat_id\n" +
            "        from tb_wang_menu_category pc\n" +
            "        join tb_wang_role_category_relation rcr on pc.cat_id = rcr.category_id\n" +
            "        join tb_wang_role role on role.id = rcr.role_id\n" +
            "        where value in (${roleNames})\n" +
            "    ),\n" +
            "1 = 1)")
    List<DeptListDTO> getDeptList(@Param("roleNames") String roleNames);

//    @Select("select *\n" +
//            "from tb_wang_user\n" +
//            "left join tb_wang_user_extra twue on tb_wang_user.id = twue.user_id\n" +
//            "left join tb_wang_user_dept_relation twudr on tb_wang_user.id = twudr.user_id\n" +
//            "where twudr.dept_id = #{deptId}")
    @Select("select twu.id, twu.username, cast(group_concat(twr.value) as char) as roles, nick_name,\n" +
            "       create_time,\n" +
            "       email,\n" +
            "       remark\n" +
            "from tb_wang_role\n" +
            "left join tb_wang_user_role_relation twurr on tb_wang_role.id = twurr.user_id\n" +
            "left join tb_wang_role twr on twurr.role_id = twr.id\n" +
            "left join tb_wang_user twu on twurr.user_id = twu.id\n" +
            "left join tb_wang_user_extra twue on twu.id = twue.user_id\n" +
            "left join tb_wang_user_dept_relation twudr on twu.id = twudr.user_id\n" +
            "where twudr.dept_id = #{deptId} and nick_name like concat('%', #{nickname}, '%') \n" +
            "and username like concat('%', #{account}, '%') \n" +
            "group by twu.id")
    IPage<EvanUser> getRoleNamesPage(
            IPage page,
            @Param("deptId") String deptId,
            @Param("nickname") String nickname,
            @Param("account") String account
    );

    @Select("select\n" +
            "    value as role_value,\n" +
            "    role_name,\n" +
            "    id,\n" +
            "    role_status as status,\n" +
            "    remarks as remark\n" +
            "from tb_wang_role")
    List<RoleListDTO> getAllRolesList();

    @Select("select count(*) as count from tb_wang_user\n" +
            "where username = #{username}")
    Integer exist(@Param("username") String username);

    @Select("select\n" +
            "    twr.value as role_value,\n" +
            "    role_name,\n" +
            "    id,\n" +
            "    role_status as status,\n" +
            "    remarks as remark,\n" +
            "    cast(ifnull(menu, '') as char) as menu,\n" +
            "    cast(ifnull(url_list, '') as char) as url_list\n" +
            "from tb_wang_role twr\n" +
            "         left join (\n" +
            "    select\n" +
            "        r.value,\n" +
            "        group_concat(cat_id) as menu\n" +
            "    from tb_wang_role r\n" +
            "             left join tb_wang_role_category_relation rcr on rcr.role_id = r.id\n" +
            "             left join tb_wang_menu_category pc on pc.cat_id = rcr.category_id and pc.cat_level = 1\n" +
            "\n" +
            "    group by r.value\n" +
            ") s on s.value = twr.value\n" +
            "         left join (select\n" +
            "                        r.value,\n" +
            "                        group_concat(url_id) as url_list\n" +
            "                    from tb_wang_role r\n" +
            "                             left join tb_wang_url_role_relation twurr on twurr.role_id = r.id\n" +
            "                             left join tb_wang_auth_url twau on twurr.url_id = twau.id\n" +
            "                    group by r.value\n" +
            "        ) b on b.value = twr.value ")
    IPage<RoleListDTO> getRoleListByPage(IPage page);
}