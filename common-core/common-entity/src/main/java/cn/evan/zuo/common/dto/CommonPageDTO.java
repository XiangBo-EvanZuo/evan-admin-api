package cn.evan.zuo.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommonPageDTO {

    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    @NotNull(message = "page不能为空")
    private Integer page;

}
