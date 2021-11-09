package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.Bar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BarRepository extends CrudRepository<Bar, String> {

    @Query("Select b from Bar b left join fetch b.keepers k where k.keepername = 'John'")
    Iterable<Bar> findAllBarsWithKeepers();
}
