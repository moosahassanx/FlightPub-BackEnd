/*
    maps the composite keys for flight model
*/
package com.seng3150.flightpub.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class FlightComposites implements Serializable {

    private Airlines airline;
    private String flightNumber;
    private Timestamp departureTime;

}
