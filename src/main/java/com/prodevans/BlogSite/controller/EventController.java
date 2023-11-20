package com.prodevans.BlogSite.controller;


import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.model.Event;
import com.prodevans.BlogSite.service.EventService;
import com.prodevans.BlogSite.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/getDates")
    public ResponseEntity getDates(){
        return new ResponseEntity<>(eventService.getDate(),HttpStatus.FOUND);
    }
    /**
     *
     * @param event
     * @return
     */
    @PostMapping("/create-event")
    public ResponseEntity saveEvent(@RequestBody Event event)
    {
        try {
            System.out.println(event.getEventDate());
            eventService.createEvent(event);
            return ResponseEntity.ok("Data Saved Successfull");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @return
     */
    @GetMapping("/getAllEvent")
    public ResponseEntity getAllEvent(){
       try {
           List<Event> eventList=eventService.getAll();
           ResponseEntity response=new ResponseEntity<>(eventList,HttpStatus.FOUND);
           return response;
       }catch (Exception e){
           return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id){
       try {
           eventService.deleteEvent(id);
           return new ResponseEntity<>("Data Deleted Successful",HttpStatus.ACCEPTED);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    /**
     *
     * @param event
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity updateEvent(@PathVariable Event event){
        try {
            Event event1=eventService.updateEvent(event);
            return new ResponseEntity<>(event1,HttpStatus.OK);
        }catch (CustomException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_MODIFIED);
        }
    }
}
