package cn.evanzuo.admin.business.order.mapper;

import cn.evanzuo.admin.business.order.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Select("select *, file_type, `number`, length, `date` from tb_wang_project " +
//            "left join tb_wang_project_date twpd on tb_wang_project.id = twpd.project_id " +
//            "left join tb_wang_project_date_file twpf on twpd.id = twpf.project_date_id " +
//            "where tb_wang_project.user_id = #{userId} and twpd.project_id = #{projectId}"
//    )
//    List<Object> getData(@Param("userId") String userId, @Param("projectId") String projectId);
}