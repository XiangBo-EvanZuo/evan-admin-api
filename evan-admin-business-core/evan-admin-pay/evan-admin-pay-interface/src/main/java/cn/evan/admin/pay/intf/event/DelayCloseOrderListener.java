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

import cn.evan.admin.pay.intf.mq.event.DelayCloseOrderEvent;
import cn.evan.admin.pay.intf.mq.provide.DelayCloseOrderProvide;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 发送延迟队列取消未付款订单监听
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Order(3)
@Component
public class DelayCloseOrderListener implements ApplicationListener<OrderCreateEvent> {

    @Autowired
    private DelayCloseOrderProvide delayCloseOrderProvide;
    
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        delayCloseOrderProvide.delayCloseOrderSend(
                new DelayCloseOrderEvent(event.getOrder().getOrderSn())
        );
    }
}
