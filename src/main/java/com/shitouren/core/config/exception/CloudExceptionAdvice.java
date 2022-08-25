package com.shitouren.core.config.exception;

import com.alibaba.fastjson.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CloudExceptionAdvice {

    private Logger log = LoggerFactory.getLogger(CloudExceptionAdvice.class);

    /**
     * 全局异常处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ExceptionConstant.请求失败.getCode());
        map.put("info", ExceptionConstant.请求失败.getMsg());
        map.put("data", new JSONObject());
        log.error(ExceptionUtils.getFullStackTrace(ex));
        ex.printStackTrace();
        return map;
    }

    /**
     * 拦截参数校验异常 MethodArgumentNotValidException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, Object> cloudExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> {
            map.put("info", error.getDefaultMessage());
            map.put("code", ExceptionConstant.参数异常.getCode());
            map.put("data", new JSONObject());
        });
        log.error(ex.getMessage());
        return map;
    }

    /**
     * 拦截自定义异常 CloudException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CloudException.class)
    public Map<String, Object> cloudExceptionHandler(CloudException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ex.getCode());
        map.put("info", ex.getInfo());
        map.put("data", ex.getData());
        return map;
    }

    /**
     * 拦截自定义异常 CloudException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = JSONException.class)
    public Map<String, Object> alibabaJSONException(JSONException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ExceptionConstant.参数解析错误.getCode());
        map.put("info", ExceptionConstant.参数解析错误.getMsg());
        map.put("data", ex.getMessage());
        return map;
    }

}
