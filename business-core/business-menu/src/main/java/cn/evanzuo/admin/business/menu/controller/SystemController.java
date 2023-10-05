package cn.evanzuo.admin.business.menu.controller;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.menu.VO.DeptListVo;
import cn.evanzuo.admin.business.menu.VO.DeptVo;
import cn.evanzuo.admin.business.menu.VO.Meta;
import cn.evanzuo.admin.business.menu.service.imp.IDeptServiceImp;
import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system")
public class SystemController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
    @Autowired
    IDeptServiceImp iDeptServiceImp;

    @GetMapping("/getDeptList")
    public DeptVo getDeptList(HttpServletRequest request) {
        // 从Header中获取用户信息
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        System.out.println((userJsonObject));

        List<String> authorities = (List<String>)userJsonObject.get("authorities");
        String authoritiesStr = authorities.stream()
                .map(item -> "`" + item + "`")
                .map(item -> item.replace("`", "'"))
                .collect(Collectors.joining(","));
        LOGGER.info(authorities.toString());
        LOGGER.info(authoritiesStr);
        System.out.println(authorities);
        List<DeptListVo> allMenus = iDeptServiceImp.getBaseMapper().getRoleNames(authoritiesStr);
        DeptVo menuVo = new DeptVo();
        List<DeptListVo> projectMenus = allMenus.stream()
                .filter(item -> item.getParentCid() == 0)
                .peek(item -> {
                    item.setChildren(SystemController.getChildren(item, allMenus));
                })
                .sorted(Comparator.comparingInt(DeptListVo::getSort).reversed())
                .collect(Collectors.toList());

        menuVo.setList(projectMenus);
        LOGGER.info(projectMenus.toString());
        LOGGER.info(String.valueOf(projectMenus.size()));
        menuVo.setTotal(projectMenus.size());
        return menuVo;
    }

    public static List<DeptListVo> getChildren(DeptListVo root, List<DeptListVo> allMenus) {
        return allMenus.stream()
                .filter(item -> Objects.equals(item.getParentCid(), root.getCatId()))
                .peek(item -> item.setChildren(SystemController.getChildren(item, allMenus)))
                .sorted(Comparator.comparingInt(DeptListVo::getSort).reversed())
                .collect(Collectors.toList());
    }
    @GetMapping("/getAccountList")
    public DeptVo getAccountList(HttpServletRequest request) throws UnsupportedEncodingException {
        DeptVo menuVo = new DeptVo();
        menuVo.setTotal(2);
        return  menuVo;
    }
}
