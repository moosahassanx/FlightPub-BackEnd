/*
    TicketType.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ticket_type")
public class TicketType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ticket_code", nullable = false)
    private String ticketCode;

    @Column(name = "exchangeable", nullable = false)
    private Boolean exchangeable;

    @Column(name = "frequent_flyer_points", nullable = false)
    private Boolean frequentFlyerPoints;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "refundable", nullable = false)
    private Boolean refundable;

    @Column(name = "transferable", nullable = false)
    private Boolean transferable;

}
