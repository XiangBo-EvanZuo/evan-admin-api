package cn.evan.admin.user.sdk.feign.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Data
public class CurrentUserDTO<T> {
  private Long id;
  private String username;
  private String password;
  private List<T> roles;

  private String nickName;
}
