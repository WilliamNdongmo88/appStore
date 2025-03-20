package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import will.dev.appStore.dto.PaymentDTO;
import will.dev.appStore.entites.DeliveryMode;
import will.dev.appStore.entites.Payment;
import will.dev.appStore.mapper.PaymentDtoMapper;
import will.dev.appStore.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentDtoMapper paymentDtoMapper;

    // Create
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Read (Get All)
    public Stream<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(paymentDtoMapper);
    }

    // Read (Get By Id)
    public PaymentDTO getPaymentById(Long id) {
        return paymentRepository.findById(id).map(paymentDtoMapper).orElseThrow(() -> new EntityNotFoundException("Aucun Payment n'existe avec cet identifiant"));
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