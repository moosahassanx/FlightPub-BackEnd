/*
    Flights.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "Flights")
@IdClass(FlightComposites.class)
public class Flights implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "airline_code", referencedColumnName = "airline_code",nullable = false, updatable = false)
    private Airlines airline;

    @Id
    @Column(name = "flight_number", nullable = false, updatable = false)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "departure_code",insertable = false, updatable = false)
    private Destinations departureCode;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "stop_over_code", insertable = false, updatable = false)
    private Destinations stopOverCode;

    @ManyToOne
    @JoinColumn(name = "destination_code", insertable = false, updatable = false)
    private Destinations destinationCode;

    @Id
    @Column(name = "departure_time", nullable = false)
    private Timestamp departureTime;

    @Column(name = "arrival_time_stop_over")
    private Timestamp arrivalTimeStopOver;

    @Column(name = "departure_time_stop_over")
    private Timestamp departureTimeStopOver;

    @Column(name = "arrival_time", nullable = false)
    private Timestamp arrivalTime;

    @OneToOne
    @JoinColumn(name = "plane_code")
    private PlaneType planeType;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "duration_second_leg")
    private Integer durationSecondLeg;

    @NotFound(action= NotFoundAction.IGNORE)
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "flight")
    private List<Booking> bookings;

}
