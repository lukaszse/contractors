package org.lukaszse.contractorsapp.orders.DTO;

import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.contractors.Contractor;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.contractors.ContractorsRepository;
import org.lukaszse.contractorsapp.orders.Order;
import org.lukaszse.contractorsapp.orders.OrdersService;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class OrderReader {

    // == fields ==
    private Integer id;

    private LocalDate orderDate;

    private Integer contractorId;

    private Contractor contractor;

    private String price;

    private String orderName;

    private String orderDescription;


    // == constructors ==
    public OrderReader() {
    }

    public OrderReader(Order order) {
        if(order != null) {
            this.id = order.getId();
            this.orderDate = order.getOrderDate();
            this.contractorId = order.getContractor().getId();
            this.contractor = order.getContractor();
            this.price = String.format("%.2f", order.getPrice());
            this.orderName = order.getOrderName();
            this.orderDescription = order.getOrderDescription();
        } else {
            throw new IllegalArgumentException("Order object must not be null");
        }

    }

    // TODO Setters to be deleted

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Integer getContractorId() {
        return contractorId;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getPrice() {
        return price;
    }

    void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    void setPrice(String price) {
        this.price = price;
    }

    void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
}
