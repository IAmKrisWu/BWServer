package com.junxi.baiweiserver.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class MailService implements Runnable {

    JavaMailSender sender;
    TemplateEngine engine;

    public MailService(JavaMailSender sender, TemplateEngine engine) {
        this.sender = sender;
        this.engine = engine;
    }

    @Override
    public void run() {
        MimeMessage mimeMessage = sender.createMimeMessage();
//        通过mimeMessageHelper设置邮件相关参数
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("462329521@qq.com");
            mimeMessageHelper.setTo("kristin_wu@sina.com");
            mimeMessageHelper.setCc("876708579@qq.com");
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setSubject("测试邮件");

            Context context = new Context();
            context.setVariable("name","kris");
            context.setVariable("position","java高级工程师");
            context.setVariable("salary","30k");
//        使用thymeleaf的模版引擎，手动渲染页面，生成一个html字符串
            String process = engine.process("mail.html", context);
//        第二个参数设置为true表示发送的是html
            mimeMessageHelper.setText(process,true);
            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
