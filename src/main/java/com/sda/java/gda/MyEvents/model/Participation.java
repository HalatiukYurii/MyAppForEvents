package com.sda.java.gda.MyEvents.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "participation")
public class Participation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Boolean acessOrganization;

    @Column(nullable = false)
    private Boolean acessParticipant;
}
