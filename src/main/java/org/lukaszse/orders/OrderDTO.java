package org.lukaszse.orders;

import lombok.Data;
import org.lukaszse.contractors.Contractor;
import org.lukaszse.contractors.ContractorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class OrderDTO {

    // == fields ==

    // from Order class, stored in database
    private Integer orderNumber;

    private String orderName;

    // from Contractor class, stored in database
    private  Integer contractorId;



    // == constructors ==
    public OrderDTO(Integer contractorId, Integer orderNumber, String orderName) {
        this.orderNumber = orderNumber;
        this.orderName = orderName;
        this.contractorId = contractorId;
    }
}
