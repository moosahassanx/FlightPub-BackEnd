/*
    Price.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "price")
@IdClass(PriceComposites.class)
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "airline_code", nullable = false)
    private String airlineCode;

    @Id
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Id
    @OneToOne
    @JoinColumn(name = "class_code")
    private TicketClass ticketClass;

    @Id
    @OneToOne
    @JoinColumn(name = "ticket_code")
    private TicketType ticketType;

    @Id
    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "price_leg1", nullable = true)
    private BigDecimal priceLeg1;

    @Column(name = "price_leg2", nullable = true)
    private BigDecimal priceLeg2;
}
