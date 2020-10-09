package org.lukaszse.contractorsapp.orders;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.contractors.Contractor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_description")
    private String orderDescription;




    // == constructors ==
    public Order(){
    }

    public Order(Contractor contractor, String orderName, String orderDescription) {
        this.contractor = contractor;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
    }
}
