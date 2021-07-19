package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AvailabilityRepository extends JpaRepository<Availability, String>, JpaSpecificationExecutor<Availability> {

}