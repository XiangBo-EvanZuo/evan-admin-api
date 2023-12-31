package cn.evanzuo.admin.business.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class AuthUrl {
    private Long id;
    private String url;
    private Long status;
    private String moduleName;
    private String path;
    private Long moduleId;
}
