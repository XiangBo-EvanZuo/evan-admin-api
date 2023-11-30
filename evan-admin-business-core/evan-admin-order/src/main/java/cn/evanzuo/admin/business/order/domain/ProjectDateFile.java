package cn.evanzuo.admin.business.order.domain;

import lombok.Data;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Data
public class ProjectDateFile {
  private Long id;
  private String fileType;
  private Integer length;
  private Integer number;
  private String date;
  private Long projectDateId;
}
