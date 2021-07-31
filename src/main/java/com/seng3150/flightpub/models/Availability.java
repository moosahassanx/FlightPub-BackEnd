/*
    Availability.java
        - Models the DB table and maps the contraints via annotations
*/

package com.seng3150.flightpub.models;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "availability")
@IdClass(AvailabilityComposite.class)
public class Availability implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "airline_code")
    private Airlines airline;

    @Id
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Id
    @Column(name = "departure_time", nullable = false)
    private Timestamp departureTime;

    @Id
    @Column(name = "class_code", nullable = false)
    private String classCode;

    @Id
    @OneToOne
    @JoinColumn(name = "ticket_code")
    private TicketType ticketType;

    @Column(name = "number_available_seats_leg1", nullable = false)
    private int numberAvailableSeatsLeg1;

    @Column(name = "number_available_seats_leg2")
    private int numberAvailableSeatsLeg2;

}
