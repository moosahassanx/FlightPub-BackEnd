/*
    Destinations.java
        - Models the DB table and maps the contraints via annotations
*/

package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "destinations")
public class Destinations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "destination_code", nullable = false)
    private String destinationCode;

    @Column(name = "airport", nullable = false)
    private String airport;

    @OneToOne
    @JoinColumn(name = "country_code3")
    private Country countryCode3;

    @Column(name = "times_booked", nullable = false)
    private Integer timesBooked;

    @Column(name = "blacklisted", nullable = false)
    private Integer blacklisted;

    @Column(name = "Tags", nullable = false)
    private String Tags;

}
