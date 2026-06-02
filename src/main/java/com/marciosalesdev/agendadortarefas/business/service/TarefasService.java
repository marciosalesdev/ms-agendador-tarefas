package com.marciosalesdev.agendadortarefas.business.service;

import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.marciosalesdev.agendadortarefas.business.mapper.TarefasConverter;
import com.marciosalesdev.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.marciosalesdev.agendadortarefas.infrastructure.enums.StatusNorificacaoEnum;
import com.marciosalesdev.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
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
    private final TarefaUpdateConverter tarefaUpdateConverter;
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

    public void deleteById(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por ID, Tarefa inexistente", ex.getCause());
        }
    }

    public TarefasDTO atualizarStatusTarefa(StatusNorificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada " + id));
            entity.setStatusNorificacaoEnum(status);
            return tarefasConverter.converterParaTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + ex.getMessage(), ex.getCause());
        }
    }

    public TarefasDTO updateTarefa(TarefasDTO tarefasDTO, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada" + id));
            tarefaUpdateConverter.updateDeTarefas(tarefasDTO, entity);
            return tarefasConverter.converterParaTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Tarefa nao encontrada" + id, ex.getCause());
        }
    }
}
