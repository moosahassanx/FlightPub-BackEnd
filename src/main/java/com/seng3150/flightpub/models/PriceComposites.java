/*
    maps the composite keys for price model
*/
package com.seng3150.flightpub.models;

import java.io.Serializable;
import java.util.Date;

public class PriceComposites implements Serializable {

    private String airlineCode;
    private String flightNumber;
    private TicketClass ticketClass;
    private TicketType ticketType;
    private Date startDate;

}
