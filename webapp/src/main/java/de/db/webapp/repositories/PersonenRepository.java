package de.db.webapp.repositories;

import de.db.webapp.repositories.PersonCustomRepository;
import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.repositories.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> ,PersonCustomRepository {

    Iterable<PersonEntity> findByVorname(String vorname);
    Iterable<PersonEntity> findByVornameAndNachname(String vorname, String nachname);
    Iterable<PersonEntity> findByVornameOrNachnameOrderByNachnameDesc(String vorname, String nachname);
    List<PersonEntity> herbert();

    @Query("from PersonEntity")
    Iterable<PersonEntity> xyz();

    @Query("Select p.id, p.nachname from PersonEntity p")// Projektion
    Iterable<Object[]> abc();

    @Query("select new de.db.webapp.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findTinyPersons();

    @Query("update PersonEntity set vorname=:vorname")// Patch
    void updateVorname(String vorname);
}
