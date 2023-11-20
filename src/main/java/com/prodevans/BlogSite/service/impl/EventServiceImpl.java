package com.prodevans.BlogSite.service.impl;

import com.prodevans.BlogSite.Repository.EventRepository;
import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.exception.DataIsNotFoundException;
import com.prodevans.BlogSite.model.Event;
import com.prodevans.BlogSite.model.payload.EventDateSlot;
import com.prodevans.BlogSite.service.EventService;
import com.prodevans.BlogSite.service.TechStackService;
import com.prodevans.BlogSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("EventService")
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private TechStackService techStackService;
    private UserService userService;

    /**
     * @param eventRepository
     * @param techStackService
     * @param userService
     */
    @Autowired
    public EventServiceImpl(EventRepository eventRepository, TechStackServiceImpl techStackService, UserServiceImpl userService) {
        this.techStackService = techStackService;
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    public List getDate() {
        LocalDate dateTime = LocalDate.now();
        Date currentDate = Date.valueOf(dateTime);
        List<Event> eventList=null;
        try {
            eventList  = eventRepository.findByEventDateGreaterThanEqual(currentDate).stream().toList();

        }catch (Exception e){
            e.printStackTrace();
        }
        List<Event> finalEventList = eventList;
        List<EventDateSlot> eventDateSlots = dateTime.datesUntil(dateTime.plusDays(30))
                .filter(date -> date.getDayOfWeek().equals(DayOfWeek.FRIDAY))
                .map(date -> {
                    List<Event> events = finalEventList.stream().filter(event -> event.getEventDate().compareTo(Date.valueOf(date)) == 0).toList();
                    if (events.isEmpty()) {
                        return new EventDateSlot(Date.valueOf(date), false, false);
                    } else {
                        if (events.size() >= 2)
                            return new EventDateSlot(events.get(0).getEventDate(), true, true);
                        else
                            return new EventDateSlot(events.get(0).getEventDate(), true, false);
                    }
                }).toList();
        return eventDateSlots;
    }

    /**
     * @param event
     * @return
     */

    @Override
    public Event createEvent(Event event) {
        try {
            event.setTools(event.getTools().stream().
                    map(techStack -> techStackService.getTechStack(techStack)).collect(Collectors.toSet()));
            event.setPartiName(event.getPartiName().stream()
                    .map(users -> userService.createUser(users)).collect(Collectors.toSet()));
            return eventRepository.save(event);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }

    /**
     * @param id
     * @return
     * @throws DataIsNotFoundException
     */
    @Override
    public Event deleteEvent(Integer id) throws DataIsNotFoundException {
        if (eventRepository.findById(id).isEmpty()) {
            throw new DataIsNotFoundException("Couldn't find and data Associated with id " + id);
        } else {
            eventRepository.deleteById(id);
            return eventRepository.findById(id).get();
        }
    }

    /**
     * @return
     */

    @Override
    public List<Event> getAll() {
        List<Event> eventList;
        try {
            eventList = eventRepository.getAllEvent();
            return eventList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * @param event
     * @return
     * @throws CustomException
     */
    @Override
    public Event updateEvent(Event event) throws CustomException {
        try {
            event.setTools(event.getTools().stream().map(techStack -> techStackService.getTechStack(techStack)).collect(Collectors.toSet()));
            event.setPartiName(event.getPartiName().stream().map(users -> userService.createUser(users)).collect(Collectors.toSet()));
            eventRepository.save(event);
            return event;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
