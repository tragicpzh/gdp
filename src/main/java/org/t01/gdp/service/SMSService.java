package org.t01.gdp.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    private static final Logger LOG = LoggerFactory.getLogger(SMSService.class);

    public String sendVerificationCode(String verificationCode,String phoneNumbers){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G3iWLyMA49yi2JDhP96", "4JWFndA15V8EY4z5EQAjiiefrGyw1q");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "四川大学JAVA实习项目");
        request.putQueryParameter("TemplateCode", "SMS_193246110");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + verificationCode + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ServerException e) {
            LOG.error(e.getMessage());
            return "SERVER_ERROR";
        } catch (ClientException e) {
            LOG.error(e.getMessage());
            return "CLIENT_ERROR";
        }
    }
//因网站未上线，目前暂时没法使用
//    public String sendBatchSMS(){
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G3iWLyMA49yi2JDhP96", "4JWFndA15V8EY4z5EQAjiiefrGyw1q");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendBatchSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumberJson", "[\"17361040193\",\"13634280868\"]");
//        request.putQueryParameter("SignNameJson", "[\"四川大学JAVA实习项目\",\"四川大学JAVA实习项目\"]");
//        request.putQueryParameter("TemplateCode", "SMS_193246110");
//        request.putQueryParameter("TemplateParamJson", "[{\"code\":\"1234\"},{\"code\":\"2234\"}]");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            return response.getData();
//        } catch (ServerException e) {
//            e.printStackTrace();
//            return "SERVER_ERROR";
//        } catch (ClientException e) {
//            e.printStackTrace();
//            return "CLIENT_ERROR";
//        }
//    }
}
