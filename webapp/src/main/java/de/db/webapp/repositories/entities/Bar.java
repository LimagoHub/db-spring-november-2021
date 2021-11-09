package de.db.webapp.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA
@Entity
@Table(name="tbl_bars")

public class Bar {
    @Id
    private String id;
    private String barname;
    @Builder.Default
    @OneToMany(mappedBy = "bar",cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH })
    private List<BarKeeper> keepers = new ArrayList<>();
}
