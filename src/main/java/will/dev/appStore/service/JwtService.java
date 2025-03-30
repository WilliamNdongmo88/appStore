package will.dev.appStore.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import will.dev.appStore.entites.Jwt;
import will.dev.appStore.entites.User;
import will.dev.appStore.repository.JwtRepository;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static will.dev.appStore.configuration.KeyGeneratorUtil.generateEncryptionKey;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class JwtService {
    public static final String BEARER = "Bearer";
    //private final String ENCRYPTION_KEY = "9710e6844f0bb2a4aa13608d1f207a15fb9f35c602582ac6ba3525daceba966d";
    private final String ENCRYPTION_KEY = generateEncryptionKey(32);
    private final UserService userService;
    private final JwtRepository jwtRepository;

    public Map<String, String> generate(String username) {
        User user = (User) this.userService.loadUserByUsername(username);
        System.out.println("User : " + user);
        this.desableTokens(user);
        Map<String, String> jwtMap = this.generateJwt(user);
        Jwt jwt = Jwt
                .builder()
                .valeur(jwtMap.get(BEARER))
                .user(user)
                .desactive(false)
                .expire(false)
                .build();
        this.jwtRepository.save(jwt);// Save token inside the table Jwt into the data base
        return jwtMap;
    }

    private void desableTokens(User user) {
        final List<Jwt> jwtList = this.jwtRepository.findUser(user.getEmail())
                .filter(jwt -> jwt.getUser() != null) // Évite les valeurs null
                .peek(jwt ->{
                            jwt.setDesactive(true);
                            jwt.setExpire(true);
                        }
                ).collect(Collectors.toList());
        this.jwtRepository.saveAll(jwtList);
    }

    private Map<String, String> generateJwt(User user) {
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + 30*60*1000;
        Map<String, String> claims = Map.of("nom", user.getUsername(),
                "email", user.getEmail()
        );

        String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .claims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of(BEARER, bearer);
    }

    private Key getKey(){
        byte[] decoded = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoded);
    }

    public String extractUsername(String token) {
        return this.getClaims(token, Claims::getSubject);
    }

    public Boolean isTokenExpred(String token) {
        try{
            Date expirationDate = this.getClaims(token, Claims::getExpiration);
            return expirationDate.before(new Date());
        }catch (RuntimeException e){
            throw new RuntimeException("Le token a expiré"+e.getMessage());
        }

    }

    private <T> T getClaims(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Jwt tokenByValue(String token) {
        return this.jwtRepository.findByValeur(token).orElseThrow(()-> new RuntimeException("Token inconnu"));
    }

    public void deconnexion() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jwt jwt = this.jwtRepository.findUserValidToken(user.getEmail(), false, false)
                .orElseThrow(()-> new RuntimeException("Token invalid ou inconnu"));
        jwt.setExpire(true);
        jwt.setDesactive(true);
        this.jwtRepository.save(jwt);
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void removeUselessTokens(){
        List<Jwt> tokens = this.jwtRepository.deleteAllByExpireAndDesactive(true, true);
        if (!tokens.isEmpty()){
            this.jwtRepository.deleteAll(tokens);
            log.info("{} tokens deleted", tokens.size());
        }else {
            log.info("No tokens to deleted for the moment");
        }
    }
}
