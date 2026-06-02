package com.marciosalesdev.agendadortarefas.business.mapper;


import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id",target = "id")
    TarefasEntity converterParaTarefas(TarefasDTO tarefasDTO);

    TarefasDTO converterParaTarefasDTO(TarefasEntity tarefaEntity);

    List<TarefasEntity> converterParaTarefasDto(List<TarefasDTO> tarefasDTO);

    List<TarefasDTO> converterParaTarefas(List<TarefasEntity> tarefas);
}