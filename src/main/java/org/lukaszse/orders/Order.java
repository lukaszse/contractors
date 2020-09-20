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

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_description")
    private String orderDescription;

    // TODO: Add relation ManyToOne, between orders and contractors


    // == constructors ==
    public Order(){
    }

    public Order(Integer contractorId, String orderName, String orderDescription) {
        this.contractorId = contractorId;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
    }
}
