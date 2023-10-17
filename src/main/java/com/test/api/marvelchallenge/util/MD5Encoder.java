package com.test.api.marvelchallenge.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * Implementación personalizada de un codificador de contraseñas que utiliza el algoritmo MD5 para cifrar y verificar contraseñas.
 */
@Component("md5Encoder")
public class MD5Encoder implements PasswordEncoder {

    /**
     * Codifica la contraseña utilizando el algoritmo MD5 y devuelve el resultado como una cadena hexadecimal.
     *
     * @param rawPassword La contraseña sin cifrar.
     * @return La contraseña cifrada en formato hexadecimal.
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    /**
     * Compara una contraseña sin cifrar con una contraseña cifrada para verificar si son iguales.
     *
     * @param rawPassword     La contraseña sin cifrar.
     * @param encodedPassword La contraseña cifrada a comparar.
     * @return true si la contraseña sin cifrar coincide con la contraseña cifrada; de lo contrario, false.
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }
}

