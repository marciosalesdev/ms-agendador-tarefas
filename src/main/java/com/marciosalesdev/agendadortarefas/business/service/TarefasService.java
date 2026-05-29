package com.marciosalesdev.agendadortarefas.business.service;

import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.business.mapper.TarefasConverter;
import com.marciosalesdev.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.marciosalesdev.agendadortarefas.infrastructure.enums.StatusNorificacaoEnum;
import com.marciosalesdev.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.marciosalesdev.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO gravarTarefas(TarefasDTO tarefasDTO, String token) {
        tarefasDTO.setEmailUsuario(jwtUtil.extractUsername(token.substring(7)));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNorificacaoEnum(StatusNorificacaoEnum.PENDENTE);
        TarefasEntity tarefasEntity = tarefasConverter.converterParaTarefas(tarefasDTO);
        return tarefasConverter.converterParaTarefasDTO(tarefasRepository.save(tarefasEntity));
    }
}
