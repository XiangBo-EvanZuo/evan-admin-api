/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.evan.admin.pay.intf.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单聚合根
 *
 * @EvanZuo 微信：15942403949
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    /**
     * 订单id
     */
    private Long id;
    
    /**
     * 用户id
     */
    private Long customerUserId;
    
    /**
     * 订单编号
     */
    private String orderSn;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    
    /**
     * 支付方式
     */
    private Integer payType;
    
    /**
     * 支付时间
     */
    private Date payTime;
    
    /**
     * 订单来源
     */
    private Integer source;
    
    /**
     * 订单类型 0：正常订单 1：秒杀订单 2：促销订单
     */
    private Integer type;
    
    /**
     * 自动确认天数
     */
    private Integer autoConfirmDay;
    
    /**
     * 物流公司
     */
    private String deliveryCompany;
    
    /**
     * 物流单号
     */
    private String deliverySn;
    
    /**
     * 订单备注信息
     */
    private String remark;
    
    /**
     * 收货状态 0：未接收 1：已接收
     */
    private Integer confirmFlag;
    
    /**
     * 订单创建时间
     */
    private Date createTime;
    
    /**
     * 发货时间
     */
    private Date deliveryTime;
    
    /**
     * 订单状态
     */
    private Integer status;
}
