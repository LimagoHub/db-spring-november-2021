package de.db.webapp.controllers;

import de.db.webapp.controllers.dtos.PersonDTO;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.models.Person;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"classpath:/create.sql", "classpath:/insert.sql"})
@ExtendWith(SpringExtension.class)

class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService personenServiceMock;

    @Test
    public void test1() throws Exception{

        when(personenServiceMock.findePersonNachId(anyString())).thenReturn( Optional.of(Person.builder().id("123").vorname("John").nachname("Doe").build()));
        PersonDTO dto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        assertEquals("John", dto.getVorname());
    }

    @Test
    public void test2() throws Exception{
        when(personenServiceMock.findePersonNachId(anyString())).thenReturn( Optional.of(Person.builder().id("123").vorname("John").nachname("Doe").build()));
        String json = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(json);
    }

    @Test
    public void test3() throws Exception{

        when(personenServiceMock.findePersonNachId(anyString())).thenReturn( Optional.of(Person.builder().id("123").vorname("John").nachname("Doe").build()));
        ResponseEntity<PersonDTO> dtoResponse = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        PersonDTO dto = dtoResponse.getBody();
        assertEquals("John", dto.getVorname());
        assertEquals(HttpStatus.OK, dtoResponse.getStatusCode());
    }

    @Test
    public void test4() throws Exception{

        when(personenServiceMock.findePersonNachId(anyString())).thenReturn( Optional.empty());
        ResponseEntity<PersonDTO> dtoResponse = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, dtoResponse.getStatusCode());
    }

    @Test
    public void test5() throws Exception{

        when(personenServiceMock.findePersonNachId(anyString())).thenThrow(new PersonenServiceException());
        ResponseEntity<PersonDTO> dtoResponse = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, dtoResponse.getStatusCode());
    }

    @Test
    public void test6() throws Exception{

        when(personenServiceMock.findePersonNachId(anyString())).thenReturn( Optional.of(Person.builder().id("123").vorname("John").nachname("Doe").build()));
        ResponseEntity<PersonDTO> dtoResponse = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.GET,null, PersonDTO.class);

        assertEquals(HttpStatus.OK, dtoResponse.getStatusCode());
    }

    @Test
    public void test7() throws Exception{

        when(personenServiceMock.findeAlle()).thenReturn( List.of(new Person()));
        ResponseEntity<Iterable<PersonDTO>> dtoResponse = restTemplate.exchange("/v1/personen", HttpMethod.GET, null,
                new ParameterizedTypeReference<Iterable<PersonDTO>>() {
                });

        assertEquals(HttpStatus.OK, dtoResponse.getStatusCode());
    }
//    @Test
//    public void test8() throws Exception{
//
//        HttpEntity entity = new HttpEntity(PersonDTO.builder().build());
//        when(personenServiceMock.speichernOderAendern(any())).thenThrow(PersonenServiceException.class);
//        ResponseEntity<Void> dtoResponse = restTemplate.exchange("/v1/personen", HttpMethod.PUT, entity, Void.class);
//
//        assertEquals(HttpStatus.BAD_REQUEST, dtoResponse.getStatusCode());
//    }
//    @Test
//    public void test9() throws Exception{
//
//        String id = UUID.randomUUID().toString();
//        PersonDTO dto = PersonDTO.builder().id(id).vorname("John").nachname("Doe").build();
//        Person expected = Person.builder().id(id).vorname("John").nachname("Doe").build();
//        HttpEntity entity = new HttpEntity(dto);
//        when(personenServiceMock.speichernOderAendern(any())).thenReturn(true);
//        ResponseEntity<Void> dtoResponse = restTemplate.exchange("/v1/personen", HttpMethod.PUT, entity, Void.class);
//
//        assertEquals(HttpStatus.OK, dtoResponse.getStatusCode());
//        verify(personenServiceMock, times(1)).speichernOderAendern(expected);
//    }

//    @Test
//    public void test10() throws Exception{
//
//        String id = UUID.randomUUID().toString();
//        PersonDTO dto = PersonDTO.builder().id(id).vorname("John").nachname("Doe").build();
//        Person expected = Person.builder().id(id).vorname("John").nachname("Doe").build();
//        HttpEntity entity = new HttpEntity(dto);
//        when(personenServiceMock.speichernOderAendern(any())).thenReturn(false);
//        ResponseEntity<Void> dtoResponse = restTemplate.exchange("/v1/personen", HttpMethod.PUT, entity, Void.class);
//
//        assertEquals(HttpStatus.CREATED, dtoResponse.getStatusCode());
//        verify(personenServiceMock, times(1)).speichernOderAendern(expected);
//    }
//

}