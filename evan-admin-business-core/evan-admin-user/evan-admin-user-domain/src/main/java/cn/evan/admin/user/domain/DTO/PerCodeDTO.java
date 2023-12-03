package cn.evan.admin.user.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class PerCodeDTO {
//    private String userId;
    private List<String> perCodeList;
}
