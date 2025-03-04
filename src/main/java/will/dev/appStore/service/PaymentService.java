package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.DeliveryMode;
import will.dev.appStore.entites.Payment;
import will.dev.appStore.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Create
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Read (Get All)
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Read (Get By Id)
    public Payment getPaymentById(Long id) {
        Optional<Payment> optionaldeliveryMode =  this.paymentRepository.findById(id);
        return optionaldeliveryMode.orElseThrow(() -> new EntityNotFoundException("Aucun Payment n'existe avec cet identifiant"));
    }

    // Update
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));

        payment.setUser(paymentDetails.getUser());
        payment.setOrder(paymentDetails.getOrder());
        payment.setAmount(paymentDetails.getAmount());
        payment.setDeletedAt(paymentDetails.getDeletedAt());
        payment.setDeletedBy(paymentDetails.getDeletedBy());

        return paymentRepository.save(payment);
    }

    // Delete
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));

        paymentRepository.delete(payment);
    }
}