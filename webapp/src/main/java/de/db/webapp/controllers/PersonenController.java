package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @GetMapping(path = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable  String id) {
        Optional<PersonDTO> optional;
        if(id.startsWith("1")){
            optional =  Optional.of( PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());

        } else {
            optional = Optional.empty();
        }
        return ResponseEntity.of(optional);
    }
}
