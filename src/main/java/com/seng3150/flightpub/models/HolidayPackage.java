/*
    HolidayPackage.java
        - Models the DB table and maps the contraints via annotations
*/

package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;



@Data
@Entity
@Table(name = "holiday_package")
public class HolidayPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    public HolidayPackage(){
    
    }

    public HolidayPackage(Integer id, String ac, String an, String dc, String a, Integer dp, String hn, Integer hsr, String hd, String psd, String ped, String tu){
        this.holiday_package_id = id;
        this.airline_code = ac;
        this.airline_name = an;
        this.destination_code = dc;
        this.airport = a;
        this.discount_percentage = dp;
        this.hotel_name = hn;
        this.hotel_star_rating = hsr;
        this.hotel_description = hd;
        this.package_start_datetime = psd;
        this.package_end_datetime = ped;
        this.target_user = tu;
    }

    @Id
    @Column(name = "holiday_package_id", nullable = false)
    private Integer holiday_package_id;

    @Column(name = "airline_code", nullable = false)
    private String airline_code;

    @Column(name = "airline_name", nullable = false)
    private String airline_name;

    @Column(name = "destination_code", nullable = false)
    private String destination_code;

    @Column(name = "airport", nullable = false)
    private String airport;

    @Column(name = "discount_percentage", nullable = false)
    private Integer discount_percentage;

    @Column(name = "hotel_name", nullable = true)
    private String hotel_name;

    @Column(name = "hotel_star_rating", nullable = true)
    private Integer hotel_star_rating;

    @Column(name = "hotel_description", nullable = true)
    private String hotel_description;

    @Column(name = "package_start_datetime", nullable = false)
    private String package_start_datetime;

    @Column(name = "package_end_datetime", nullable = false)
    private String package_end_datetime;

    @Column(name = "target_user", nullable = false)
    private String target_user;

    private Integer getHolidayPackageId(){
        return this.holiday_package_id;
    }

    private void setHolidayPackageId(Integer id){
        this.holiday_package_id = id;
    }

    private String getAirlineCode(){
        return this.airline_code;
    }

    private void setAirlineCode(String ac){
        this.airline_code = ac;
    }

    private String getAirlineName(){
        return this.airline_name;
    }

    private void setAirlineName(String an){
        this.airline_name = an;
    }

    private String getDestinationCode(){
        return this.destination_code;
    }

    private void setDestinationCode(String dc){
        this.destination_code = dc;
    }
    
    private String getAirport(){
        return this.airport;
    }

    private void setAirport(String a){
        this.airport = a;
    }

    private Integer getDiscountPercentage(){
        return this.discount_percentage;
    }

    private void setDiscountPercentage(Integer dp){
        this.discount_percentage = dp;
    }

    private String getHotelName(){
        return this.hotel_name;
    }

    private void setHotelName(String hn){
        this.hotel_name = hn;
    }
    
    private Integer getHotelStarRating(){
        return this.hotel_star_rating;
    }

    private void getHotelStarRating(Integer hsr){
        this.hotel_star_rating = hsr;
    }
        
    private String getHotelDescription(){
        return this.hotel_description;
    }

    private void setHotelDescription(String hd){
        this.hotel_description = hd;
    }

    private String getPackageStartDate(){
        return this.package_start_datetime;
    }

    private void setPackageStartDate(String psd){
        this.package_start_datetime = psd;
    }

    private String getPackageEndDate(){
        return this.package_end_datetime;
    }

    private void setPackageEndDate(String ped){
        this.package_end_datetime = ped;
    }
    
    private String getTargetUser(){
        return this.target_user;
    }
    
    private void setTargetUser(String tu){
        this.target_user = tu;
    }
}