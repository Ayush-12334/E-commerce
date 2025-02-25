// package com.example.app.practice.Payment;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.razorpay.RazorpayException;
// @RestController
// @RequestMapping("/api/payment")
// public class PaymentController {
// @Autowired

// private RazorPayService razorPayService;

// @PostMapping("/createOrder")
// public String createOrder(@RequestParam int amount, @RequestParam String currency, @RequestParam String receipt) throws RazorpayException {
//     try{
//     return razorPayService.createOrder(amount, currency, "recipttent ");
//     }catch(RazorpayException e){
//         return e.getMessage();
//     }   
    

// }


// }
package com.example.app.practice.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/pay/payment")
public class PaymentController {

    @Autowired
    private RazorPayService razorPayService;

    // @PostMapping("/createOrder")
    // public ResponseEntity<?> createOrder(@RequestBody PaymentRequest paymentRequest) {
    //     try {
    //         String order = razorPayService.createOrder(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getReceipt());
    //         return new ResponseEntity<>(order, HttpStatus.CREATED);
    //     } catch (RazorpayException e) {
    //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }
    // }
    @PostMapping("/createOrder")
public ResponseEntity<?> createOrder(@RequestBody PaymentRequest paymentRequest) {
    try {
        String order = razorPayService.createOrder(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getReceipt());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    } catch (RazorpayException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

}