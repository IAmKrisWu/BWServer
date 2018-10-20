package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.RespBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login_page")
//    设置响应的状态
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RespBean unlogin(){
        return RespBean.error("未登录");
    }
}
