package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {

    @GetMapping(path = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonDTO getPersonById(@PathVariable  String id) {
        return PersonDTO.builder().id(id).vorname("John").nachname("Doe").build();

    }
}
