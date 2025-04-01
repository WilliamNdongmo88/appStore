package will.dev.appStore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.Validation;
import will.dev.appStore.entites.User;
import will.dev.appStore.repository.ValidationRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationRepository validationRepository;
    private final NotificationService notificationService;

    public void enregistrer(User user){
        Optional<Validation> validationDansBd = this.validationRepository.findByUser(user);
        if (validationDansBd.isPresent()){
            this.updateValidation(validationDansBd);
        }else {
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
    }

    private void updateValidation(Optional<Validation> validationDansBd) {
        validationDansBd.get().setUser(validationDansBd.get().getUser());
        Instant creation = Instant.now();
        validationDansBd.get().setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validationDansBd.get().setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validationDansBd.get().setCode(code);

        this.validationRepository.save(validationDansBd.get());
        this.notificationService.envoyer(validationDansBd.get());
    }

    public Validation lireCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(()-> new RuntimeException("Votre code est invalide"));
    }
}
