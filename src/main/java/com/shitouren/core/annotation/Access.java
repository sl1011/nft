package com.shitouren.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 王涛
 * 2019年8月23日  上午11:19:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {

	/**
	 * @Description 未定义Access注解的Controller默认拦截所有
     * true = 拦截
     * false = 不拦截
	 * @return
	 */
	boolean value() default true;

    /**
     * @Description 未定义此属性的。默认拦截Controller进行权限验证
     * 是否进行鉴权
     * @return
     */
	boolean authentication() default true;

}
