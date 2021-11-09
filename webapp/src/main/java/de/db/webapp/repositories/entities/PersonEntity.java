package de.db.webapp.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA
@Entity
@Table(name="tbl_personen")

@NamedQuery(name="PersonEntity.herbert", query = "select p from PersonEntity p")
public class PersonEntity {

    @Id
    @Column(nullable = false, length = 36)
    private String id;
    @Column(length = 30)
    private String vorname;
    @Column(length = 30, nullable = false)
    private String nachname;
//    @Column(name="age", nullable = false)
//    private int alter=18;
}
