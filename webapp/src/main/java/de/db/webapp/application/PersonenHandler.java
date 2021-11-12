package de.db.webapp.application;

import de.db.webapp.controllers.dtos.PersonDTO;
import org.springframework.scheduling.annotation.Async;

public interface PersonenHandler {
    @Async
    void handleErfassen(PersonDTO dto);

    @Async
    void handleLoeschen(String id);
}
