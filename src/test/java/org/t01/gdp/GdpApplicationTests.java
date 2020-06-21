package org.t01.gdp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.t01.gdp.config.MailProperties;
import org.t01.gdp.service.MailService;
import org.t01.gdp.service.VerificationService;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GdpApplicationTests {
	@Test
	void contextLoads() {
//		VerificationService.sendEmailVerificationCode("429050398@qq.com");
	}

}
