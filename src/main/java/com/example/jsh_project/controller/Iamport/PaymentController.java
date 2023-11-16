package com.example.jsh_project.controller.Iamport;

import com.example.jsh_project.Service.PurchaseService;
import com.example.jsh_project.domain.Dto.request.CancelRequest;
import com.example.jsh_project.domain.Entity.ShoppingList;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000" )
public class PaymentController {
    private final RefundService refundService;
    private final PurchaseService purchaseService;
    private IamportClient iamportClient;
    @Value("${imp.api.key}")
    private String imp_key;

    @Value("${imp.api.secretkey}")
    private String imp_secret;
    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(imp_key, imp_secret);
    }

    @PostMapping("/payments/cancel")
    public ResponseEntity<String> paymentComplete(@RequestBody Canceldata cancelData) throws IOException {
        String token = refundService.getToken(imp_key,imp_secret);
        try {
            refundService.refundRequest(token, cancelData.getMerchant_id(), cancelData.getReason());
            purchaseService.cancel(cancelData.getId(), cancelData.getPurchase_id(), cancelData.getStock());
            return ResponseEntity.ok().build();

        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }

}
