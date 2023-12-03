package cn.evan.admin.user.sdk.feign.api.impl;
import cn.evan.admin.common.convention.config.api.CommonResult;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.admin.user.sdk.feign.api.UserFeignMenuProvider;
import cn.evan.admin.user.sdk.feign.dto.MenuVo;
import cn.evan.admin.common.feign.client.clients.EvanFeignUserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class UserFeignProviderImpl implements UserFeignMenuProvider {

    @Autowired
    EvanFeignUserInfo evanFeignUserInfo;
    @Override
    public MenuVo menuListFeign(String user) {
        String json = evanFeignUserInfo.getUserMenuList(user);
        ObjectMapper objectMapper = new ObjectMapper();
        CommonResult<MenuVo> menuVoRes = new CommonResult<>();
        try {
            menuVoRes = objectMapper.readValue(json, new TypeReference<CommonResult<MenuVo>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return menuVoRes.getData();
    }

    public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, Page page) {
        return null;
    }
}
