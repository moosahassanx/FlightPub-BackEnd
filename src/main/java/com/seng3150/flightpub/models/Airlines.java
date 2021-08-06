/*
    Airlines.java
        - Models the DB table and maps the contraints via annotations
*/

package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "airlines")
public class Airlines implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "airline_code", nullable = false)
    private String airlineCode;

    @Column(name = "airline_name", nullable = false)
    private String airlineName;

    @Column(name = "sponsored", nullable = true)
    private Integer sponsored;

    @ManyToOne
    @JoinColumn(name = "country_code3", insertable = false, updatable = false)
    private Country countryCode3;

}
