package cn.evanzuo.admin.business.user.sdk.feign.dto;

import cn.evan.zuo.common.entity.EvanUserVo;
import lombok.Data;

import java.util.List;

@Data
public class AccountVo {
    private Integer total;
    private List<EvanUserVo> items;
}
