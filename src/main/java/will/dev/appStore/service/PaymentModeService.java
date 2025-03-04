package will.dev.appStore.service;

import will.dev.appStore.entites.PaymentMode;
import will.dev.appStore.repository.PaymentModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentModeService {

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    // Create
    public PaymentMode createPaymentMode(PaymentMode paymentMode) {
        return paymentModeRepository.save(paymentMode);
    }

    // Read (Get All)
    public List<PaymentMode> getAllPaymentModes() {
        return paymentModeRepository.findAll();
    }

    // Read (Get By Id)
    public Optional<PaymentMode> getPaymentModeById(Long id) {
        return paymentModeRepository.findById(id);
    }

    // Read (Get By Slug)
    public Optional<PaymentMode> getPaymentModeBySlug(String slug) {
        return paymentModeRepository.findBySlug(slug);
    }

    // Update
    public PaymentMode updatePaymentMode(Long id, PaymentMode paymentModeDetails) {
        PaymentMode paymentMode = paymentModeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentMode not found with id " + id));

        paymentMode.setTitle(paymentModeDetails.getTitle());
        paymentMode.setSlug(paymentModeDetails.getSlug());
        paymentMode.setDescription(paymentModeDetails.getDescription());
        paymentMode.setDeletedAt(paymentModeDetails.getDeletedAt());
        paymentMode.setDeletedBy(paymentModeDetails.getDeletedBy());

        return paymentModeRepository.save(paymentMode);
    }

    // Delete
    public void deletePaymentMode(Long id) {
        PaymentMode paymentMode = paymentModeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentMode not found with id " + id));

        paymentModeRepository.delete(paymentMode);
    }
}