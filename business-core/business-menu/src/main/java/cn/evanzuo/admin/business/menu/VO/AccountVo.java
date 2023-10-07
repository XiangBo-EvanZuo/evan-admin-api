package cn.evanzuo.admin.business.menu.VO;

import lombok.Data;

import java.util.List;

@Data
public class AccountVo {
    private Integer total;
    private List<AccountListVo> items;
}
