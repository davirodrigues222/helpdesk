package com.helpdesk.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String status;       // Ex: ABERTO, EM_ANDAMENTO, FECHADO
    private String prioridade;   // Ex: BAIXA, MEDIA, ALTA
    private LocalDateTime dataAbertura = LocalDateTime.now();

    // RELACIONAMENTO: Muitos chamados pertencem a Um usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}