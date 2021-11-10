package de.db.webapp.services.models;

import lombok.*;

import javax.persistence.Version;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {
    @Setter(AccessLevel.PRIVATE)
    private String id;
    private String name;
    @Setter(AccessLevel.PRIVATE)
    @Builder.Default
    private int gewicht=10;
    @Setter(AccessLevel.PRIVATE)
    private long version;

    public void fuettern(){
        setGewicht(getGewicht() + 1);
    }
}
