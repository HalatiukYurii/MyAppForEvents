package com.sda.java.gda.MyEvents.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {


    @Column(nullable = false, name = "date")
    private LocalDate date;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "adress")
    private String adress;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "access")
    private Access access;

    @Column(nullable = false, name = "organization")
    private String nameOrganization;


    public void updateFrom(Event event) {
        if (event.getName() != null) {
            this.name = event.getName();
        }
    }


}
