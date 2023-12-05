package cn.evan.admin.user.domain.controller;

import cn.evan.admin.user.domain.DTO.*;
import cn.evan.admin.user.domain.entity.AuthUrl;
import cn.evan.admin.user.domain.entity.BusinessModuleEntity;
import cn.evan.admin.user.domain.serviceOld.UrlService;
import cn.evan.admin.user.sdk.feign.dto.*;
import cn.evan.admin.user.sdk.feign.dto.AccountExistDTO;
import cn.evan.zuo.common.dto.CommonPageDTO;
import cn.evan.zuo.common.entity.EvanUser;
import cn.evan.zuo.common.entity.EvanUserUO;
import cn.evan.zuo.common.vo.CommonPageVo;
import cn.evan.zuo.common.vo.UpdateResult;
import cn.evan.admin.user.domain.serviceOld.SystemService;
import cn.evan.admin.user.domain.serviceOld.imp.IDeptServiceImp;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system")
public class SystemController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
    @Autowired
    IDeptServiceImp iDeptServiceImp;

    @Resource
    SystemService systemService;

    @Resource
    UrlService urlService;

    public List<DeptListFormatDTO> format(
            List<DeptListDTO> commonMenuLists
    ) {
        if (commonMenuLists.size() == 0) {
            return new ArrayList<>();
        }
        return commonMenuLists.stream().map(
                item -> {
                    DeptListFormatDTO deptListFormatDTO = new DeptListFormatDTO();
                    // 其他字段
                    deptListFormatDTO.setId(item.getCatId());
                    deptListFormatDTO.setDeptName(item.getDeptName());
                    deptListFormatDTO.setStatus(item.getStatus());
                    deptListFormatDTO.setRemark(item.getRemark());
                    deptListFormatDTO.setCreateTime(item.getCreateTime());
                    deptListFormatDTO.setParentDept(item.getParentCid());
                    deptListFormatDTO.setChildren(format(item.getChildren()));
                    return deptListFormatDTO;
                }
        ).collect(Collectors.toList());
    }
    @GetMapping("/getDeptList")
    public DeptDTO getDeptList(HttpServletRequest request) {
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
        List<DeptListDTO> allMenus = iDeptServiceImp.getBaseMapper().getDeptList(authoritiesStr);
        DeptDTO menuVo = new DeptDTO();
        List<DeptListDTO> projectMenus = allMenus.stream()
                .filter(item -> item.getParentCid() == 0)
                .peek(item -> {
                    item.setChildren(SystemController.getChildren(item, allMenus));
                })
                .sorted(Comparator.comparingInt(DeptListDTO::getSort).reversed())
                .collect(Collectors.toList());

        menuVo.setList(format(projectMenus));
        LOGGER.info(projectMenus.toString());
        LOGGER.info(String.valueOf(projectMenus.size()));
        menuVo.setTotal(projectMenus.size());
        return menuVo;
    }

    public static List<DeptListDTO> getChildren(DeptListDTO root, List<DeptListDTO> allMenus) {
        return allMenus.stream()
                .filter(item -> Objects.equals(item.getParentCid(), root.getCatId()))
                .peek(item -> item.setChildren(SystemController.getChildren(item, allMenus)))
                .sorted(Comparator.comparingInt(DeptListDTO::getSort).reversed())
                .collect(Collectors.toList());
    }
    @PostMapping("/getAccountList")
    public AccountDTO getAccountList(@RequestBody AccountListDTO accountListDTO) {
        String defaultString = "";
        Page<DeptListDTO> page = new Page<>();
        page.setCurrent(accountListDTO.getPage());
        page.setSize(accountListDTO.getPageSize());
        IPage<EvanUser> allMenus2 = iDeptServiceImp.getBaseMapper().getRoleNamesPage(
                page, accountListDTO.getDeptId(),
                Optional.ofNullable(accountListDTO.getNickname()).orElse(defaultString),
                Optional.ofNullable(accountListDTO.getAccount()).orElse(defaultString)
        );
        LOGGER.error(allMenus2.getRecords().toString());
        List<EvanUserUO> evanUserUOS = allMenus2.getRecords().stream()
                .map(item -> {
                    EvanUserUO evanUserUO = new EvanUserUO();
                    evanUserUO.setId(item.getId());
                    evanUserUO.setAccount(item.getUsername());
                    evanUserUO.setNickname(item.getNickName());
                    evanUserUO.setRemark(item.getRemark());
                    evanUserUO.setEmail(item.getEmail());
                    evanUserUO.setCreateTime(item.getCreateTime());
                    evanUserUO.setRole(Arrays.asList(item.getRoles().split(",")));
                    return evanUserUO;
                }).collect(Collectors.toList());
        AccountDTO accountVo = new AccountDTO();
        accountVo.setTotal((int) allMenus2.getTotal());
        accountVo.setItems(evanUserUOS);
        return  accountVo;
    }
    // todo: 需要根据用户的权限返回权限列表
    @GetMapping("/getAllRoleList")
    public List<RoleListDTO> getAllRoleList() {
        List<RoleListDTO> roleList = iDeptServiceImp.getBaseMapper().getAllRolesList();
        LOGGER.info("roleList: {}", roleList);
        return  roleList;
    }

    @PostMapping("/accountExist")
    public AccountExistDTO accountExist(@RequestBody cn.evan.admin.user.domain.DTO.AccountExistDTO accountExistDTO) {
        return systemService.accountExist(accountExistDTO.getAccount());
    }

    // todo
    @PostMapping("/setRoleStatus")
    public AccountExistDTO setRoleStatus(@RequestBody cn.evan.admin.user.domain.DTO.AccountExistDTO accountExistDTO) {
        return systemService.accountExist(accountExistDTO.getAccount());
    }
    // todo
    @PostMapping("/getRoleListByPage")
    public CommonPageVo<RoleListFinalDTO> getRoleListByPage(@RequestBody CommonPageDTO commonPageDTO) {
        return systemService.getRoleListByPage(commonPageDTO);
    }

    // todo
    @PostMapping("/getUrlList")
    public CommonPageVo<AuthUrl> getRoleUrlList(@RequestBody UrlListDTO commonPageDTO) {
        return urlService.getUrlList(commonPageDTO);
    }
    @PostMapping("/getModuleList")
    public List<BusinessModuleEntity> getModuleList() {
        return urlService.getModuleList();
    }

    @PostMapping("/updateAuthUrl")
    public UpdateResult updateAuthUrl(@RequestBody UpdateUrlDTO updateUrlDTO) {
        return urlService.updateAuthUrl(updateUrlDTO);
    }

    @PostMapping("/addAuthUrl")
    public UpdateResult addAuthUrl(@RequestBody UpdateUrlDTO updateUrlDTO) {
        return urlService.addAuthUrl(updateUrlDTO);
    }
    @PostMapping("/deleteAuthUrl")
    public UpdateResult deleteAuthUrl(@RequestBody UpdateUrlDTO updateUrlDTO) {
        return urlService.deleteAuthUrl(updateUrlDTO);
    }

    @PostMapping("/updateRoleUrl")
    public UpdateResult updateRoleUrl(@RequestBody UpdateRoleUrlDTO updateRoleUrlDTO) {
        return urlService.updateRoleUrl(updateRoleUrlDTO);
    }

    @PostMapping("/updateRoleMenu")
    public UpdateResult updateRoleMenu(@RequestBody UpdateRoleUrlDTO updateRoleUrlDTO) {
        return urlService.updateRoleMenu(updateRoleUrlDTO);
    }
}
