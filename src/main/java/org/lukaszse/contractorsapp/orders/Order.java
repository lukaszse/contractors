package org.lukaszse.contractorsapp.orders;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.contractors.Contractor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    private BigDecimal price;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_description")
    private String orderDescription;




    // == constructors ==
    public Order(){
    }

    public Order(Contractor contractor, BigDecimal price, String orderName, String orderDescription) {
        this.orderDate = LocalDate.now();
        this.contractor = contractor;
        this.price = price;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
    }
}
