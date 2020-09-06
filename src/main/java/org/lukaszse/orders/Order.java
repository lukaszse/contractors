package org.lukaszse.orders;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "orders")
public class Order {

    // == fields ==
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;

    @Column(name = "contractor_id")
    private Integer contractorId;

    private Integer number;

    private String name;


    // == constructors ==

    public Order(){
    }

    public Order(Integer contractorId, Integer number, String name) {
        this.contractorId = contractorId;
        this.number = number;
        this.name = name;
    }
}
