/*
    Distances.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "distances")
@IdClass(DistanceComposite.class)
public class Distances implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne
    @JoinColumn(name = "destination_code1", insertable = false, updatable = false)
    private Destinations destinationCode1;

    @Id
    @OneToOne
    @JoinColumn(name = "destination_code2", insertable = false, updatable = false)
    private Destinations destinationCode2;

    @Column(name = "distance_in_kms", nullable = false)
    private Integer distanceInKms;

}
