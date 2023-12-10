package cn.evan.admin.user.intf.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 类名称：GlobalMethodAnnotation
 * 类描述：全局方法注解-不受租户隔离约束
 */
@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Documented
public @interface GlobalMethodAnnotation {
    boolean dynamicOrgId() default false;
}
