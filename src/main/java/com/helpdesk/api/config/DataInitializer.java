package com.helpdesk.api.config;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.repository.ChamadoRepository;
import com.helpdesk.api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;
    private final ChamadoRepository chamadoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            
            Usuario u1 = new Usuario();
            u1.setNome("Carlos Silva");
            u1.setEmail("carlos@empresa.com");
            u1.setTelefone("85999990001");
            u1.setDepartamento("TI");
            u1.setCargo("Analista");
            u1 = usuarioRepository.save(u1);

            Usuario u2 = new Usuario();
            u2.setNome("Mariana Souza");
            u2.setEmail("mariana@empresa.com");
            u2.setTelefone("85999990002");
            u2.setDepartamento("RH");
            u2.setCargo("Gerente");
            u2 = usuarioRepository.save(u2);

            Usuario u3 = new Usuario();
            u3.setNome("Roberto Alves");
            u3.setEmail("roberto@empresa.com");
            u3.setTelefone("85999990003");
            u3.setDepartamento("Financeiro");
            u3.setCargo("Assistente");
            u3 = usuarioRepository.save(u3);

            Chamado c1 = new Chamado();
            c1.setTitulo("Erro no Monitor");
            c1.setDescricao("O monitor não liga na tomada do RH.");
            c1.setStatus("ABERTO");
            c1.setPrioridade("ALTA");
            c1.setUsuario(u2);
            chamadoRepository.save(c1);

            Chamado c2 = new Chamado();
            c2.setTitulo("Troca de Senha");
            c2.setDescricao("Esqueci a senha do e-mail corporativo.");
            c2.setStatus("EM_ANDAMENTO");
            c2.setPrioridade("MEDIA");
            c2.setUsuario(u3);
            chamadoRepository.save(c2);

            Chamado c3 = new Chamado();
            c3.setTitulo("Instalar Impressora");
            c3.setDescricao("Configurar impressora de rede no setor do TI.");
            c3.setStatus("CONCLUIDO");
            c3.setPrioridade("BAIXA");
            c3.setUsuario(u1);
            chamadoRepository.save(c3);

            System.out.println("Carga inicial de dados concluída com sucesso!");
        }
    }
}