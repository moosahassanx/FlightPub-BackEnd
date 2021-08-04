package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {
    @Query(value = "INSERT INTO payment" +
            "(price, user_id)" +
            "VALUES (?1, ?2)", nativeQuery = true)
    ResponseEntity<?> makeRPayment(double price, int userId);


    @Query(value = "INSERT INTO payment" +
            "(price, guest_user_id)" +
            "VALUES (?1, ?2)", nativeQuery = true)
    ResponseEntity<?> makeGPayment(double price, int guestUserId);
}