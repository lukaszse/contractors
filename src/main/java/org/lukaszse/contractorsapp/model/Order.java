package org.lukaszse.contractorsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.model.Contractor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;

    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @NotNull(message = "Field Price must not be null")
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @NotBlank(message = "Field Order Name must not be empty")
    @Column(name = "order_name")
    private String orderName;

    @NotBlank(message = "Field Order Description must not be empty")
    @Column(name = "order_description")
    private String orderDescription;

    public Order(Contractor contractor, BigDecimal price, String orderName, String orderDescription) {
        this.orderDate = LocalDate.now();
        this.contractor = contractor;
        this.price = price;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
    }
}
