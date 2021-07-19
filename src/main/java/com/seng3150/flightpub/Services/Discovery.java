/*
    Discovery.java
        - A helper class used to store FlightPubs destination latitude and longitudes
        - Will be extended to provide the backend implementation of the no-item search discovery feature of FlightPub
 */
package com.seng3150.flightpub.Services;

import java.util.HashMap;

public class Discovery {

    private HashMap<String, String> countryAspects = new HashMap<>();
    public HashMap<String, Double[]> countryLatLng = new HashMap<>();

    public Discovery(){createMap();}

    // Creating the lat/lng for destinations
    public void createMap() {
        countryLatLng.put("ADL", new Double[] {34.92,138.60});
        countryLatLng.put("AMS", new Double[] {52.36,4.90});
        countryLatLng.put("ATL", new Double[] {33.74,84.38});
        countryLatLng.put("BKK", new Double[] {13.75,100.50});
        countryLatLng.put("BNE", new Double[] {27.47,153.02});
        countryLatLng.put("CBR", new Double[] {35.28,149.13});
        countryLatLng.put("CDG", new Double[] {48.85,2.35});
        countryLatLng.put("CNS", new Double[] {16.92,145.77});
        countryLatLng.put("DOH", new Double[] {25.28,51.53});
        countryLatLng.put("DRW", new Double[] {12.46,130.84});
        countryLatLng.put("DXB", new Double[] {25.20,55.27});
        countryLatLng.put("FCO", new Double[] {41.90,12.49});
        countryLatLng.put("GIG", new Double[] {22.90,43.17});
        countryLatLng.put("HBA", new Double[] {42.88,147.32});
        countryLatLng.put("HEL", new Double[] {60.16,24.93});
        countryLatLng.put("HKG", new Double[] {22.31,114.16});
        countryLatLng.put("HNL", new Double[] {21.30,157.85});
        countryLatLng.put("JFK", new Double[] {40.64,73.77});
        countryLatLng.put("JNB", new Double[] {26.20,28.04});
        countryLatLng.put("KUL", new Double[] {3.13,101.68});
        countryLatLng.put("LAX", new Double[] {34.05,118.24});
        countryLatLng.put("LGA", new Double[] {40.71,74.0});
        countryLatLng.put("LGW", new Double[] {51.15,0.18});
        countryLatLng.put("LHR", new Double[] {51.47,0.45});
        countryLatLng.put("MAD", new Double[] {40.41,3.70});
        countryLatLng.put("MEL", new Double[] {37.81,144.96});
        countryLatLng.put("MIA", new Double[] {25.76,80.19});
        countryLatLng.put("MUC", new Double[] {48.13,11.58});
        countryLatLng.put("NRT", new Double[] {35.77,140.39});
        countryLatLng.put("OOL", new Double[] {28.01,153.40});
        countryLatLng.put("ORD", new Double[] {41.87,87.62});
        countryLatLng.put("ORY", new Double[] {48.72,2.36});
        countryLatLng.put("PER", new Double[] {31.95,115.86});
        countryLatLng.put("SFO", new Double[] {37.7,122.4});
        countryLatLng.put("SIN", new Double[] {1.35,103.8});
        countryLatLng.put("SYD", new Double[] {33.8,151.2});
        countryLatLng.put("VIE", new Double[] {48.2,16.3});
        countryLatLng.put("YYZ", new Double[] {43.6,79.3});
    }
}
