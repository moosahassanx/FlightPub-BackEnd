/*
    DestinationsRepository.java
        - Extends JPARepo allows access to DB
*/

package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationsRepository extends JpaRepository<Destinations, String>, JpaSpecificationExecutor<Destinations> {

    // Flips boolean value of destination
    // Inputs airport name to narrow search
    // Inputs boolean state from json as a change request
    @Modifying
    @Query(value = "UPDATE destinations " +
            "SET blacklisted = ?2 " +
            "WHERE airport = ?1", nativeQuery = true)
    void blacklistDestination(String airport, String blacklistState);

    // getting all of the destinations in the db
    @Query(value = "SELECT * FROM destinations ", nativeQuery = true)
    List<Destinations> findDestinations();

    // Selects the top 10 destinations that have been books more than once
    // Orders by highest to lowers
    // removed "WHERE destinations.times_booked > 0 ORDER BY destinations.times_booked DESC" from query
    @Query(value = "SELECT TOP 10 airport FROM destinations ", nativeQuery = true)
    List<String> findDestinationName();

}
