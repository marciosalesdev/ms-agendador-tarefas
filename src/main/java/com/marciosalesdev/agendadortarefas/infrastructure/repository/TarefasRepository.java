package com.marciosalesdev.agendadortarefas.infrastructure.repository;

import com.marciosalesdev.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataEventoInicial, LocalDateTime dataEventoFinal);

    List<TarefasEntity> findByEmailUsuario(String email);
}
