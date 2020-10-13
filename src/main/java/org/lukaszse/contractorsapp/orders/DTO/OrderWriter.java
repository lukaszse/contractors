package org.lukaszse.contractorsapp.orders.DTO;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.contractors.Contractor;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.orders.Order;
import org.lukaszse.contractorsapp.orders.OrdersService;
import org.slf4j.Logger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
public class OrderWriter {

    // == fields ==
    private Integer id;

    private LocalDate orderDate;

    @NotNull(message = "Field Contractor must not be empty")
    private Integer contractorId;

    @NotBlank(message = "Field Price must not be null")
    @Pattern(regexp = "^[0-9]+([.,][0-9]{1,2})?", message = "Price must contain integer optionally with 1 or 2 decimal places")
    private String  price;

    @NotBlank(message = "Field Order Name must not be empty")
    private String orderName;

    @NotBlank(message = "Field Order Description must not be empty")
    private String orderDescription;


    // == constructors ==

    OrderWriter () {
    }

/*    OrderWriter(LocalDate orderDate, int contractorId, String price, String orderName, String orderDescription) {
        this.orderDate = orderDate;
        this.contractorId = contractorId;
        this.price = price;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
    }*/


    public Order toOrder(final OrdersService ordersService, final ContractorService service) {

        // TODO this method is to be revised (improvement possible)

        price = price.replace(',', '.');

        log.info("!!!! Id = {}", id);
        if(id == null) {
            log.info("ADDING ORDER");
            return new Order(
                    service.getContractor(contractorId),
                    new BigDecimal(price),
                    orderName,
                    orderDescription
            );
        } else {
            log.info("UPDATING ORDER");
            Order order = ordersService.getOrder(id);
            order.setContractor(service.getContractor(contractorId));
            order.setPrice(new BigDecimal(price));
            order.setOrderName(orderName);
            order.setOrderDescription(orderDescription);
            return order;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
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

    public Integer getContractorId() {
        return contractorId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public static Logger getLog() {
        return log;
    }


}
