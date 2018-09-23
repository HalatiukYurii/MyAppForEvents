package com.sda.java.gda.MyEvents.repository;

import com.sda.java.gda.MyEvents.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client,String> {
    Optional<Client> findOneByClientId(String clientId);

}
