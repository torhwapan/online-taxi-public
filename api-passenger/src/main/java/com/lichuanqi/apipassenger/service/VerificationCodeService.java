package com.lichuanqi.apipassenger.service;


import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    public String generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        System.out.println("伪代码，调用验证码服务获取验证码");
        String code = "123456";



        // 存入redis




        // 返回值


        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");


        return result.toString();
    }


}
