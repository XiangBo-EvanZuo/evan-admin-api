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

package cn.evan.admin.pay.domain.mq.consumer;

import cn.evan.admin.pay.domain.mq.comon.MessageWrapper;
import cn.evan.admin.pay.domain.mq.event.PayResultNotifyMessageEvent;
import cn.evan.admin.pay.domain.mq.messaging.OrderSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 支付结果通知消息消费者
 *
 * @EvanZuo 微信：15942403949
 */
@Slf4j
@Component
public class PayResultNotifyMessageConsumer {

    @StreamListener(OrderSink.PAY_RESULT_NOTIFY)
    public void delayCloseOrderConsumer(MessageWrapper<PayResultNotifyMessageEvent> messageWrapper) {

        log.info("res: {}", messageWrapper);
    }
}
