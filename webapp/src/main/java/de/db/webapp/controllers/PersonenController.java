package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.controllers.mapper.PersonDTOMapper;
import de.db.webapp.services.PersonenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@AllArgsConstructor
public class PersonenController {

    private final PersonenService service;
    private final PersonDTOMapper mapper;

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @GetMapping(path = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable  String id) throws Exception{

        return ResponseEntity.of(service.findePersonNachId(id).map(mapper::convert));
    }

    @ApiResponse(responseCode = "200", description = "Liste wurde erstellt")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @GetMapping(path = "", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDTO>> findAll(@RequestParam(defaultValue = "", required = false) String vorname, @RequestParam(defaultValue = "", required = false) String nachname) throws Exception{
        Thread.sleep(1000);
        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }

    @ApiResponse(responseCode = "204", description = "Person wurde geloescht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable  String id) throws Exception{
        if(service.loeschen(id)){
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ApiResponse(responseCode = "200", description = "Person wurde geaendert")
    @ApiResponse(responseCode = "201", description = "Person wurde erfasst")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody  PersonDTO person) throws Exception{
        System.out.println(Thread.currentThread().getId());

        // Async
        service.speichernOderAendern(mapper.convert(person));

        return ResponseEntity.ok().build();

    }

//    @ApiResponse(responseCode = "200", description = "Person wurde geaendert")
//    @ApiResponse(responseCode = "201", description = "Person wurde erfasst")
//    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
//    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> saveOrUpdateNotIdempotent(@RequestBody  PersonDTO person, UriComponentsBuilder builder)  throws Exception{
//        person.setId(UUID.randomUUID().toString()); // Hier nicht sinnvoll
//        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(person.getId());
//        if(service.speichernOderAendern(mapper.convert(person)))
//            return ResponseEntity.ok().build();
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @PostMapping(path="/toUpper", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE) // Ersatzget
    public ResponseEntity<PersonDTO> toUpper (@RequestBody  PersonDTO person) {
        person.setVorname(person.getVorname().toUpperCase());
        person.setNachname(person.getNachname().toUpperCase());
        return ResponseEntity.ok(person);
    }
}
