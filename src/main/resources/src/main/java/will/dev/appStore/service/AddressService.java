package will.dev.appStore.service;

import jakarta.persistence.EntityNotFoundException;
import will.dev.appStore.entites.Address;
import will.dev.appStore.entites.DeliveryMode;
import will.dev.appStore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Create
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // Read (Get All)
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Read (Get By Id)
    public Address getAddressById(Long id) {
        Optional<Address> optionaladdress =  this.addressRepository.findById(id);
        return optionaladdress.orElseThrow(() -> new EntityNotFoundException("Aucune Address n'existe avec cet identifiant"));
    }

    // Update
    public Address updateAddress(Long id, Address addressDetails) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        address.setUser(addressDetails.getUser());
        address.setFullName(addressDetails.getFullName());
        address.setPhone(addressDetails.getPhone());
        address.setAddressLine1(addressDetails.getAddressLine1());
        address.setAddressLine2(addressDetails.getAddressLine2());
        address.setPostCode(addressDetails.getPostCode());
        address.setTownCity(addressDetails.getTownCity());
        address.setStateRegion(addressDetails.getStateRegion());
        address.setCountry(addressDetails.getCountry());
        address.setDeliveryInstructions(addressDetails.getDeliveryInstructions());
        address.setDeletedAt(addressDetails.getDeletedAt());

        return addressRepository.save(address);
    }

    // Delete
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        addressRepository.delete(address);
    }
}