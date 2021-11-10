package de.db.webapp.controllers.dtos;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDTO {
    @NotNull
    @Size(min=36, max=36)
    private String id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @DecimalMin(value="10",inclusive = true)
    private int gewicht;

    @Builder.Default
    @DecimalMin(value="0",inclusive = true)
    private long version=0;


}
