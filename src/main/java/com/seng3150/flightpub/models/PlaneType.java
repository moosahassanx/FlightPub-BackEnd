/*
    PlaneType.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "plane_type")
public class PlaneType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "plane_code", nullable = false)
    private String planeCode;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "economy", nullable = false)
    private Integer economy;

    @Column(name = "num_business", nullable = false)
    private Integer numBusiness;

    @Column(name = "num_first_class", nullable = false)
    private Integer numFirstClass;

    @Column(name = "num_premium_economy", nullable = false)
    private Integer numPremiumEconomy;

}
