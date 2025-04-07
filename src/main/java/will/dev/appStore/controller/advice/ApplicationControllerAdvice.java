package will.dev.appStore.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import will.dev.appStore.dto.ErrorEntity;
import org.springframework.security.access.AccessDeniedException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    /**
     * 400 - Requête invalide (ex : validation)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ErrorEntity handleBadRequest(Exception ex) {
        return new ErrorEntity(HttpStatus.BAD_REQUEST.value(), "Requête invalide : " + ex.getMessage());
    }

    /**
     * Erreur 401 - Non autorisé (authentification échouée)
     */
    /*@ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = SignatureException.class)
    public ProblemDetail signatureException(SignatureException exception){
        return ProblemDetail.forStatusAndDetail(UNAUTHORIZED, "Token invalid.");
    }*/

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public @ResponseBody ErrorEntity handleUnauthorized(BadCredentialsException exception) {
        return new ErrorEntity(UNAUTHORIZED.value(), "Authentification échouée : " + exception.getMessage());
    }

    /**
     * Erreur 403 - Accès refusé
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody ErrorEntity handleAccessDenied(AccessDeniedException exception) {
        return new ErrorEntity(HttpStatus.FORBIDDEN.value(), exception.getMessage() + ": Vous n'êtez pas autorisé a mener cette action.");
    }

    /**
     * Erreur 404 - Ressource non trouvée
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorEntity handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ErrorEntity(HttpStatus.NOT_FOUND.value(), "Ressource non trouvée : " + exception.getMessage());
    }

    /**
     * Erreur 500 - Erreur interne du serveur
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorEntity handleGenericException(Exception exception) {
        return new ErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erreur interne : " + exception.getMessage());
    }

}
