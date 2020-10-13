package org.lukaszse.contractorsapp.contractors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.orders.Order;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table( name = "contractors" )
public class Contractor {

    // == fields ==

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Order> orders;

    @NotBlank(message = "Field name must not be empty.")
    private String name;

    @NotBlank(message = "Field street must not be empty.")
    private String street;

    @Positive(message = "Property value must be positive")
    @NotNull(message = "Field property must not be empty")
    private Integer property;

    @NotBlank(message = "Field post must not be empty.")
    private String post;

    @NotBlank(message = "Field city must not be empty.")
    private String city;

    @NotBlank(message = "Field country must not be empty.")
    private String country;

    @Positive(message = "Phone must be positive number")
    @NotNull(message = "Field phone must not be empty")
    private Long phone;


    // == Hibernate (JPA) needs it
    public Contractor() {
    }


}
