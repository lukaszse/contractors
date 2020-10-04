package org.lukaszse.contractorsapp.orders;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Data
@Entity
@Immutable
@Table(name = "orders_list")
public class OrderView {

    @Id
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "contractor_id")
    private Integer contractorId;

    @Column(name = "contractor_name")
    private String contractorName;


    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_description")
    private String orderDescription;
}
