package de.db.webapp.controllers;

import de.db.webapp.application.PersonenHandler;
import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.controllers.mapper.PersonDTOMapper;
import de.db.webapp.services.PersonenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/personen")
@AllArgsConstructor
public class PersonenCommandController {

    private final PersonenHandler handler;



    @ApiResponse(responseCode = "204", description = "Person wurde geloescht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable  String id) throws Exception{
        handler.handleLoeschen(id);
        return ResponseEntity.ok().build();
    }

    @ApiResponse(responseCode = "200", description = "Person wurde geaendert")
    @ApiResponse(responseCode = "201", description = "Person wurde erfasst")
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody  PersonDTO person) throws Exception{


        // Async
        handler.handleErfassen(person);

        return ResponseEntity.ok().build();

    }


}
