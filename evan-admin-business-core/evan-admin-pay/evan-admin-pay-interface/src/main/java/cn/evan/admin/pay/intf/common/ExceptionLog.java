
/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package cn.evan.admin.pay.intf.common;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.reflect.MethodSignature;

public class ExceptionLog {


    private String orgId;

    private String appId;

    private MethodSignature methodSignature;

    private String methodName;

    private Class<?> clazz;

    private Object[] paramValue;

    private StopWatch watch;

    private String env;

    private String clientVersion;

    private String input;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public MethodSignature getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(MethodSignature methodSignature) {
        this.methodSignature = methodSignature;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object[] paramValue) {
        this.paramValue = paramValue;
    }

    public StopWatch getWatch() {
        return watch;
    }

    public void setWatch(StopWatch watch) {
        this.watch = watch;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
