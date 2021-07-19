package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AirlinesRepository extends JpaRepository<Airlines, String>, JpaSpecificationExecutor<Airlines> {

}