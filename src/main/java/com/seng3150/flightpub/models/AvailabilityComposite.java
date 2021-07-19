/*
    Maps the composite keys for Availabilty model
*/

package com.seng3150.flightpub.models;

import java.io.Serializable;
import java.util.Date;

public class AvailabilityComposite implements Serializable {

    private Airlines airline;
    private String flightNumber;
    private Date departureTime;
    private String classCode;
    private TicketType ticketType;
}
