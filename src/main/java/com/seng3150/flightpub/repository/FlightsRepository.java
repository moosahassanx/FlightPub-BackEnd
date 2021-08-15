/*
    FlightRepository.java
        - Extends JPARepo and allows access to DB
*/

package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights, String>, JpaSpecificationExecutor<Flights> {

    // returns a list of the flights that match the destination code
    // that gets passed in from the user, can easily add date restriction after the query
    @Query(value = "SELECT * " +
            "from flights " +
            "JOIN (select destinations.destination_code " +
            "FROM destinations " +
            "WHERE destination_code = ?1) " +
            "as ref on ref.destination_code=flights.destination_code " +
            " WHERE flights.departure_time between ?2 AND ?3", nativeQuery = true)
    List<Flights> getTrendingFlights(String code, String firstDate, String secondDate);
//    total_price, price.airline_code, price.flight_number, Departure_Time, arrival_time, departure_code, destination_code, price.ticket_code

//    "SELECT * " +
//            "FROM flights " +
//            "JOIN price " +
//            "ON flights.flight_number = price.flight_number " +
//            "WHERE flights.departure_Code = ?1 " +
//            "AND flights.destination_Code = ?2 " +
//            "AND datediff(day, flights.departure_time, ?3) = 0"

    @Query(value = "SELECT f.*" +
            "FROM flights f " +
            "FULL OUTER JOIN airlines a ON a.airline_code = f.airline_code " +
            "FULL OUTER JOIN destinations d ON f.destination_code = d.destination_code " +
            "WHERE f.departure_Code = ?1 " +
            "AND f.destination_Code = ?2 " +
            "AND datediff(day, f.departure_time, ?3) = 0 " +
            "AND  d.COVID = 0 " +
            "ORDER BY a.sponsored DESC", nativeQuery = true)
    List<Flights> findFlights(String from, String to, String date);
//    List<String> searchFlightByDestinationAndDates(String depart, String arrival,
//                                                   String departDate, String arriveDate);
    @Query(value = "SELECT f.*" +
            "FROM flights f " +
            "FULL OUTER JOIN airlines a ON a.airline_code = f.airline_code " +
            "FULL OUTER JOIN destinations d ON f.destination_code = d.destination_code " +
            "WHERE f.departure_Code = ?1 " +
            "AND f.destination_Code = ?2 " +
            "AND f.departure_time BETWEEN ?3 AND ?4 " +
            "AND  d.COVID = 0 " +
            "ORDER BY a.sponsored DESC", nativeQuery = true)
    List<Flights> findFlexFlights(String depart, String arrive, String date, String date2);

}
