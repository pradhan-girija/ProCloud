package com.example.demo1;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.datastax.driver.core.utils.UUIDs;
import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.Data;
import org.springframework.data.cassandra.core.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("Tacoorders")

public class Order implements Serializable {

    private static long serialerid=12L;
    @PrimaryKey
    private UUID id= UUIDs.timeBased();
    private Date placedat=new Date();
    @Column("user")
    private UserUDT user;
    // delivery and credit card properties omitted for brevity's sake
    @Column("tacos")
    private List<TacoUTD> tacos=new ArrayList<>();
    public void addDesign(TacoUTD design) {
        this.tacos.add(design);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Ingridents> getIngridents() {
        return ingridents;
    }

    public void setIngridents(List<Ingridents> ingridents) {
        this.ingridents = ingridents;
    }

    @NotBlank(message = "Name is required")
    private String Id;
    private String name;
    private Date created;
    List<Ingridents> ingridents;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @NotBlank(message = "street is required")
    private String street;
    @NotBlank(message = "street is required")
    private String city;
    @NotBlank(message = "state is required")
    private String state;
    @NotBlank(message = "zip is required")
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private String DeliveryName;
    private String DeliveryStreet;
    private String DeliveryCity;

    public String getDeliveryName() {
        return DeliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        DeliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return DeliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        DeliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return DeliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        DeliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return DeliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        DeliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return DeliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        DeliveryZip = deliveryZip;
    }

    private  String DeliveryState;
    private String  DeliveryZip;

    public Order(){};


}
