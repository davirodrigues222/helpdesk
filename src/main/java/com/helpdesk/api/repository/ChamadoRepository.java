package com.helpdesk.api.repository;

import com.helpdesk.api.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByUsuarioId(Long usuarioId);
}