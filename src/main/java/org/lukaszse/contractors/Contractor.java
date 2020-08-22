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
    @GeneratedValue
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;

    private String name;

    private Integer phone;

    // == Hibernate (JPA) needs it
    public Contractor() {
    }
}
