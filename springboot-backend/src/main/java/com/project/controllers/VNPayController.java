package com.project.controllers;

import com.project.dtos.ResponseObject;
import com.project.services.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "*")
@PreAuthorize("permitAll()")
@RequestMapping("/api/v1/vnpay")
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;
    @PostMapping("/submitOrder")
    public ResponseEntity<?> submitOrder(@RequestParam("amount") int orderTotal,
                                      @RequestParam("orderInfo") String orderInfo,
                                      HttpServletRequest request){
        String baseUrl = request.getHeader("X-Forwarded-Host");
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Create payment successfully",vnpayUrl));
    }
    @GetMapping("/vnpay-payment")
    public RedirectView getUrlPayment(HttpServletRequest request, Model model){
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestURL.append("?").append(queryString);
        }
        String fullUrl = requestURL.toString();
        int paymentStatus = vnPayService.orderReturn(request);
        RedirectView redirectView = new RedirectView();

        redirectView.setUrl("https://tuyenkc.github.io/shoesshop-frontend/payment-status?paymentstatus="+ paymentStatus);
        return redirectView;
    }

}
