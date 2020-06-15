package org.t01.gdp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.t01.gdp.config.MailProperties;
import org.t01.gdp.service.MailService;
import org.t01.gdp.service.VerificationService;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class GdpApplicationTests {
	@Test
	void contextLoads() {
		VerificationService.sendEmailVerificationCode("");
	}

}
