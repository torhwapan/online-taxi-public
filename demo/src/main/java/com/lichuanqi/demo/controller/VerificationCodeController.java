package com.lichuanqi.demo.controller;


import com.lichuanqi.demo.request.VerificationCodeDTO;
import com.lichuanqi.demo.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码
 */
@RestController
public class VerificationCodeController {


    @Autowired
    private VerificationCodeService verificationCodeService;


    @GetMapping("/verification-code")
    public String verficationCode(@RequestBody VerificationCodeDTO dto){

        String passengerCode = dto.getPassengerPhone();
        System.out.println("手机号参数：" + passengerCode);
        return verificationCodeService.generatorCode(passengerCode);
    }
}
