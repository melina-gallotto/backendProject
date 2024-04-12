package com.example.ClinicaProyecto.security;

import com.example.ClinicaProyecto.entity.AppUsuario;
import com.example.ClinicaProyecto.entity.AppUsuarioRole;
import com.example.ClinicaProyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    private final UserService userService;

    @Autowired
    public CargadoraDeDatos(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = "p";
        String passCifrada = passwordEncoder.encode(pass);
        userService.guardarUsuario(new AppUsuario("Diego", "Rueda", "diego@gmail.com", passCifrada, AppUsuarioRole.ROLE_USER));
        userService.guardarUsuario(new AppUsuario("Agustin", "Rueda", "agustin@gmail.com", passCifrada, AppUsuarioRole.ROLE_ADMIN));
    }
}
