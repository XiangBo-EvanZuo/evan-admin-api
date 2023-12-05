package cn.evan.zuo.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommonPageVo<T> {
    private Integer total;
    private List<T> items;
}

