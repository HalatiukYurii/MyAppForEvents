package com.sda.java.gda.MyEvents.controller;

import com.sda.java.gda.MyEvents.model.Event;
import com.sda.java.gda.MyEvents.repository.EventRepository;
import com.sda.java.gda.MyEvents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    EventRepository eventRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create (@RequestBody @Valid Event event, BindingResult bindingResult){
        return eventService.create(event,bindingResult);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event update(
            @PathVariable("id")UUID id,
        @RequestBody @Valid Event event, BindingResult bindingResult  ){
        return eventService.update(event,id,bindingResult);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event get(@PathVariable("id")UUID id){
        return eventService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("id")UUID id){
         eventService.remove(id);
    }
}
