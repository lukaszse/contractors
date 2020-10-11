package org.lukaszse.contractorsapp.settings;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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

    private String companyName;

    private String street;

    private Integer property;

    private String post;

    private String city;

    private String country;

    private Integer phone;


    // == constructors ==
    public Settings() {}


}
