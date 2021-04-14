package com.gb.hw_lesson12_finalproject.repo;

import com.gb.hw_lesson12_finalproject.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findOneByFirstname(String firstname);
    Optional<Person> findByLogin(String login);
}
