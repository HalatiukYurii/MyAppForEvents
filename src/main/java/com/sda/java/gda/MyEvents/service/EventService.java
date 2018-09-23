package com.sda.java.gda.MyEvents.service;

import com.sda.java.gda.MyEvents.exeption.BindingResultException;
import com.sda.java.gda.MyEvents.exeption.NotFoundException;
import com.sda.java.gda.MyEvents.model.Access;
import com.sda.java.gda.MyEvents.model.Event;
import com.sda.java.gda.MyEvents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    private List<Event> events = new ArrayList<>();

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> search(String name, Access access) {
        return eventRepository.findByNameContainingAndAccess(name, access);
    }

    public Event create(Event event, BindingResult bindingResult) {
        validateEvent(event.getName(), null, bindingResult);
        return eventRepository.save(event);
    }

    public Event getById(UUID id) {
        return eventRepository.findById(id)
        .orElseThrow(()->
             new NotFoundException(String.format("Event with id %s does not find",id)));

    }

    public void delete(UUID id) {
       if(!eventRepository.existsById(id)){
           throw new NotFoundException(String.format("Event with id %s does not find",id));
       }
       eventRepository.deleteById(id);
    }

    public Event update(Event event,UUID id, BindingResult bindingResult) {
        Event existingEvent  = getById(id);
                validateEvent(event.getName(),existingEvent.getName(),bindingResult);
               event.setId(id);
                return eventRepository.save(event);
    }

    public void validateEvent(String name, String currentName, BindingResult bindingResult) {
        if (eventRepository.existsByName(name)&& !name.equals(currentName)){
                    bindingResult.addError(new FieldError("Event","name",String.format("Event %s already exist",name)));
        }
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
    }

    public void remove(UUID id) {
        if(!eventRepository.existsById(id)){
            throw new NotFoundException(String.format("Product with id %s does not find", id));
        }
         eventRepository.deleteById(id);
    }
}
