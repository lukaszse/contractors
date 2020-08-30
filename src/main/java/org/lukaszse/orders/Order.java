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
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "contractor_id")
    private Integer contractorId;

    @Column(name = "contractor_name")
    private String contractorName;

    @Column(name = "order_number")
    private Integer number;

    @Column(name = "order_name")
    private String name;

}
