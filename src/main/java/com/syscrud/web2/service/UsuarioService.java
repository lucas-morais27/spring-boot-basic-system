//package com.syscrud.web2.service;
//
//import com.syscrud.web2.model.UsuarioEntity;
//import com.syscrud.web2.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import static com.syscrud.web2.enuns.UserRole.ADMIN;
//
//@Service
//public class UsuarioService implements CommandLineRunner {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    public void criarPrimeiroUsuario() {
//        UsuarioEntity usuario = new UsuarioEntity("admin1", passwordEncoder.encode("123456789"), ADMIN);
//        usuarioRepository.save(usuario);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        criarPrimeiroUsuario();
//    }
//}
