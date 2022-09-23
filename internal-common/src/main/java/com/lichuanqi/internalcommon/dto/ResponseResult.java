package com.lichuanqi.internalcommon.dto;


import com.lichuanqi.internalcommon.constant.CommonStatusEnum;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 *
 * 这个  Accessors 注解有点意思
 * @param <T>
 */
@Data
@Accessors (chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;


    /**
     * 返回成功响应的方法
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data){
        return  new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }


    public static <T> ResponseResult fail (T data){
        return  new ResponseResult().setCode(CommonStatusEnum.FAIL.getCode()).setMessage(CommonStatusEnum.FAIL.getValue()).setData(data);

    }



    /**
     * 返回自定义失败， 提示错误码 和 错误信息
     * @param code
     * @param message
     * @return
     */
    public static  ResponseResult fail(int code, String message){
        return  new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 失败： 自定义失败， 错误码、提示信息、data
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static  ResponseResult fail(int code, String message,String data){
        return  new ResponseResult().setCode(code).setMessage(message).setData(data);
    }


}
