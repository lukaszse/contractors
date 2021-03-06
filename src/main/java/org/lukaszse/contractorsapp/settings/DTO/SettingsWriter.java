package org.lukaszse.contractorsapp.settings.DTO;

import org.lukaszse.contractorsapp.settings.Settings;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


/*
 * This is setting class which includes datas about company and all user properties
 * */


public class SettingsWriter {

    // == entity fields ==
    private Integer id;

    @NotBlank(message = "Company Name must not be empty")
    private String companyName;

    @NotBlank(message = "Field street must not be empty")
    private String street;

    @NotNull(message = "Field phone must not be empty")
    @Positive(message = "Field must be positive")
    private Integer property;

    @NotBlank(message = "Field post must not be empty")
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}", message = "Postal code format: xx-xxx")
    private String post;

    @NotBlank(message = "Field city must not be empty")
    private String city;

    @NotBlank(message = "Field country must not be empty")
    private String country;

    @NotNull(message = "Field phone must not be empty")
    private Integer phone;


    // == constructors ==
    public SettingsWriter() {}


    public Settings toSettings() {
        var newSetting = new Settings();
        newSetting.setCompanyName(companyName);
        newSetting.setStreet(street);
        newSetting.setProperty(property);
        newSetting.setPost(post);
        newSetting.setCity(city);
        newSetting.setCountry(country);
        newSetting.setPhone(phone);
        return newSetting;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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
