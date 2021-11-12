package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
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

    public void BulkInsert(List<Person> personen) throws Exception{
        for (Person p: personen) {
            speichernOderAendern(p);
        }
    }

    @Override
    @Async
    public void speichernOderAendern(Person person) throws PersonenServiceException {
        System.out.println("Threadid = " + Thread.currentThread().getId());
        try {


            Thread.sleep(2000);
            speichernImpl(person);
        } catch (Exception e) {
            // Loggen
            throw new PersonenServiceException("Fehler im Service", e);
        }
    }

    private void speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);



        repo.save(mapper.convert(person));


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
        validate(person);
        return loeschen(person.getId());
    }

    @Override
    public boolean loeschen(String id) throws PersonenServiceException {
        try {
            if(repo.existsById(id)){
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
           throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Optional<Person> findePersonNachId(String id) throws PersonenServiceException {
        try {

            return repo.findById(id).map(mapper::convert);
        } catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {

        try {
            return mapper.convert(repo.findAll());

        } catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }
}
