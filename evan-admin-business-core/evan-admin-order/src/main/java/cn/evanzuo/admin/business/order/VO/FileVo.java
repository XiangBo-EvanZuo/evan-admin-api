package cn.evanzuo.admin.business.order.VO;

import cn.evanzuo.admin.business.order.domain.ProjectDateFile;
import lombok.Data;

import java.util.List;

@Data
public class FileVo {
    private String date;
    private List<ProjectDateFile> list;
}
