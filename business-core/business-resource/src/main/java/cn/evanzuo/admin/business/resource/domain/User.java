package cn.evanzuo.admin.business.resource.domain;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class User {
  private Long id;
  private String username;
  private String password;
  private List<RoleItem> roles;

  private String realName;
}
