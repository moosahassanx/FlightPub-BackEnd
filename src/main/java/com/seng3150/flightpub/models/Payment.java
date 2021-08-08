/*
    Payment.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "payment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "guest_user_id", nullable = true)
    private Long guestUserId;

    public Payment() {

    }
}
