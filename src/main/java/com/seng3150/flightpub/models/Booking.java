/*
    Booking.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;
import lombok.Generated;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "booking")
@IdClass(BookingComposite.class)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToOne
    @JoinColumn(name = "user_id" )
    private User user;

    @OneToOne
    @JoinColumn(name = "guest_user_id")
    private GuestUser guestUser;

    @ManyToOne
    private Flights flight;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;


}
