package de.db.webapp.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA
@Entity
@Table(name="tbl_barkeeper")

public class BarKeeper {
    @Id
    private String id;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH })
    private Bar bar;

    private String keepername;
}
