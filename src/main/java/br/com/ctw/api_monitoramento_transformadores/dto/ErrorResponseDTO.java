package br.com.ctw.api_monitoramento_transformadores.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Resposta dada pelo sistema em caso de erro
 * @param timestamp Horário exato em que o erro foi lançado
 * @param status Status HTTP do erro encontrado
 * @param erro Descrição simples do erro
 * @param mensagem Descrição detalhada do erro
 * @param caminho Caminho que originou o erro
 */

@Schema(description = "Resposta dada pelo sistema em caso de erro")
public record ErrorResponseDTO(
        @Schema(
                description = "Horário exato em que o erro foi lançado",
                example = "2007-12-03T10:15:30"
        )
        LocalDateTime timestamp,

        @Schema(
                description = "Status HTTP do erro encontrado",
                example = "404"
        )
        Integer status,

        @Schema(
                description = "Descrição simples do erro",
                example = "Recurso não encontrado"
        )
        String erro,

        @Schema(
                description = "Descrição detalhada do erro",
                example = "Recurso não encontrado pelo ID informado"
        )
        String mensagem,

        @Schema(
                description = "Caminho que originou o erro",
                example = "/api/v1/transformadores/1"
        )
        String caminho
) {
    public ErrorResponseDTO(@Schema(
            description = "Horário exato em que o erro foi lançado",
            example = "2007-12-03T10:15:30"
    )
                            LocalDateTime timestamp, @Schema(
            description = "Status HTTP do erro encontrado",
            example = "404"
    )
                            Integer status, @Schema(
            description = "Descrição simples do erro",
            example = "Recurso não encontrado"
    )
                            String erro, @Schema(
            description = "Descrição detalhada do erro",
            example = "Recurso não encontrado pelo ID informado"
    )
                            String mensagem, @Schema(
            description = "Caminho que originou o erro",
            example = "/api/v1/transformadores/1"
    )
                            String caminho) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }
}
