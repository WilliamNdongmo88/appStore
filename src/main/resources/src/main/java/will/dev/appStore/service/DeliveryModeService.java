package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.DeliveryMode;
import will.dev.appStore.entites.Order;
import will.dev.appStore.repository.DeliveryModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryModeService {

    @Autowired
    private DeliveryModeRepository deliveryModeRepository;

    // Create
    public DeliveryMode createDeliveryMode(DeliveryMode deliveryMode) {
        return deliveryModeRepository.save(deliveryMode);
    }

    // Read (Get All)
    public List<DeliveryMode> getAllDeliveryModes() {
        return deliveryModeRepository.findAll();
    }

    // Read (Get By Id)
    public DeliveryMode getDeliveryModeById(Long id) {
        Optional<DeliveryMode> optionaldeliveryMode =  this.deliveryModeRepository.findById(id);
        return optionaldeliveryMode.orElseThrow(() -> new EntityNotFoundException("Aucun Delivery Mode n'existe avec cet identifiant"));
    }

    // Update
    public DeliveryMode updateDeliveryMode(Long id, DeliveryMode deliveryModeDetails) {
        DeliveryMode deliveryMode = deliveryModeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryMode not found with id " + id));

        deliveryMode.setTitle(deliveryModeDetails.getTitle());
        deliveryMode.setDescription(deliveryModeDetails.getDescription());
        deliveryMode.setPrice(deliveryModeDetails.getPrice());
        deliveryMode.setUnitOfMeasurement(deliveryModeDetails.getUnitOfMeasurement());
        deliveryMode.setMethod(deliveryModeDetails.getMethod());
        deliveryMode.setTimeframe(deliveryModeDetails.getTimeframe());
        deliveryMode.setDeletedAt(deliveryModeDetails.getDeletedAt());
        deliveryMode.setDeletedBy(deliveryModeDetails.getDeletedBy());

        return deliveryModeRepository.save(deliveryMode);
    }

    // Delete
    public void deleteDeliveryMode(Long id) {
        DeliveryMode deliveryMode = deliveryModeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryMode not found with id " + id));

        deliveryModeRepository.delete(deliveryMode);
    }
}