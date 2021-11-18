package org.lukaszse.contractorsapp.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


/*
 * This is setting class which includes datas about company and all user properties
 * */


@Slf4j
@Data
@Entity(name = "settings")
public class Settings {

    // == entity fields ==
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;

    @NotBlank(message = "Company Name must not be empty")
    private String companyName;

    @NotBlank(message = "Field street must not be empty")
    private String street;

    @NotNull(message = "Field phone must not be empty")
    @Positive(message = "Field must be positive")
    private Integer property;

    @NotBlank(message = "Field post must not be empty")
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}")
    private String post;

    @NotBlank(message = "Field city must not be empty")
    private String city;

    @NotBlank(message = "Field country must not be empty")
    private String country;

    @NotNull(message = "Field phone must not be empty")
    private Integer phone;


    // == constructors ==
    public Settings() {}


}
