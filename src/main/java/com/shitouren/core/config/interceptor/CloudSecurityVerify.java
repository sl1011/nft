package com.shitouren.core.config.interceptor;

import com.shitouren.core.annotation.Access;
import com.shitouren.core.bean.vo.user.UserLoginWx;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.utils.MD5Util;
import com.shitouren.core.utils.StringUtil;
import com.shitouren.core.bean.vo.user.UserLoginVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class CloudSecurityVerify {

    /**
     * md5签名
     * 按参数token连接appSecret 签名
     *
     * @param appSecret
     * @param token
     * @return
     */
    public static String sign(String appSecret, String token) {
        StringBuilder paramValues = new StringBuilder();
        paramValues.append(appSecret).append(token);
        return MD5Util.MD5Encode(paramValues.toString());
    }

    /**
     * 请求参数签名验证
     *
     * @param request
     * @return true 验证通过 false 验证失败
     * @throws Exception
     */
    public static boolean signVerify(CloudRedisTemplate cloudRedisTemplate, HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Access access = ((HandlerMethod) handler).getMethod().getAnnotation(Access.class);
        if (access != null && !access.value()){
            return true;
        }
        if(HttpStatus.NOT_FOUND.value() == response.getStatus()){
            throw new CloudException(ExceptionConstant.请求不存在);
        }
        String requestURI = request.getServletPath().toLowerCase();
        if (requestURI.equals("/error")){
            throw new CloudException(ExceptionConstant.请求错误);
        }
        String token = request.getHeader("token");
        if(StringUtil.isEmpty(token)){
            throw new CloudException(ExceptionConstant.登录失效);
        }
        UserLoginWx userLoginDto = null;
        try {
            if(cloudRedisTemplate.get(token) instanceof UserLoginWx){
                userLoginDto = cloudRedisTemplate.get(token);
            }
            if (userLoginDto == null || StringUtil.isEmpty(String.valueOf(userLoginDto.getUserId()))) {
                throw new CloudException(ExceptionConstant.登录失效);
            }
//            if(userLoginDto.getAccountStatus().equals(EumSysUser.SysUserAccountStatus.禁用.getValue())){
//                throw new CloudException(ExceptionConstant.账号已被禁用);
//            }
        } catch (Exception e) {
            if (ExceptionConstant.账号在其他设备登录.getCode().equals(cloudRedisTemplate.get(token))) {
                cloudRedisTemplate.delete(token);
                throw new CloudException(ExceptionConstant.账号在其他设备登录);
            }
            cloudRedisTemplate.delete(token);
            throw new CloudException(ExceptionConstant.登录失效);
        }
//        //权限验证
//        if(access == null || access.authentication()){
//            //权限验证
//            authPower(userLoginDto,requestURI);
//        }
        return true;
    }

/*
    public static boolean signVerify(CloudRedisTemplate cloudRedisTemplate, HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Access access = ((HandlerMethod) handler).getMethod().getAnnotation(Access.class);
        if (access != null && !access.value()){
            return true;
        }
        if(HttpStatus.NOT_FOUND.value() == response.getStatus()){
            throw new CloudException(ExceptionConstant.请求不存在);
        }
        String requestURI = request.getServletPath().toLowerCase();
        if (requestURI.equals("/error")){
            throw new CloudException(ExceptionConstant.请求错误);
        }
        String token = request.getHeader("token");
        if(StringUtil.isEmpty(token)){
            throw new CloudException(ExceptionConstant.登录失效);
        }
        UserLoginVo userLoginDto = null;
        try {
            if(cloudRedisTemplate.get(token) instanceof UserLoginVo){
                userLoginDto = cloudRedisTemplate.get(token);
            }
            if (userLoginDto == null || StringUtil.isEmpty(userLoginDto.getLoginAccount())) {
                throw new CloudException(ExceptionConstant.登录失效);
            }
//            if(userLoginDto.getAccountStatus().equals(EumSysUser.SysUserAccountStatus.禁用.getValue())){
//                throw new CloudException(ExceptionConstant.账号已被禁用);
//            }
        } catch (Exception e) {
            if (ExceptionConstant.账号在其他设备登录.getCode().equals(cloudRedisTemplate.get(token))) {
                cloudRedisTemplate.delete(token);
                throw new CloudException(ExceptionConstant.账号在其他设备登录);
            }
            cloudRedisTemplate.delete(token);
            throw new CloudException(ExceptionConstant.登录失效);
        }
//        //权限验证
//        if(access == null || access.authentication()){
//            //权限验证
//            authPower(userLoginDto,requestURI);
//        }
        return true;
    }
*/

//    /**
//     * 请求鉴权
//     * @param userLoginDto 用户token对象
//     * @param requestURI 请求url
//     */
//    private static void authPower(UserLoginVo userLoginDto,String requestURI) {
////        if(!EumSysUser.SysUserAccountStatus.启用.getValue().equals(userLoginDto.getAccountStatus())){
////            throw new CloudException(ExceptionConstant.账号已被禁用);
////        }
//        Set<String> powerSet = userLoginDto.getPowerSet();
//        if(powerSet == null || powerSet.size() < 1){
//            throw new CloudException(ExceptionConstant.没有权限访问);
//        }
//        boolean havePower = false; //是否拥有访问权限， true = 拥有权限
//        for (String powerKey : powerSet) {
//            if(StringUtil.isEmpty(powerKey)){
//                continue;
//            }
//            if(powerKey.toLowerCase().equals(requestURI)){
//                havePower = true;
//            }
//        }
//        if(!havePower){
//            throw new CloudException(ExceptionConstant.没有权限访问);
//        }
//    }


}
