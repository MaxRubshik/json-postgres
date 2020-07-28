package com.krlch.jsonpostgres.repositories;

import com.krlch.jsonpostgres.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
