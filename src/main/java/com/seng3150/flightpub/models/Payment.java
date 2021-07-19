/*
    Payment.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
