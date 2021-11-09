package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;
    @Mock
    private PersonenRepository repoMock;
    @Mock
    private PersonMapper mapperMock;
    @Mock
    private List<String> antipathenMock;

    private final Person validPerson = Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname("Doe").build();

    @Test
    void speichern_parameterNull_throwsPersonenException() {
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichernOderAendern(null));
        assertEquals("Parameter darf nicht null sein.", ex.getMessage());
    }
    @Test
    void speichern_vornameNull_throwsPersonenException() {
        Person p = Person.builder().id(UUID.randomUUID().toString()).vorname(null).nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichernOderAendern(p));
        assertEquals("Vorname ist zu kurz.", ex.getMessage());
    }

    @Test
    void speichern_vornameZuKurz_throwsPersonenException() {
        Person p = Person.builder().id(UUID.randomUUID().toString()).vorname("J").nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichernOderAendern(p));
        assertEquals("Vorname ist zu kurz.", ex.getMessage());
    }
    @Test
    void speichern_Antipath_throwsPersonenException() {
        Person p = Person.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build();
        when(antipathenMock.contains(anyString())).thenReturn(true);
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichernOderAendern(p));
        assertEquals("Antipath.", ex.getMessage());
    }
    @Test
    void speichern_UnexpectedExceptionInUnderlyingService_throwsPersonenException() {
        PersonEntity expected = PersonEntity.builder().id("012345678901234567890123456789012345").vorname("John").nachname("Doe").build();
        when(repoMock.save(any())).thenThrow(new ArithmeticException());
        when(mapperMock.convert((Person) any())).thenReturn(expected);
        when(antipathenMock.contains(anyString())).thenReturn(false);
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichernOderAendern(validPerson));
        assertEquals("Fehler im Service", ex.getMessage());
        verify(repoMock, times(1)).save(expected);
    }

}