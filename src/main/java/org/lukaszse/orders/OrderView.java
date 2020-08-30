package org.lukaszse.orders;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Data
@Entity
@Immutable
@Table(name = "orders_list")
public class OrderView {

    @Id
    private Integer id;

    private Integer contractorId;

    private Integer number;

    private String name;
}
