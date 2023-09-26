package cn.evanzuo.admin.auth.component;

import cn.evanzuo.admin.auth.domain.entity.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT内容增强器
 *
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
  private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenEnhancer.class);

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    User userPrincipal = (User) authentication.getPrincipal();
    Map<String, Object> info = new HashMap<>();
    // 把用户ID设置到JWT中
    info.put("id", userPrincipal.getId());
    LOGGER.info(JSON.toJSONString(userPrincipal));
    try {
      info.put("roles", URLEncoder.encode(JSON.toJSONString(userPrincipal.getRoles()), "utf8"));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
    return accessToken;
  }
}
