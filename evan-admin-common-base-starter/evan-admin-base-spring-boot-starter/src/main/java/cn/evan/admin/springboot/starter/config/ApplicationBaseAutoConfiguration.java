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

package cn.evan.admin.springboot.starter.config;

import cn.evan.admin.springboot.starter.init.ApplicationContentPostProcessor;
import cn.evan.admin.springboot.starter.safa.FastJsonSafeMode;
import cn.evan.admin.springboot.starter.ApplicationContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * 应用基础自动装配
 *
 * @EvanZuo 微信：15942403949
 */
public class ApplicationBaseAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHolder congoApplicationContextHolder() {
        return new ApplicationContextHolder();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ApplicationContentPostProcessor congoApplicationContentPostProcessor() {
        return new ApplicationContentPostProcessor();
    }
    
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "congomall.fastjson.safa-mode", havingValue = "true")
    public FastJsonSafeMode congoFastJsonSafeMode() {
        return new FastJsonSafeMode();
    }
}
