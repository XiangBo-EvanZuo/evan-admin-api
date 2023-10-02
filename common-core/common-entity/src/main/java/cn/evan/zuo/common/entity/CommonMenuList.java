package cn.evan.zuo.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author EvanZuo[739221432@qq.com] 2023/10/02
 */
@Data
@TableName(value = "pms_category")
public class CommonMenuList {
    private Long catId;
    private String name;
    private Long parentCid;
    private Long catLevel;
    private Long showStatus;
    private int sort;
    private String component;
    private String path;
    private String redirect;
    private Boolean hideChildrenInMenu;
    private String icon;
    private String title;
    @TableField(exist = false)
    private List<CommonMenuList> children;

    @TableField(exist = false)
    private Meta meta;
}

