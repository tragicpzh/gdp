package org.t01.gdp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.t01.gdp.config.MailProperties;
import org.t01.gdp.service.MailService;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class GdpApplicationTests {
	@Autowired
	MailService mailService;

	@Test
	void contextLoads() {
		mailService.sendVerificationCode("1234","461960391@qq.com");

		ArrayList<String> addresses = new ArrayList<>();
		System.out.println(addresses.add("461960391@qq.com"));

		mailService.patchSendSingleMessage("这是一封群发消息",addresses);
	}

}
