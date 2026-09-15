package br.com.ctw.api_monitoramento_transformadores.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Requisição levada do sistema ao banco de dados que representa a entidade Transformador persistida
 * @param numeroSerie Número de série do equipamento
 * @param modelo Modelo do equipamento
 * @param subestacao Subestação de localização
 * @param potenciaKva Potência nominal em kVA
 * @param limiteTempOleo Limite máximo suportado para a temperatura do óleo em °C
 * @param limiteTempEnrol Limite máximo suportado para a temperatura do enrolamento em °C
 */

@Schema(description = "Requisição levada do sistema ao banco de dados que representa a entidade Transformador persistida")
public record TransformadorRequestDTO(
        @Schema(
                description = "Número de série do equipamento",
                example = "TRF-2026-1001"
        )
        @NotNull(message = "O número de série é obrigatório")
        String numeroSerie,

        @Schema(
                description = "Modelo do equipamento",
                example = "Transformador Trifásico de Força 15MVA"
        )
        @NotNull(message = "O modelo do equipamento é obrigatório")
        @Size(max = 100, message = "O número máximo de caracteres de modelo é 100")
        String modelo,

        @Schema(
                description = "Subestação de localização",
                example = "Subestação Central - Setor A"
        )
        @NotNull(message = "A subestação é obrigatória")
        @Size(max = 100, message = "O número máximo de caracteres de subestação é 100")
        String subestacao,

        @Schema(
                description = "Potência nominal em kVA",
                example = " 15000.00"
        )
        @Digits(integer = 8, fraction = 2, message = "O valor de Potência kVA deve ter 8 digitos inteiros e até 2 decimais")
        @NotNull(message = "A potência em kVA é obrigatória")
        BigDecimal potenciaKva,

        @Schema(
                description = "Limite máximo suportado para a temperatura do óleo em °C",
                example = "85.00"
        )
        @Digits(integer = 3, fraction = 2, message = "O valor do limite de temperatura do óleo deve ter 5 digitos inteiros e até 2 decimais")
        @NotNull(message = "O limite de temperatura do óleo é obrigatório")
        BigDecimal limiteTempOleo,

        @Schema(
                description = "Limite máximo suportado para a temperatura do enrolamento em °C",
                example = "105.00"
        )
        @Digits(integer = 3, fraction = 2, message = "O valor do limite de temperatura do enrolamento deve ter 5 digitos inteiros e até 2 decimais")
        @NotNull(message = "O limite de temperatura do enrolamento é obrigatório")
        BigDecimal limiteTempEnrol
) {
}
