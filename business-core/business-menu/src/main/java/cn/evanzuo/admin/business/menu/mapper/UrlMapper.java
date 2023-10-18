package cn.evanzuo.admin.business.menu.mapper;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.menu.entity.AuthUrl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UrlMapper extends BaseMapper<AuthUrl> {

    @Select("select *\n" +
            "from skin.tb_wang_auth_url")
    IPage<AuthUrl> getUrlList(IPage page);
}