package cn.evanzuo.admin.business.user.DTO;

import cn.evan.zuo.common.dto.CommonPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UrlListDTO extends CommonPageDTO {
    private Integer moduleId;
}
