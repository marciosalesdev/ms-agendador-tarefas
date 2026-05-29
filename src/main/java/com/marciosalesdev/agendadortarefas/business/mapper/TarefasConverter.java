package com.marciosalesdev.agendadortarefas.business.mapper;


import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity converterParaTarefas(TarefasDTO tarefasDTO);

    TarefasDTO converterParaTarefasDTO(TarefasEntity tarefaEntity);
}