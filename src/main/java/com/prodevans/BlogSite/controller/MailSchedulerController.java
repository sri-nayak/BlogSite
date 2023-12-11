package com.prodevans.BlogSite.controller;


import com.prodevans.BlogSite.Repository.EventRepository;
import com.prodevans.BlogSite.model.Event;
import com.prodevans.BlogSite.model.payload.EventDateSlot;
import com.prodevans.BlogSite.model.payload.EventDateTime;
import com.prodevans.BlogSite.service.impl.AppMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MailSchedulerController {
    @Autowired
    private AppMailSender appMailSender;
    @Autowired
    private EventRepository eventRepository;
    @Scheduled(cron = "00 30 10 ? * MON-FRI")
    public  void sendMail()
    {
        System.out.println("scheduling");
    }
//    @Scheduled(fixedRate = 200000)
    public  void sendMailToUsers()
    {
        Context context=new Context();
        context.setVariable("name","Dear Team");
        context.setVariable("link","link");
        context.setVariable("cordinator","Prasana Nayak");
        context.setVariable("number","789845651");
        context.setVariable("email","dummy@prodevans.com");
        context.setVariable("from","Prodevans Team");
        context.setVariable("message","This is the test mail template which is to be invoked and send");
        appMailSender.sendToListOfUser("gopalkrushna647@gmail.com",context,null,"email");
    }
    public void sendCnfrmBooking(){

    }
//    @Scheduled(fixedRate = 20000)
//    @Scheduled(cron = "*")
    public  void sendMailToAdmin() {
        Context context=new Context();
        context.setVariable("name","Dear Team");
        Date date=Date.valueOf(LocalDate.now().plusDays(2));
        System.out.println(date);
        List<Event> eventList=eventRepository.findByEventDateGreaterThanEqual(Date.valueOf(LocalDate.now()));
        List<EventDateTime> eventDateTimeList=eventList.stream().filter(event -> event.getEventDate().compareTo(date)<=0).map(event -> new EventDateTime(event.getEventDate(),"15.30",event.getTopicName())).toList();
        List<String> nameList=new ArrayList<>();
        System.out.println(eventList.isEmpty());
        List<Event> list= eventList.stream().filter(event -> event.getEventDate().compareTo(date)<=0).toList();
        list.stream().forEach(li->{li.getPartiName().stream().forEach(users -> {
            nameList.add(users.getUserName());
        });});
        context.setVariable("lists",eventDateTimeList);
        context.setVariable("listName",nameList);
        context.setVariable("cordinator","Prasana Nayak");
        context.setVariable("number","7898456525");
        context.setVariable("email","dummy@prodevans.com");
        context.setVariable("from","Prodevans Team");
        appMailSender.sendToListOfUser("gopalkrushna647@gmail.com",context,null,"ackmail");
    }
}
