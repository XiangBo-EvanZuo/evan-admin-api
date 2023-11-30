package cn.evanzuo.admin.business.order.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectDate {
  private Long id;
  private String date;
  private Long projectId;
}
