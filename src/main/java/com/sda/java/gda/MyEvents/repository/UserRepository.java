package com.sda.java.gda.MyEvents.repository;

import com.sda.java.gda.MyEvents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findOneByUsername(String username);
    Boolean existsByUsername(String username);
}
