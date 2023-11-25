package com.project.services;

import com.project.dtos.AddPaymentDto;
import com.project.dtos.PaymentDto;
import com.project.dtos.SelectPaymentDto;
import com.project.dtos.UpdatePaymentDto;
import com.project.entities.Payment;

import java.util.List;

public interface PaymentService {
    public Payment findById(String id);
    public PaymentDto addPayment(AddPaymentDto addPaymentDto);
    public PaymentDto updatePayment(String id, UpdatePaymentDto updatePaymentDto);
    public void deletePayment(String id);
    public PaymentDto getPaymentById(String id);
    public List<PaymentDto> getAllPayment();
    public List<SelectPaymentDto> getAllPaymentValid();

}
