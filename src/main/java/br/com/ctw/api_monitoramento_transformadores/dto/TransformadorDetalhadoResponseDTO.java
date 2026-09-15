package br.com.ctw.api_monitoramento_transformadores.dto;

import br.com.ctw.api_monitoramento_transformadores.entity.LeituraTermicaEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.TecnicoEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

/**
 * Resposta retornada do banco de dados ao sistema que representa uma entidade Transformador detalhada pelas regras de negócio
 * @param id Identificador único de Transformador
 * @param numeroSerie Número de série do equipamento
 * @param leituras Coleção do histórico de medições recebidas pelo equipamento
 * @param tecnicos Coleção de profissionais responsáveis
 */

@Schema(description = "Resposta retornada do banco de dados ao sistema que representa uma entidade Transformador detalhada pelas regras de negócio")
public record TransformadorDetalhadoResponseDTO(
        @Schema(
                description = "Identificador único de Transformador",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Número de série do equipamento",
                example = "TRF-2026-1001"
        )
        String numeroSerie,

        @Schema(
                description = "Coleção do histórico de medições recebidas pelo equipamento",
                example = "Lista de JSONs contendo a entidade LeituraTermica"
        )
        Set<LeituraTermicaEntity> leituras,

        @Schema(
                description = "Coleção de profissionais responsáveis pelo ID",
                example = "Lista de JSONs contendo a entidade Tecnico"
        )
        Set<TecnicoEntity> tecnicos
) {
}
