package com.marciosalesdev.agendadortarefas.controller;

import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.business.service.TarefasService;
import com.marciosalesdev.agendadortarefas.infrastructure.enums.StatusNorificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;


    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefa(@RequestBody TarefasDTO tarefaDTO,
                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.gravarTarefas(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPediodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoInicial,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoFinal) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataEventoInicial, dataEventoFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> listaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTarefaPorId(@RequestParam String id) {
        tarefasService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status") StatusNorificacaoEnum status,
                                                              @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.atualizarStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestBody TarefasDTO tarefaDTO,
                                                   @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.updateTarefa(tarefaDTO, id));
    }
}
