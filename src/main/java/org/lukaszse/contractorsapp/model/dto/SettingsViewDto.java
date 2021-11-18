package org.lukaszse.contractorsapp.model.dto;

import org.lukaszse.contractorsapp.model.Settings;


public class SettingsViewDto {

    private Integer id;
    private String companyName;
    private String street;
    private Integer property;
    private String post;
    private String city;
    private String country;
    private Integer phone;

    public SettingsViewDto() {}

    public SettingsViewDto(Settings settings) {
        this.id = settings.getId();
        this.companyName = settings.getCompanyName();
        this.street = settings.getStreet();
        this.property = settings.getProperty();
        this.post = settings.getPost();
        this.city = settings.getCity();
        this.country = settings.getCountry();
        this.phone = settings.getPhone();
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
