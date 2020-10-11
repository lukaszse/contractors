package org.lukaszse.contractorsapp.orders.DTO;

import org.lukaszse.contractorsapp.contractors.Contractor;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.orders.Order;

import java.time.LocalDate;

public class OrderWriter {

    // == fields ==
    private LocalDate orderDate;

    private int contractorId;

    private String price;

    private String orderName;

    private String orderDescription;


    // == constructors ==

    OrderWriter () {
    }

    OrderWriter(LocalDate orderDate, int contractorId, String price, String orderName, String orderDescription) {
        this.orderDate = orderDate;
        this.contractorId = contractorId;
        this.price = price;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
    }


    public Order toOrder(final ContractorService service) {
        return new Order(
                            service.getContractor(contractorId),
                            orderName,
                        orderDescription
        );
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
}
