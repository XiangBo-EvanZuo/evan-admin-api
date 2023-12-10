/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package cn.evan.admin.pay.intf.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常处理切面
 */
//@Aspect
//@Component
//@Order(1)
public class ExceptionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

    /**
     * 请求拦截
     */
    @Around(value = "@target(Class1)" +
            "&& @annotation(Anootation1)")
    public Object aroundTianluAdvice(ProceedingJoinPoint pjp) {
        ExceptionLog exceptionLog = buildLogBo(pjp);
        LOGGER.info("[tianlu-input] === orgId: {} === appId: {} === method: {} === env: {} === clientVersion: {} "
                        + "=== param: {}",
                exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(), exceptionLog.getEnv(),
                exceptionLog.getClientVersion(), exceptionLog.getInput());

        try {
            Object result = pjp.proceed();
            LOGGER.info("[tianlu-output] === orgId: {} === appId: {} === method: {} === env: {} === clientVersion: {}"
                            + " === param: {} === result: {} === cost: {}ms,",
                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
                    exceptionLog.getEnv(), exceptionLog.getClientVersion(), exceptionLog.getInput(),
                    JSON.toJSON(result), exceptionLog.getWatch().getTime());
            return result;
//        } catch (ApiBusinessException apiBusinessException) {
//            // 玉门关风险流量拦截
//            LOGGER.error("[tianlu-apiBusinessException] === orgId: {} === appId: {} === method: {} === env: {} "
//                            + "=== clientVersion: {} === param: {} === e: {}",
//                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
//                    exceptionLog.getEnv(), exceptionLog.getClientVersion(), exceptionLog.getInput(),
//                    apiBusinessException.getMessage(), apiBusinessException);
//            throw apiBusinessException;
//        } catch (AdaptorException adaptorException) {
//            // AdaptorException的异常信息不应该展示给用户
//            LOGGER.error("[tianlu-adaptorException] === orgId: {} === appId: {} === method: {} === env: {} "
//                            + "=== clientVersion: {} === param: {} === e: {}",
//                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
//                    exceptionLog.getEnv(), exceptionLog.getClientVersion(), exceptionLog.getInput(),
//                    adaptorException.getMessage(), adaptorException);
//            throw new BusinessException(BaseErrorCodes.ADAPTOR_DEFAULT_ERROR.getCode(), "操作失败，请刷新后重试");
//        } catch (SystemException systemException) {
//            LOGGER.error("[tianlu-systemException] === orgId: {} === appId: {} === method: {} === env: {} "
//                            + "=== clientVersion: {} === param: {} === e: {}",
//                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
//                    exceptionLog.getEnv(), exceptionLog.getClientVersion(), exceptionLog.getInput(),
//                    systemException.getMessage(), systemException);
//            throw new BusinessException(BaseErrorCodes.UNKNOWN_ERROR_CODE.getCode(), "操作失败，请刷新后重试");
//        } catch (BaseException baseException) {
//            LOGGER.warn("[tianlu-baseException] === orgId: {} === appId: {} === method: {} === env: {} "
//                            + "=== clientVersion: {} === param: {} === e: {}",
//                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
//                    exceptionLog.getEnv(), exceptionLog.getClientVersion(), exceptionLog.getInput(),
//                    baseException.getLogMsg(), baseException);
//            throw new BusinessException(baseException.getCode(), baseException.getMsg());
//        } catch (BusinessException businessException) {
//            LOGGER.warn("[tianlu-businessException] === orgId: {} === appId: {} === method: {} === env: {} "
//                            + "=== clientVersion: {} === param: {} === e: {}",
//                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
//                    exceptionLog.getEnv(), exceptionLog.getClientVersion(), exceptionLog.getInput(), businessException);
//            throw businessException;
        } catch (Throwable e) {
            LOGGER.error("[tianlu-UnknownException] === orgId: {} === appId: {} === method: {} === env: {} === "
                            + "clientVersion: {} === param: {} === e: {}",
                    exceptionLog.getOrgId(), exceptionLog.getAppId(), exceptionLog.getMethodName(),
                    exceptionLog.getEnv(),
                    exceptionLog.getClientVersion(), exceptionLog.getInput(), e.getMessage(), e);
            throw new RuntimeException("系统异常请稍后再试", e);
        }
    }

    private ExceptionLog buildLogBo(ProceedingJoinPoint pjp) {
        ExceptionLog exceptionLog = new ExceptionLog();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//        String env = GatewayContext.getEnv();
//        String clientVersion = GatewayContext.getClientVersion();
        Object[] paramValues = pjp.getArgs();
        Class<?> clazz = pjp.getTarget().getClass();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        exceptionLog.setMethodSignature(methodSignature);
//        exceptionLog.setEnv(env);
//        exceptionLog.setClientVersion(clientVersion);
//        exceptionLog.setParamValue(paramValues);
//        exceptionLog.setMethodName(clazz.getSimpleName() + "." + methodSignature.getName());
//        exceptionLog.setClazz(clazz);
        exceptionLog.setWatch(stopWatch);
//        String[] paramNames = methodSignature.getParameterNames();
//        List<String> input = Lists.newArrayList();
//        if (paramNames != null) {
//            for (int i = 0; i < paramNames.length; i++) {
//                String paramName = paramNames[i];
//                Object paramValue = paramValues[i];
//                if (StringUtils.equals(paramName, InterfaceConstants.ORG_ID)) {
//                    exceptionLog.setOrgId(String.valueOf(paramValue));
//                } else if (StringUtils.equals(paramName, InterfaceConstants.APP_ID)) {
//                    exceptionLog.setAppId(String.valueOf(paramValue));
//                } else {
//                    input.add(paramName + ":" + SimpleJacksonWrapper.toJson(paramValue));
//                }
//            }
//            exceptionLog.setInput(StringUtils.join(input, " "));
//        }
        return exceptionLog;
    }
}

