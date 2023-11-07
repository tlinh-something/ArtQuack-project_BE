package com.swp.ArtQuack.controller;
import com.swp.ArtQuack.service.EmailService;
import com.swp.ArtQuack.view.EmailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    EmailService emailService;

    @GetMapping("test")
    public void sendMail(){
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient("leanhduy16022002@gmail.com");
        emailDetail.setSubject("test123");
        emailDetail.setMsgBody("aaa");
        emailService.sendMailTemplate(emailDetail,"emailtemplate");
    }
}
