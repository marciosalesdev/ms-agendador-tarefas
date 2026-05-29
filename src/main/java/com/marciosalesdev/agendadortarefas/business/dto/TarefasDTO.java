package com.marciosalesdev.agendadortarefas.business.dto;

import com.marciosalesdev.agendadortarefas.infrastructure.enums.StatusNorificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarefasDTO {

    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNorificacaoEnum statusNorificacaoEnum;

}
