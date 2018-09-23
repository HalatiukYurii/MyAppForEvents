package com.sda.java.gda.MyEvents.model;

import lombok.Data;

@Data
public class Comment {
    private Event event;
    private User user;
    private String content;
}
