package com.test.api.marvelchallenge.security;

import com.test.api.marvelchallenge.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * La clase `SecurityBeansInjector` configura los beans relacionados con la seguridad de la aplicación, como el `AuthenticationManager`,
 * el `AuthenticationProvider`, el `PasswordEncoder`, y el `UserDetailsService`.
 */
@Configuration
public class SecurityBeansInjector {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private UserRepository userRepository;

    /**
     * Configura el `AuthenticationManager` que se utilizará para autenticar a los usuarios.
     *
     * @return El `AuthenticationManager` configurado.
     * @throws Exception Si se produce una excepción durante la configuración.
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configura el `AuthenticationProvider` que se encargará de la autenticación de los usuarios.
     *
     * @return El `AuthenticationProvider` configurado.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Configura el `PasswordEncoder` que se utilizará para cifrar y verificar contraseñas.
     *
     * @return El `PasswordEncoder` configurado.
     */
    @Bean
    @Primary
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el `UserDetailsService` que se utilizará para cargar detalles de usuario y autenticar a los usuarios.
     *
     * @return El `UserDetailsService` configurado.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            return userRepository.findByUsername(username)
                    .orElseThrow( () -> new UsernameNotFoundException("Username not found [" + username + "]"));
        };
    }

    // Otros métodos y configuraciones de seguridad pueden ir aquí.

}

