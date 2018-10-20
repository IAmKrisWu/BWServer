package com.junxi.baiweiserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class SimpleMailService {
    @Autowired
    JavaMailSender sender;

    @Autowired
    TemplateEngine engine;

//    ggbyplnctvjbbghg
    public void sendSimpleMail(){
//        创建一封简单的邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("462329521@qq.com");
        simpleMailMessage.setTo("kristin_wu@sina.com");
        simpleMailMessage.setCc("876708579@qq.com");
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("这是一封测试邮件");
        sender.send(simpleMailMessage);
    }

    public void sendHTMLMail() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
//        通过mimeMessageHelper设置邮件相关参数
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("462329521@qq.com");
        mimeMessageHelper.setTo("kristin_wu@sina.com");
//        抄送地址
        mimeMessageHelper.setCc("876708579@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setSubject("测试邮件");

//        设置thymeleaf中的变量
        Context context = new Context();
        context.setVariable("name","kris");
        context.setVariable("position","java高级工程师");
        context.setVariable("salary","30k");
//        使用thymeleaf的模版引擎，手动渲染页面，生成一个html字符串
        String process = engine.process("mail.html", context);
//        第二个参数设置为true表示发送的是html
        mimeMessageHelper.setText(process,true);
        sender.send(mimeMessage);

    }
}
