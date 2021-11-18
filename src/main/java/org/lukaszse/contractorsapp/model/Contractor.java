package org.lukaszse.contractorsapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@NoArgsConstructor
@Table( name = "contractors" )
public class Contractor {

    // == fields ==

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "contractor")
    @JsonManagedReference
    private Set<Order> orders;

    @NotBlank(message = "Field Name must not be empty.")
    private String name;

    @NotBlank(message = "Field Street must not be empty.")
    private String street;

    @Positive(message = "Property Value must be positive")
    @NotNull(message = "Field Property must not be empty")
    private Integer property;

    @NotBlank(message = "Field Post must not be empty.")
    private String post;

    @NotBlank(message = "Field City must not be empty.")
    private String city;

    @NotBlank(message = "Field Country must not be empty.")
    private String country;

    @Positive(message = "Phone must be positive number")
    @NotNull(message = "Field Phone must not be empty")
    private Long phone;
}
