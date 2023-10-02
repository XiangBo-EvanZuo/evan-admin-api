package cn.evan.zuo.common.entity;

import lombok.Data;

@Data
public class CommonApiResult {
    private String timestamp;
    private long code;
    private String message;
    private Object data;
}
