package de.db.webapp.application;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.controllers.mapper.PersonDTOMapper;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonenHandlerImpl implements PersonenHandler {
    private final PersonDTOMapper mapper;
    private final PersonenService service;

    @Override
    @Async
    public void handleErfassen(PersonDTO dto) {
        try {
            service.speichernOderAendern(mapper.convert(dto));
            // Event success
        } catch (PersonenServiceException e) {
            // Event failure
            throw new RuntimeException(e);
        }
    }
    @Override
    @Async
    public void handleLoeschen(String id) {
        try {
            service.loeschen(id);
            // Event success
        } catch (PersonenServiceException e) {
            // Event failure
            throw new RuntimeException(e);
        }
    }




}
