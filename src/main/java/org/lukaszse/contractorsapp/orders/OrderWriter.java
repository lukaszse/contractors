package org.lukaszse.contractorsapp.orders;

import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.contractors.Contractor;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.contractors.ContractorsRepository;

import javax.persistence.*;

public class OrderWriter {

    // == fields ==
    private int contractorId;

    private String orderName;

    private String orderDescription;


    // == constructors ==
    public OrderWriter() {
    }

    public OrderWriter(Order order) {
        this.contractorId = order.getContractor().getId();
        this.orderName = order.getOrderName();
        this.orderDescription = order.getOrderDescription();
    }

    public OrderWriter(int contractorId, String orderName, String orderDescription) {
        this.contractorId = contractorId;
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
