package org.lukaszse.contractorsapp.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.service.ContractorService;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.service.OrdersService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Slf4j
@NoArgsConstructor
public class OrderDto {

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
                    orderDescription);
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
}
