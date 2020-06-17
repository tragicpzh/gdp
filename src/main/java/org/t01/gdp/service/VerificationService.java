package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//验证码服务类
@Service
@RequiredArgsConstructor
public class VerificationService {
    private final SMSService smsService;
    private final MailService mailService;
    private static Map<String, CodeAndOverdueTime> smsVerificationRecord = new HashMap<>();
    private static Map<String, CodeAndOverdueTime> mailVerificationRecord = new HashMap<>();
    private static final int CODEWIDTH = 10000;

    //清理过期的验证码，目前由Timer每6分钟调用一次
    public static void clearOverdueCode() {
        Iterator<Map.Entry<String, CodeAndOverdueTime>> iterator = smsVerificationRecord.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CodeAndOverdueTime> next = iterator.next();
            if (next.getValue().bOverdue()) {
                iterator.remove();
            }
        }

        iterator = mailVerificationRecord.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CodeAndOverdueTime> next = iterator.next();
            if (next.getValue().bOverdue()) {
                iterator.remove();
            }
        }
    }

    //发送SMS验证码，并记录发送的验证码
    public void sendSMSVerificationCode(String phoneNumber) {
        String code = "" + (int) (Math.random() * CODEWIDTH);
        smsService.sendVerificationCode(code, phoneNumber);
        CodeAndOverdueTime codeAndOverdueTime = new CodeAndOverdueTime(code);
        smsVerificationRecord.put(phoneNumber, codeAndOverdueTime);
    }

    //发送E-mail验证码，并记录发送的验证码
    public void sendEmailVerificationCode(String emailAddress) {
        String code = "" + (int) (Math.random() * CODEWIDTH);
        System.out.println(code);
        mailService.sendVerificationCode(code, emailAddress);
        CodeAndOverdueTime codeAndOverdueTime = new CodeAndOverdueTime(code);
        mailVerificationRecord.put(emailAddress, codeAndOverdueTime);
    }

    //验证SMS验证码，包括是否正确，是否过期，并在验证成功之后清除该验证码
    public boolean smsVerify(String code, String phoneNumber) {
        CodeAndOverdueTime record = smsVerificationRecord.get(phoneNumber);
        if (record != null && record.verify(code)) {
            smsVerificationRecord.remove(phoneNumber);
            return true;
        }
        return false;
    }

    //验证E-mail验证码，包括是否正确，是否过期，并在验证成功之后清除该验证码
    public boolean emailVerify(String code, String emailAddress) {
        CodeAndOverdueTime record = mailVerificationRecord.get(emailAddress);
        if (record != null && record.verify(code)) {
            mailVerificationRecord.remove(emailAddress);
            return true;
        }
        return false;
    }
}

class CodeAndOverdueTime {
    private static final int VALIDTIMELONG = 300000;

    private String code;
    private Date overdueTime;

    public CodeAndOverdueTime(String code) {
        this.code = code;
        overdueTime = new Date(new Date().getTime() + VALIDTIMELONG);
        System.out.println(overdueTime);
    }

    public String getCode() {
        return code;
    }

    public boolean verify(String inputCode) {
        return code.equals(inputCode) && overdueTime.after(new Date());
    }

    public boolean bOverdue() {
        return overdueTime.before(new Date());
    }
}