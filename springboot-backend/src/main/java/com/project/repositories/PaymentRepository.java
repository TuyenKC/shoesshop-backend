package com.project.repositories;

import com.project.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {
    boolean existsByPaymentName(String paymentName);

}
