/*
    EDesinations.java
        - Helper enum, file to match the country name to country code
*/

package com.seng3150.flightpub.enums;

public class EDestinations {

    public enum DestinationCodes {

        Adelaide("ADL"),
        Amsterdam("AMS"),
        Atlanta("ATL"),
        Bangkok("BKK"),
        Brisbane("BNE"),
        Canberra("CBR"),
        Paris("CDG"), // Charles De airport
        Cairns("CNS"),
        Doha("DOH"),
        Darwin("DRW"),
        Dubai("DXB"),
        Rome("FCO"),
        Rio("GIG"),
        Hobart("HBA"),
        Helsinki("HEL"),
        HongKong("HKG"),
        Honolulu("HNL"),
        NewYork1("JFK"), // New york jfk airport
        Johannesburg("JNB"),
        KualaLumpur("KUL"),
        LosAngeles("LAX"),
        NewYork2("LGA"), // New york laguardia airport
        London1("LGW"), // London gatwick
        London2("LHR"), // London heathrow
        Madrid("MAD"),
        Melbourne("MEL"),
        Miami("MIA"),
        Munich("MUC"),
        Tokyo("NRT"),
        GoldCoast("OOL"),
        Chicago("ORD"),
        Paris2("ORY"),
        Perth("PER"), // Orly airport
        SanFrancisco("SFO"),
        Singapore("SIN"),
        Sydney("SYD"),
        Vienna("VIE"),
        Toronto("YYZ");

        public final String text;

        DestinationCodes(String text) {
            this.text = text;
        }

        public String getCode() {
            return this.text;
        }

        // Finds the country name and returns the country code
        public static String fromString(String text) {
            for (DestinationCodes b : DestinationCodes.values()) {
                if (b.name().equals(text)) {
                    return b.getCode();
                }
            }
            return null;
        }
    }


}
