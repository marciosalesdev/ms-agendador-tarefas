package com.marciosalesdev.agendadortarefas.controller;

import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.business.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
