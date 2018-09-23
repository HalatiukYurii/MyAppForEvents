package com.sda.java.gda.MyEvents.repository;

import com.sda.java.gda.MyEvents.model.Access;
import com.sda.java.gda.MyEvents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event,UUID>{
    List<Event> findByNameContainingAndAccess(String name, Access access);
boolean existsByName(String name);
}

