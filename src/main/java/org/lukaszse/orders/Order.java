package org.lukaszse.orders;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    @Column(name = "contractor_id")
    private int contractorid;

    private int number;

    private String name;

/*    private String description;

    @Column(name = "order_date")
    private LocalDate orderDate;*/

}
