package org.lukaszse.contractorsapp.orders.DTO;

import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.contractors.Contractor;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.contractors.ContractorsRepository;
import org.lukaszse.contractorsapp.orders.Order;
import org.lukaszse.contractorsapp.orders.OrdersService;

import javax.persistence.*;
import java.time.LocalDate;

public class OrderReader {

    // == fields ==
    private int id;

    private LocalDate orderDate;

    private int contractorId;

    private Contractor contractor;

    private String price;

    private String orderName;

    private String orderDescription;


    // == constructors ==
    public OrderReader() {

        // this is just for test
        // TODO this needs to be deleted and SQL base tables are to be revised.
        //  NullPointerExeption protection to be done for particular fields;

/*        this.id = null;
        this.orderDate = LocalDate.now();
        this.contractorId = 0;
        this.contractor = null;
        this.price = "0.00";
        this.orderName = "";
        this.orderDescription = "";*/
    }

    public OrderReader(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.contractorId = order.getContractor().getId();
        this.contractor = order.getContractor();
        this.price = String.format("%10.2f", order.getPrice());
        this.orderName = order.getOrderName();
        this.orderDescription = order.getOrderDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
