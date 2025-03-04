package will.dev.appStore.controller;

import will.dev.appStore.entites.Address;
import will.dev.appStore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Create
    @PostMapping("creer")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    // Read (Get All)
    @GetMapping("all_addresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Read (Get By Id)
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        return ResponseEntity.ok(addressService.updateAddress(id, addressDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}