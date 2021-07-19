/*
    Tickets.java
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
@Table(name = "ticket_class")
public class TicketClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "class_code", nullable = false)
    private String classCode;

    @Column(name = "details", nullable = false)
    private String details;

}
