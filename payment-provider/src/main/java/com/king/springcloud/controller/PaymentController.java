package com.king.springcloud.controller;

import com.king.springcloud.entities.CommonResult;
import com.king.springcloud.entities.Payment;
import com.king.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String server_port;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****"+result+"****");
        if(result > 0){
            return new CommonResult(200, "插入数据库成功+端口"+server_port , result);
        }else{
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****"+payment+"****！！！");
        if(payment != null){
            return new CommonResult(200, "查询成功:"+server_port, payment);
        }else{
            return new CommonResult(444, "无此id", null);
        }
    }
}
