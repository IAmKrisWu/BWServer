package com.junxi.baiweiserver;

import com.junxi.baiweiserver.service.SimpleMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaiweiserverApplicationTests {

	@Autowired
	SimpleMailService service;
	@Test
	public void contextLoads() {
		service.sendSimpleMail();
	}

	@Test
	public void sendHTMLMail(){
		try {
			service.sendHTMLMail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
