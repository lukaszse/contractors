package org.lukaszse.contractorsapp.settings.DTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.lukaszse.contractorsapp.settings.Settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/*
 * This is setting class which includes datas about company and all user properties
 * */


public class SettingsReader {

    // == entity fields ==
    private Integer id;

    private String companyName;

    private String street;

    private Integer property;

    private String post;

    private String city;

    private String country;

    private Integer phone;


    // == constructors ==
    public SettingsReader() {}

    public SettingsReader(Settings settings) {
        this.id = settings.getId();
        this.companyName = settings.getCompanyName();
        this.street = settings.getStreet();
        this.property = settings.getProperty();
        this.post = settings.getPost();
        this.city = settings.getCity();
        this.country = settings.getCountry();
        this.phone = settings.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStreet() {
        return street;
    }

    public Integer getProperty() {
        return property;
    }

    public String getPost() {
        return post;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getPhone() {
        return phone;
    }
}
