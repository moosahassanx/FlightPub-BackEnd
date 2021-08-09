package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking>{

}