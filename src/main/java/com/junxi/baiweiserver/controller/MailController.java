package com.junxi.baiweiserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junxi.baiweiserver.model.EmployeeTrain;
import com.junxi.baiweiserver.model.MailSend;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import java.util.Date;
import java.util.List;

@RestController
public class MailController {
//  spring自动为我们提供了线程池

    @Autowired
    JavaMailSender sender;
    @Autowired
    TemplateEngine engine;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/mail/send2")
    public RespBean sendMail2(){
//        执行线程
        threadPoolTaskExecutor.execute(new MailService(sender,engine));
        return RespBean.ok("发送成功");
    }

    @PostMapping("/mail/send")
    public RespBean sendMail(MailSend mailSend) throws JsonProcessingException {
        System.out.println(mailSend);
        ObjectMapper om = new ObjectMapper();
        jmsTemplate.convertAndSend("com.junxi.baiwei.mailsend",om.writeValueAsString(mailSend));
        return RespBean.ok("发送成功");
    }
}
