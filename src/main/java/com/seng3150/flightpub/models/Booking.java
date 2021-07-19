/*
    Booking.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "booking")
@IdClass(BookingComposite.class)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment_complete", nullable = false)
    private String paymentComplete;

    @ManyToOne
    private Flights flight;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;

}
