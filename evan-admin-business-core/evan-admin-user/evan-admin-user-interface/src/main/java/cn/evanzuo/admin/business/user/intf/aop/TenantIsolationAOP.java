package cn.evanzuo.admin.business.user.intf.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 类名称：TenantIsolationAOP
 * ********************************
 * <p>
 * 类描述：组合隔离切面
 */
//@Aspect
//@Component
@Slf4j
public class TenantIsolationAOP {

    @Pointcut("execution(public * cn.evanzuo.admin..*RepositoryImpl.*(..))")
    public void tenantIsolation() {
    }

    @Around(value = "tenantIsolation()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String appId = "";
        String orgId = "";
        String userId = "";


        // 如果方法上存在@GlobalMethodAnnotation注解，不受租户隔离约束
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        boolean hasAnnotation = signature.getMethod().isAnnotationPresent(GlobalMethodAnnotation.class);

        if (!hasAnnotation) {
            // 获取参数
            Object[] args = proceedingJoinPoint.getArgs();
            String defaultAppId = (String) args[0];
            String defaultOrgId = (String) args[1];
            String defaultUserId = (String) args[2];

            log.info("[TenantIsolationAop], appId:{}, orgId:{}, originAppId:{}, originOrgId:{}", appId, orgId,
                    defaultAppId, defaultOrgId);
            if (StringUtils.isBlank(appId) || StringUtils.isBlank(orgId)) {
                appId = defaultAppId;
                orgId = defaultOrgId;
                userId = defaultUserId;
            }
        }
        // 设置上下文
//        TenantIsolationContext.set(appId, orgId, userId);

        if (hasAnnotation) {
            GlobalMethodAnnotation annotation = signature.getMethod().getAnnotation(GlobalMethodAnnotation.class);
            boolean dynamicOrgId = annotation.dynamicOrgId();
            if (dynamicOrgId) {
                // 获取参数
                Object[] args = proceedingJoinPoint.getArgs();
                if (args.length < 3) {
                    throw new RuntimeException("Repository参数不全！");
                }

                String defaultAppId = (String) args[0];
                String defaultOrgId = (String) args[1];
                String defaultUserId = (String) args[2];
//                TenantIsolationContext.set(defaultAppId, defaultOrgId, defaultUserId);
            } else {
//                TenantIsolationContext.setGlobal();
            }
        }

        try {

            return proceedingJoinPoint.proceed();

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            // 清空上下文
//            TenantIsolationContext.clear();
        }
    }

}
