package com.lichuanqi.verificationcode.controller;

import com.lichuanqi.internalcommon.dto.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *  验证码生产接口
 */
@RestController
public class NumberCodeController {


    @GetMapping("/numberCode/{size}")
    public ResponseResult<String> numberCode(@PathVariable("size") int size){
        System.out.println("size" + size);


        Integer result = numberCodeProduction(size);


        return ResponseResult.success(result);



    }

    /**
     *    n位随机数生成方法。
     * @param size
     * @return
     */
    public Integer numberCodeProduction(Integer size){
        // Math.random()*9 + 1 是为了防止首位是0，或者全部为0 ，  后面乘以10的次方，是为了取整数作为最终的结果。
        double mathRandom = (Math.random()*9 + 1) * (Math.pow(10,size-1));

        int intResult = (int) mathRandom;
        return intResult;
    }

}
