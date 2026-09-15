package br.com.ctw.api_monitoramento_transformadores.dto;

import br.com.ctw.api_monitoramento_transformadores.entity.AlertaTermicoEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.LeituraTermicaEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.TecnicoEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Resposta retorada do banco de dados ao sistema que representa uma entidade Transformador persistida
 * @param id Identificador único de Transformador
 * @param numeroSerie Número de série do equipamento
 * @param modelo Modelo do equipamento
 * @param subestacao Subestação de localização
 * @param potenciaKva Potência nominal em kVA
 * @param limiteTempOleo Limite máximo suportado para a temperatura do óleo em °C
 * @param limiteTempEnrol Limite máximo suportado para a temperatura do enrolamento em °C
 * @param leituras Coleção do histórico de medições recebidas pelo equipamento
 * @param alertas Coleção do histórico de anomalias disparadas pelo equipamento
 * @param tecnicos Coleção de profissionais responsáveis
 */

@Schema(description = "Resposta retornada do banco de dados ao sistema que representa uma entidade Transformador persistida")
public record TransformadorResponseDTO(
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
                description = "Modelo do equipamento",
                example = "Transformador Trifásico de Força 15MVA"
        )
        String modelo,

        @Schema(
                description = "Subestação de localização",
                example = "Subestação Central - Setor A"
        )
        String subestacao,

        @Schema(
                description = "Potência nominal em kVA",
                example = " 15000.00"
        )
        BigDecimal potenciaKva,

        @Schema(
                description = "Limite máximo suportado para a temperatura do óleo em °C",
                example = "85.00"
        )
        BigDecimal limiteTempOleo,

        @Schema(
                description = "Limite máximo suportado para a temperatura do enrolamento em °C",
                example = "105.00"
        )
        BigDecimal limiteTempEnrol,

        @Schema(
                description = "Coleção do histórico de medições recebidas pelo equipamento",
                example = "Lista de JSONs contendo a entidade LeituraTermica"
        )
        Set<LeituraTermicaEntity> leituras,

        @Schema(
                description = "Coleção do histórico de anomalias disparadas pelo equipamento",
                example = "Lista de JSONs contendo a entidade AlertaTermico"
        )
        Set<AlertaTermicoEntity> alertas,

        @Schema(
                description = "Coleção de profissionais responsáveis pelo ID",
                example = "Lista de JSONs contendo a entidade Tecnico"
        )
        Set<TecnicoEntity> tecnicos
) {
}
