package com.gabriel.api2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.api2.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
