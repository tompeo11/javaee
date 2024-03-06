package com.tom.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="email")
    private String email;

    @Column(name="fullname")
    private String fullName;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="phone")
    private String phone;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name="password")
    private String password;

    @Column(name="register_date")
    private Date registerDate;

    public Customer(String email, String fullName, String address, String city, String country, String phone, String zipcode, String password, Date registerDate) {
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.zipcode = zipcode;
        this.password = password;
        this.registerDate = registerDate;
    }
}
