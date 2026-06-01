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
import java.util.List;

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

        return tarefasConverter.converterParaTarefasDTO(
                tarefasRepository.save(tarefasEntity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataEventoInicial, LocalDateTime dataEventoFinal) {
        return tarefasConverter.converterParaTarefas(tarefasRepository.findByDataEventoBetween(dataEventoInicial, dataEventoFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefasConverter.converterParaTarefas(tarefasRepository.findByEmailUsuario(email));
    }
}
