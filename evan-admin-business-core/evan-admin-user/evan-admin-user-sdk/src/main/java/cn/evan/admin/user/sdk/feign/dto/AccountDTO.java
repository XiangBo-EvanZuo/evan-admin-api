package cn.evan.admin.user.sdk.feign.dto;

import cn.evan.zuo.common.entity.EvanUserUO;
import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private Integer total;
    private List<EvanUserUO> items;
}
