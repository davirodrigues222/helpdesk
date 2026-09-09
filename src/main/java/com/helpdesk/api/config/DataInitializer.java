package com.helpdesk.api.config;

import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            
            Usuario u1 = new Usuario(); 
            u1.setNome("Ana Silva"); u1.setEmail("ana@email.com"); u1.setTelefone("1111-1111"); 
            u1.setDepartamento("Vendas"); u1.setCargo("Vendedora"); u1.setDataCadastro(LocalDate.now());
            
            Usuario u2 = new Usuario(); 
            u2.setNome("Carlos Souza"); u2.setEmail("carlos@email.com"); u2.setTelefone("2222-2222"); 
            u2.setDepartamento("Marketing"); u2.setCargo("Analista"); u2.setDataCadastro(LocalDate.now());
            
            Usuario u3 = new Usuario(); 
            u3.setNome("João Mendes"); u3.setEmail("joao@email.com"); u3.setTelefone("3333-3333"); 
            u3.setDepartamento("RH"); u3.setCargo("Gerente"); u3.setDataCadastro(LocalDate.now());
            
            usuarioRepository.saveAll(List.of(u1, u2, u3));
            
            // O Davi vai inserir a carga inicial dos 3 chamados logo abaixo desta linha
        }
    }
}