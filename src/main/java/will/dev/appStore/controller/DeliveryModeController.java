package will.dev.appStore.controller;

import will.dev.appStore.entites.DeliveryMode;
import will.dev.appStore.service.DeliveryModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery-modes")
public class DeliveryModeController {

    @Autowired
    private DeliveryModeService deliveryModeService;

    // Create
    @PostMapping("creer")
    public DeliveryMode createDeliveryMode(@RequestBody DeliveryMode deliveryMode) {
        return deliveryModeService.createDeliveryMode(deliveryMode);
    }

    // Read (Get All)
    @GetMapping("all_delivery-modes")
    public List<DeliveryMode> getAllDeliveryModes() {
        return deliveryModeService.getAllDeliveryModes();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public DeliveryMode getDeliveryModeById(@PathVariable Long id) {
        return deliveryModeService.getDeliveryModeById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryMode> updateDeliveryMode(@PathVariable Long id, @RequestBody DeliveryMode deliveryModeDetails) {
        return ResponseEntity.ok(deliveryModeService.updateDeliveryMode(id, deliveryModeDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryMode(@PathVariable Long id) {
        deliveryModeService.deleteDeliveryMode(id);
        return ResponseEntity.noContent().build();
    }
}