package de.db.webapp.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TinyPerson {

    private final String id;
    private final String nachname;
}
