package org.example.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "street_address")
    private String streetAddress;

    @Size(max = 255)
    @Column(name = "city")
    private String city;
//
//    @Size(max = 255)
//    @Column(name = "state")
//    private String state;

    @Size(max = 255)
    @Column(name = "country")
    private String country;
//
//    @Size(max = 10)
//    @Column(name = "postal_code", length = 10)
//    private String postalCode;

}