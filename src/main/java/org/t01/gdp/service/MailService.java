package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.t01.gdp.config.MailProperties;

import java.util.Iterator;
import java.util.List;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    public void sendMessage(String messageNotice,String mailAddress){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getFrom());
        message.setTo(mailAddress);

        message.setSubject("来自GDP平台的消息");
        message.setText("[GDP]" + messageNotice);
        javaMailSender.send(message);
    }

    public void sendVerificationCode(String verificationCode,String mailAddress){
        String verificationMessage = "您的验证代码为：" + verificationCode + ",此代码五分钟内有效";
        sendMessage(verificationMessage,mailAddress);
    }

    public void patchSendSingleMessage(String messageNotice, List<String> mailAddresses){
        Iterator<String> iterator = mailAddresses.iterator();

        while(iterator.hasNext()){
            sendMessage(messageNotice,iterator.next());
        }
    }
}
