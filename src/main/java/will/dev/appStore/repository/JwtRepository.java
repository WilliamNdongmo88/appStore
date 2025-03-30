package will.dev.appStore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import will.dev.appStore.entites.Jwt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface JwtRepository extends CrudRepository<Jwt, Long> {
    Optional<Jwt> findByValeur(String valeur);

    @Query("SELECT j FROM Jwt j WHERE j.user.email=:email AND j.expire=:expire AND j.desactive=:desactive")
    Optional<Jwt> findUserValidToken(String email, boolean expire, boolean desactive);

    @Query("SELECT j FROM Jwt j WHERE j.user.email=:email")
    Stream<Jwt> findUser(String email);

    List<Jwt> deleteAllByExpireAndDesactive(boolean expire, boolean desactive);
}

