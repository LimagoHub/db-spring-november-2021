package de.db.webapp.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Version;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA annotaionen + Repo
public class SchweinEntity {

    private String id;
    private String name;
    private int gewicht;

    @Version
    private long version;
}
