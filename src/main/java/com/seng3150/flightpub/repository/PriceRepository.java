package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price, String>, JpaSpecificationExecutor<Price> {
//    "AND  CAST(?2 as datetime) BETWEEN start_date AND end_date"
    @Query(value=   "SELECT min(total_price) " +
                    "FROM price "+
                    "WHERE flight_number = ?1 " , nativeQuery = true)
    double getLowPrice(String flightNum, String date);
}
