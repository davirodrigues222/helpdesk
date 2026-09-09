package com.helpdesk.api.controller;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.repository.ChamadoRepository;
import com.helpdesk.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. LISTAR TODOS OS CHAMADOS
    @GetMapping
    public List<Chamado> listarTodos() {
        return chamadoRepository.findAll();
    }

    // 2. BUSCAR CHAMADO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id) {
        return chamadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. BUSCA INTELIGENTE: Listar chamados por ID do usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Chamado>> listarPorUsuario(@PathVariable Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chamadoRepository.findByUsuarioId(usuarioId));
    }

    // 4. CRIAR CHAMADO (Com trava de segurança para validar se usuário existe)
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Chamado chamado) {
        if (chamado.getUsuario() == null || chamado.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().body("Informe o ID do usuário.");
        }

        boolean usuarioExiste = usuarioRepository.existsById(chamado.getUsuario().getId());
        if (!usuarioExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        Chamado novoChamado = chamadoRepository.save(chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamado);
    }

    // 5. EDITAR CHAMADO
    @PutMapping("/{id}")
    public ResponseEntity<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado chamadoAtualizado) {
        return chamadoRepository.findById(id).map(chamado -> {
            chamado.setTitulo(chamadoAtualizado.getTitulo());
            chamado.setDescricao(chamadoAtualizado.getDescricao());
            chamado.setStatus(chamadoAtualizado.getStatus());
            chamado.setPrioridade(chamadoAtualizado.getPrioridade());
            Chamado salvo = chamadoRepository.save(chamado);
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 6. DELETAR CHAMADO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!chamadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        chamadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}