package com.krlch.jsonpostgres.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krlch.jsonpostgres.entities.Person;
import com.krlch.jsonpostgres.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("json")
    public void json() {
//        URL url = this.getClass().getClassLoader().getResource("people.json");
//
//        if (url != null) {
        File jsonFile = null;

        try {
            jsonFile = ResourceUtils.getFile("classpath:people.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Person> people = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {
            });

            personRepository.saveAll(people);
            logger.info("All records saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } else {
//            logger.warn("URL is null.");
//        }
    }
}
