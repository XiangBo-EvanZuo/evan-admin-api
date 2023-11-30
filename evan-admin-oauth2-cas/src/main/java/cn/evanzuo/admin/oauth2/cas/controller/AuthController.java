package cn.evanzuo.admin.oauth2.cas.controller;

import cn.evanzuo.admin.oauth2.cas.utils.redis.RedisService;
import cn.evanzuo.admin.oauth2.cas.domain.dto.Oauth2TokenDto;
import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

import cn.evanzuo.admin.oauth2.cas.api.CommonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义Oauth2获取令牌接口
 *
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {
  @Autowired
  RedisService redisService;
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  private final TokenEndpoint tokenEndpoint;

  public AuthController(TokenEndpoint tokenEndpoint) {
    this.tokenEndpoint = tokenEndpoint;
  }

  /**
   * Oauth2登录认证
   */
  @PostMapping("/token")
  public CommonResult<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
    OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
    Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
        .token(Objects.requireNonNull(oAuth2AccessToken).getValue())
        .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
        .expiresIn(oAuth2AccessToken.getExpiresIn())
        .tokenHead("Bearer ").build();
    return CommonResult.success(oauth2TokenDto);
  }

  @GetMapping("/logout")
  public CommonResult<LogoutVo> getKey(HttpServletRequest request) {
      // 清除在redis中的token
      String userStr = request.getHeader("user");
      LOGGER.info(userStr);
      JSONObject userJsonObject = new JSONObject(userStr);
      redisService.remove(userJsonObject.get("user_name", String.class));
      LogoutVo logoutVo = new LogoutVo();
      logoutVo.setResult("退出成功");
      return CommonResult.success(logoutVo);
  }

}
