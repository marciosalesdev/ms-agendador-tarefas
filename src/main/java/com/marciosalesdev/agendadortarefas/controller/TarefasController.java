package com.marciosalesdev.agendadortarefas.controller;

import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.business.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;


    public ResponseEntity<TarefasDTO> gravarTarefa(@RequestBody TarefasDTO tarefaDTO,
                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefas(tarefaDTO, token));
    }
}
