package com.project.services.impl;

import com.project.dtos.*;
import com.project.entities.Payment;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.repositories.PaymentRepository;
import com.project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment findById(String id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if(!payment.isPresent()){
            throw new AppException("Phương thức thanh toán không tồn tại","404");
        }
        return payment.get();
    }

    @Override
    public PaymentDto addPayment(AddPaymentDto addPaymentDto) {
        if(paymentRepository.existsByPaymentName(addPaymentDto.getPaymentName())){
            throw new AppException("Tên phương thức thanh toán đã tồn tại","409");
        }
        Payment payment = new DtoToEntityMapper().dtoToEntityAddPayment(addPaymentDto);
        Payment savedPayment = paymentRepository.save(payment);
        return new EntityToDtoMapper().entityToDtoPayment(savedPayment);
    }

    @Override
    public PaymentDto updatePayment(String id, UpdatePaymentDto updatepaymentDto) {
        if(paymentRepository.findById(id) == null)
            throw new AppException("Phương thức thanh toán không tồn tại", "404");
        Payment payment = paymentRepository.findById(id).get();
        if(!updatepaymentDto.getPaymentName().equals(payment.getPaymentName()) && paymentRepository.existsByPaymentName(updatepaymentDto.getPaymentName())){
            throw new AppException("Tên phương thức thanh toán đã tồn tại","409");
        }
        payment.setPaymentName(updatepaymentDto.getPaymentName());
        payment.setDescription(updatepaymentDto.getDescription());
        payment.setStatus(updatepaymentDto.getStatus());
        Payment savedPayment = paymentRepository.save(payment);
        return new EntityToDtoMapper().entityToDtoPayment(savedPayment);
    }

    @Override
    public void deletePayment(String id) {
        if(paymentRepository.findById(id) == null)
            throw new AppException("Phương thức thanh toán không tồn tại", "404");
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDto getPaymentById(String id) {
        if(!paymentRepository.findById(id).isPresent())
            throw new AppException("Phương thức thanh toán không tồn tại", "404");
        Payment payment = paymentRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoPayment(payment);
    }

    @Override
    public List<PaymentDto> getAllPayment() {
        List<PaymentDto> paymentDtos = paymentRepository.findAll().stream()
                .map(payment -> new EntityToDtoMapper().entityToDtoPayment(payment))
                .collect(Collectors.toList());
        return paymentDtos;
    }

    @Override
    public List<SelectPaymentDto> getAllPaymentValid() {
        List<SelectPaymentDto> selectPaymentDtos  = paymentRepository.findAll().stream()
                .filter(payment -> "Active".equals(payment.getStatus()))
                .map(payment -> new EntityToDtoMapper().entityToDtoSelectPayment(payment))
                .collect(Collectors.toList());
        return selectPaymentDtos;
    }
}
