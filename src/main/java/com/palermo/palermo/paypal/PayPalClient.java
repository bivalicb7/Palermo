/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.paypal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author djbil
 */

@Component
public class PayPalClient {
    String clientId = "AVzlY5bP5lzrqO9NBO-6o04gtDyFm-HdUTqNNP-0NYJDDSD8YyXVjlptqwNoxh73i-2Nalfd5iRzqfsX";
String clientSecret = "EAm8G2hWTJlmx5TlnznAwz1l8Hs5ELeqKuoVt-UeKL1o8Q26iVCx1JZXcQN5Spa9RvMAJJOm47ybpp6J";
public Map<String, Object> createPayment(String sum){
    Map<String, Object> response = new HashMap<String, Object>();
    Amount amount = new Amount();
    amount.setCurrency("USD");
    amount.setTotal(sum);
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(transaction);

    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");

    Payment payment = new Payment();
    payment.setIntent("sale");
    payment.setPayer(payer);
    payment.setTransactions(transactions);

    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl("http://localhost:8080/payment/cancel");
    redirectUrls.setReturnUrl("http://localhost:8080/palermo/payment/afterdonation");
    payment.setRedirectUrls(redirectUrls);
    Payment createdPayment;
    try {
        String redirectUrl = "";
        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
        createdPayment = payment.create(context);
        if(createdPayment!=null){
            List<Links> links = createdPayment.getLinks();
            for (Links link:links) {
                if(link.getRel().equals("approval_url")){
                    redirectUrl = link.getHref();
                    break;
                }
            }
            response.put("status", "success");
            response.put("redirect_url", redirectUrl);
        }
    } catch (PayPalRESTException e) {
        System.out.println("Error happened during payment creation!");
    }
    return response;
}
public Map<String, Object> completePayment(HttpServletRequest req){
    Map<String, Object> response = new HashMap();
    Payment payment = new Payment();
    payment.setId(req.getParameter("paymentId"));

    PaymentExecution paymentExecution = new PaymentExecution();
    paymentExecution.setPayerId(req.getParameter("PayerID"));
    try {
        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
        Payment createdPayment = payment.execute(context, paymentExecution);
        if(createdPayment!=null){
            response.put("status", "success");
            response.put("payment", createdPayment);
        }
    } catch (PayPalRESTException e) {
        System.err.println(e.getDetails());
    }
    return response;
}
}
