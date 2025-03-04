package will.dev.appStore.controller;

import will.dev.appStore.entites.PaymentMode;
import will.dev.appStore.service.PaymentModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment-modes")
public class PaymentModeController {

    @Autowired
    private PaymentModeService paymentModeService;

    // Create
    @PostMapping("creer")
    public PaymentMode createPaymentMode(@RequestBody PaymentMode paymentMode) {
        return paymentModeService.createPaymentMode(paymentMode);
    }

    // Read (Get All)
    @GetMapping("all_payment-modes")
    public List<PaymentMode> getAllPaymentModes() {
        return paymentModeService.getAllPaymentModes();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMode> getPaymentModeById(@PathVariable Long id) {
        return paymentModeService.getPaymentModeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Read (Get By Slug)
    @GetMapping("/slug/{slug}")
    public ResponseEntity<PaymentMode> getPaymentModeBySlug(@PathVariable String slug) {
        return paymentModeService.getPaymentModeBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMode> updatePaymentMode(@PathVariable Long id, @RequestBody PaymentMode paymentModeDetails) {
        return ResponseEntity.ok(paymentModeService.updatePaymentMode(id, paymentModeDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMode(@PathVariable Long id) {
        paymentModeService.deletePaymentMode(id);
        return ResponseEntity.noContent().build();
    }
}