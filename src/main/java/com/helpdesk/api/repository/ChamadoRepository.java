package com.helpdesk.api.repository;

import com.helpdesk.api.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    
    // Busca Inteligente: O Spring entende o nome do método e gera
    // o SQL para buscar chamados filtrando pelo ID do usuário automaticamente!
    List<Chamado> findByUsuarioId(Long usuarioId);
}