package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.models.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;
    private final List<String> antipathen;

    public PersonenServiceImpl(final PersonenRepository repo, final PersonMapper mapper,  @Qualifier("antipathen") final List<String> antipathen) {
        this.repo = repo;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }

    /*

        person ist null -> PSE
        vorname ist null -> PSE
        vorname < 2 Zeichen ->PSE
        nachname ist null -> PSE
        nachname < 2 Zeichen ->PSE
        Attila -> PSE
        Runtime in Repo -> PSE
        Happy Day -> Person -> repo


     */

    @Override
    public boolean speichernOderAendern(Person person) throws PersonenServiceException {
        try {
            return speichernImpl(person);
        } catch (RuntimeException e) {
            // Loggen
            throw new PersonenServiceException("Fehler im Service", e);
        }
    }

    private boolean speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);

        boolean result = repo.existsById(person.getId());

        repo.save(mapper.convert(person));

        return result;
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validate(person);

        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(antipathen.contains(person.getVorname()))
            throw new PersonenServiceException("Antipath.");
    }

    private void validate(Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Parameter darf nicht null sein.");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname ist zu kurz.");
    }

    @Override
    public boolean loeschen(Person person) throws PersonenServiceException {
        return false;
    }

    @Override
    public boolean loeschen(String id) throws PersonenServiceException {
        return false;
    }

    @Override
    public Optional<Person> findePersonNachId(String id) throws PersonenServiceException {
        return Optional.empty();
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        return null;
    }
}
