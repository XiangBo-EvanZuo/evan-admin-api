package cn.evanzuo.admin.business.resource.api;

/**
 * 枚举了一些常用API操作码
 *
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
public enum ResultCode implements IErrorCode {
  SUCCESS(200, "操作成功"),
  FAILED(500, "操作失败"),
  VALIDATE_FAILED(404, "参数检验失败"),
  UNAUTHORIZED(401, "暂未登录或token已经过期"),
  FORBIDDEN(403, "没有相关权限");
  private final long code;
  private final String message;

  ResultCode(long code, String message) {
    this.code = code;
    this.message = message;
  }

  public long getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}