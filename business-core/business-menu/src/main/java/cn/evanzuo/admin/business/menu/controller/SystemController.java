package cn.evanzuo.admin.business.menu.controller;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.menu.VO.*;
import cn.evanzuo.admin.business.menu.service.imp.IDeptServiceImp;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system")
public class SystemController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
    @Autowired
    IDeptServiceImp iDeptServiceImp;

    public List<DeptListFormatVo> format(
            List<DeptListVo> commonMenuLists
    ) {
        if (commonMenuLists.size() == 0) {
            return new ArrayList<>();
        }
        return commonMenuLists.stream().map(
                item -> {
                    DeptListFormatVo deptListFormatVo = new DeptListFormatVo();
                    // 其他字段
                    deptListFormatVo.setId(item.getCatId());
                    deptListFormatVo.setDeptName(item.getDeptName());
                    deptListFormatVo.setStatus(item.getStatus());
                    deptListFormatVo.setRemark(item.getRemark());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    deptListFormatVo.setCreateTime(format.format(item.getCreateTime()));
                    deptListFormatVo.setChildren(format(item.getChildren()));
                    return deptListFormatVo;
                }
        ).collect(Collectors.toList());
    }
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
        Page<DeptListVo> page = new Page<>();
        page.setCurrent(2);
        page.setPages(2);
        page.setSize(1);
        IPage<DeptListVo> allMenus2 = iDeptServiceImp.getBaseMapper().getRoleNamesPage(page, authoritiesStr);
        LOGGER.error(allMenus2.getRecords().toString());
        DeptVo menuVo = new DeptVo();
        List<DeptListVo> projectMenus = allMenus.stream()
                .filter(item -> item.getParentCid() == 0)
                .peek(item -> {
                    item.setChildren(SystemController.getChildren(item, allMenus));
                })
                .sorted(Comparator.comparingInt(DeptListVo::getSort).reversed())
                .collect(Collectors.toList());

        menuVo.setList(format(projectMenus));
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
    public AccountVo getAccountList(HttpServletRequest request) throws UnsupportedEncodingException {
        AccountVo accountVo = new AccountVo();
        accountVo.setTotal(2);
        AccountListVo accountListVo = new AccountListVo();
        accountListVo.setAccount("12");
        accountVo.setItems(Collections.singletonList(accountListVo));
        return  accountVo;
    }
}
