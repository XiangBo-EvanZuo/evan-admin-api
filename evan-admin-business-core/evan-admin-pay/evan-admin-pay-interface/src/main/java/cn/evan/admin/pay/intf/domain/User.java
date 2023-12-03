package cn.evan.admin.pay.intf.domain;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Long id;
  private String username;
  private String password;
  @TableField(exist = false)
  private List<String> roles;
  private Boolean deleted;
}
