package com.sda.java.gda.MyEvents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto extends BaseDto {
    protected String name;

//    public EventDto(Event event){
//        this(event.getId(),event.getName());
//    }
}
