package com.shitouren.core.annotation;


import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.bean.vo.user.UserLoginVo;
import com.shitouren.core.bean.vo.user.UserLoginWx;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.utils.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Autho： 王涛
 * @DATE： 2019/8/20 8:33
 */
@Component
@Aspect
public class GetLoginUserAOP {

    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;


    @Pointcut("@annotation(com.shitouren.core.annotation.GetLoginUser)")
    public void userTokenPoinCut() {

    }

/*    @Before(value = "userTokenPoinCut()")
    public Object tokenToUser(JoinPoint joinPoint) {
        try {
            */

    /**
     * 获取请求参数
     *//*
            Object[] args = joinPoint.getArgs();
            SysUserParam requestParam = null;
            for (Object param : args) {
                if (param instanceof SysUserParam) {
                    requestParam = (SysUserParam) param;
                    break;
                }
            }
            if (requestParam == null) {
                System.out.println("请继承 " + SysUserParam.class + "类");
                return "";
            }
            String methodAnnotationValue = getMethodAnnotationValue(joinPoint);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader(methodAnnotationValue);
            if (StringUtil.isEmpty(token)) {
                System.out.println("没有用户令牌，无法获取用户信息");
            }
            UserLoginVo loginDto = cloudRedisTemplate.get(token);
            requestParam.setLogUserPid(loginDto.getPid());
            requestParam.setLoginAccount(loginDto.getLoginAccount());
            requestParam.setToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudException(ExceptionConstant.参数解析错误);
        }
        return "";
    }*/
    @Before(value = "userTokenPoinCut()")
    public Object tokenToUser(JoinPoint joinPoint) {
        try {
            /**
             * 获取请求参数
             */
            Object[] args = joinPoint.getArgs();
            SysUserParam requestParam = null;
            for (Object param : args) {
                if (param instanceof SysUserParam) {
                    requestParam = (SysUserParam) param;
                    break;
                }
            }
            if (requestParam == null) {
                System.out.println("请继承 " + SysUserParam.class + "类");
                return "";
            }
            String methodAnnotationValue = getMethodAnnotationValue(joinPoint);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader(methodAnnotationValue);
            if (StringUtil.isEmpty(token)) {
                System.out.println("没有用户令牌，无法获取用户信息");
            }
//            UserLoginVo loginDto = cloudRedisTemplate.get(token);
//            requestParam.setLogUserPid(loginDto.getPid());
            UserLoginWx loginDto = cloudRedisTemplate.get(token);
            requestParam.setLogUserPid(loginDto.getUserId());
            requestParam.setLoginAccount(loginDto.getUserIp());
            requestParam.setToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudException(ExceptionConstant.参数解析错误);
        }
        return "";
    }

    /**
     * 获取方法上注解的值
     *
     * @return
     */
    public String getMethodAnnotationValue(JoinPoint joinPoint) {
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            Class<?> targetClass = Class.forName(targetName);
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName) && method.isAnnotationPresent(GetLoginUser.class)
                        && method.getParameterTypes().length == arguments.length) {
                    return method.getAnnotation(GetLoginUser.class).tokenKey();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
