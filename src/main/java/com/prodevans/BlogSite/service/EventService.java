package com.prodevans.BlogSite.service;

import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.model.Event;

import java.util.List;

public interface EventService {
    public Event createEvent(Event event);
    public Event deleteEvent(Integer id) throws Exception;
    public List<Event> getAll();
    public Event updateEvent(Event event) throws CustomException;
}
