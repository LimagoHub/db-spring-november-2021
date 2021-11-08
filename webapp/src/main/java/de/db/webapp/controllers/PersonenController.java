package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
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

    @ApiResponse(responseCode = "200", description = "Liste wurde erstellt")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @GetMapping(path = "", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PersonDTO>> findAll(@RequestParam(defaultValue = "", required = false) String vorname, @RequestParam(defaultValue = "", required = false) String nachname) {

        System.out.println(vorname);
        System.out.println(nachname);
        List<PersonDTO> result = List.of(
                PersonDTO.builder().id("1").vorname("John").nachname("Doe").build(),
                PersonDTO.builder().id("1").vorname("John").nachname("Wayne").build(),
                PersonDTO.builder().id("1").vorname("John").nachname("Wick").build(),
                PersonDTO.builder().id("1").vorname("John").nachname("McClaine").build(),
                PersonDTO.builder().id("1").vorname("John").nachname("Rambo").build(),
                PersonDTO.builder().id("1").vorname("John Boy").nachname("Walton").build()
        );

        return ResponseEntity.ok(result);
    }
    @ApiResponse(responseCode = "204", description = "Person wurde geloescht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable  String id){
        if(id.startsWith("1")){
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ApiResponse(responseCode = "200", description = "Person wurde geaendert")
    @ApiResponse(responseCode = "201", description = "Person wurde erfasst")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate(@RequestBody  PersonDTO person){
        System.out.println("Person wurde gespeichert");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}