package com.prize.prize_gzh.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:   SpringMVC数据校验证工具类
* @Author:         WJZ
* @CreateDate:     2018-11-30 19:34
*/
public class ValidatorUtils {
    /**
     * 校验实体类中字段是否正确
     * @param bindingResult 类型<br/> bindingResult  错误信息对象
     * @return Map<String,Object> 如果为Null,则说明无错误信息,校验通过,返回一个封装的错误信息集合map
     */
    public static Map<String,Object> fieldValidate(BindingResult bindingResult) {
        Map<String,Object> errorMap = null;
        boolean flag= bindingResult.hasErrors();
        if(flag) {
            errorMap =  new HashMap<String, Object>();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for(FieldError fieldError: errorList) {
                String field = fieldError.getField();
                String fieldErrorMessage=fieldError.getDefaultMessage();
                errorMap.put(field, fieldErrorMessage);
            }
        }
        return errorMap;
    }
}
