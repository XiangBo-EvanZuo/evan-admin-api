package cn.evanzuo.admin.business.resource.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class PerCodeDTO {
//    private String userId;
    private List<String> perCodeList;
}
