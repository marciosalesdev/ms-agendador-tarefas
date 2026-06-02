package com.marciosalesdev.agendadortarefas.business.mapper;

import com.marciosalesdev.agendadortarefas.business.dto.TarefasDTO;
import com.marciosalesdev.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateDeTarefas(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefaEntity);
}
