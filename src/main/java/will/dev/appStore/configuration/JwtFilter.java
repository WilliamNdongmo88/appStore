package will.dev.appStore.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import will.dev.appStore.entites.Jwt;
import will.dev.appStore.service.JwtService;
import will.dev.appStore.service.UserService;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;//Pour gerer l'exception sur le filtre
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = null;
        String username = null;
        Boolean isTokenExpred = true;
        Jwt tokenDansBd = null;
        try {
            //Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NDI2OTYyMDAsImV4cCI6MTc0MjY5ODAwMCwic3ViIjoiZm90c29uZG9uZ21vOEBnbWFpbC5jb20iLCJub20iOiJGb3RzbyBOZG9uZ21vIiwiZW1haWwiOiJmb3Rzb25kb25nbW84QGdtYWlsLmNvbSJ9.LfcWY1gDsACTUwld1wLmw6LICu6K54WnCku7-89VVn4
            String authorisation = request.getHeader("Authorization");
            //System.out.println("Authorization Header: " + authorisation);

            if (authorisation != null && authorisation.startsWith("Bearer ")){
                token = authorisation.substring(7);
                isTokenExpred = jwtService.isTokenExpred(token);
                tokenDansBd = this.jwtService.tokenByValue(token);
                username = jwtService.extractUsername(token);
                //System.out.println("Token extrait: " + token);
                //System.out.println("Username extrait: " + username);
                //System.out.println("Token expiré ? " + isTokenExpred);
            }
            if (!isTokenExpred
                    //&& username != null
                    && tokenDansBd.getUser().getEmail().equals(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (userDetails != null) {
                    System.out.println("Utilisateur authentifié : " + userDetails.getUsername());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    System.out.println("⚠️ Aucun utilisateur trouvé pour ce token !");
                }
            } else {
                System.out.println("⚠️ Problème d'authentification !");
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null,exception);
        }
    }
}
