package org.lukaszse.contractors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String name;

    private String street;

    private Integer property;

    private String post;

    private String city;

    private String country;

    private Integer phone;

    // == Hibernate (JPA) needs it
    public Contractor() {
    }

    public Contractor(String name,
                      String street,
                      Integer property,
                      String post,
                      String city,
                      String country,
                      Integer phone) {

        this.name = name;
        this.street = street;
        this.property = property;
        this.post = post;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }


}
