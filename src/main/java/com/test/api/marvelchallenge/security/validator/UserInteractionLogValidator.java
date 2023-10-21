package com.test.api.marvelchallenge.security.validator;

import com.test.api.marvelchallenge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * La clase `UserInteractionLogValidator` proporciona validaciones personalizadas para registros de interacción de usuario.
 */
@Component("interactionLogValidator")
public class UserInteractionLogValidator {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Valida si el nombre de usuario proporcionado coincide con el nombre de usuario del usuario actualmente autenticado.
     *
     * @param username El nombre de usuario que se va a validar.
     * @return `true` si el nombre de usuario coincide con el usuario autenticado actualmente, de lo contrario, `false`.
     */
    public boolean validate(String username){
        String userLoggedIn = authenticationService.getUserLoggedIn().getUsername();
        return userLoggedIn.equals(username);
    }
}

