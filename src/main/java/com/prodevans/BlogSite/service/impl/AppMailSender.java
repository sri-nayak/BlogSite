package com.prodevans.BlogSite.service.impl;


import com.prodevans.BlogSite.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class AppMailSender {
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;

    @Autowired
    public AppMailSender(JavaMailSender javaMailSender,TemplateEngine templateEngine) {
        this.templateEngine=templateEngine;
        this.javaMailSender = javaMailSender;
    }

    /**
     * @param toUser
     */
    public void sendMailTo(String toUser) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("HR Prodevans");
        simpleMailMessage.setTo(toUser);
        simpleMailMessage.setSubject("Booking Slot for Demo is Done");
        simpleMailMessage.setText("Booking for slot is done on that particular day");
        javaMailSender.send(simpleMailMessage);
    }


    public void sendToListOfUser(String listOfUser, Context  message, String subject,String mailTemplate)  {
        MimeMessage mimeMailMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMailMessage,"UTF-8");
        try {
            String message1=templateEngine.process(mailTemplate,message);
            mimeMessageHelper.setTo(listOfUser);
            mimeMessageHelper.setText(message1,true);
            mimeMessageHelper.setSubject("testemail");
            javaMailSender.send(mimeMailMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
