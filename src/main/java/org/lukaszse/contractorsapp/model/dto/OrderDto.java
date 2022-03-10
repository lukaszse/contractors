package org.lukaszse.contractorsapp.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
}
