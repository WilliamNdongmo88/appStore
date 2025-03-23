package will.dev.appStore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.controller.Validation;
import will.dev.appStore.entites.User;
import will.dev.appStore.repository.ValidationRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationRepository validationRepository;
    private final NotificationService notificationService;

    public void enregistrer(User user){
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);

        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);
    }

    public Validation lireCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(()-> new RuntimeException("Votre code est invalide"));
    }
}
