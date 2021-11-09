package de.db.webapp.controllers.mapper;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.services.models.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {

    Person convert(PersonDTO dto);
    PersonDTO convert(Person person);
    Iterable<PersonDTO> convert(Iterable<Person> personen);
}
