package br.com.ctw.api_monitoramento_transformadores.controller;

import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorDetalhadoResponseDTO;
import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorRequestDTO;
import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorResponseDTO;
import br.com.ctw.api_monitoramento_transformadores.service.TransformadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Tag(
        name = "Transformador",
        description = "Controlador que centraliza as funções de gerenciamento da entidade Transformador"
)

@RestController
@RequestMapping("/api/v1/transformadores")
@AllArgsConstructor
public class TransformadorController
{
    private TransformadorService service;

    @Operation(
            summary = "Cadastrar transformador",
            description = "Cadastra um novo transformador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Criação bem-sucedida de um recurso (POST)."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição com JSON malformado ou falhas nas validações de campo"
            )
    })

    /**
     * Controller de cadastrar um novo transformador
     * @param request requisição levada para service com dados internos para criação de Transformador
     * @return DTO {@link TransformadorResponseDTO} com a entidade Transformador persistida
     */
    @PostMapping
    public ResponseEntity<TransformadorResponseDTO> cadastrar(@RequestBody @Valid TransformadorRequestDTO request)
    {
        TransformadorResponseDTO response = service.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/id")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(
            summary = "Listar transformadores",
            description = "Lista todos os transformadores cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Retorno de consultas executadas com sucesso"
    )
    /**
     * Controller para listar todos os transformadores
     * @return Lista de DTOs {@link TransformadorResponseDTO} com a entidade Transformador persistida
     */
    @GetMapping
    public ResponseEntity<List<TransformadorResponseDTO>> listarTodos()
    {
        return ResponseEntity.ok(service.listarTudo());
    }


    @Operation(
            summary = "Buscar transformador pelo número de série",
            description = "Busca um transformador, suas leituras e técnicos pelo número de série"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Retorno de consultas executadas com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "O recurso solicitado não existe no banco de dados para o identificador ou parâmetro fornecido"
            )
    })
    /**
     * Controller de buscar transformador por número de série
     * @param numeroSerie parâmetro de busca de transformador
     * @return DTO {@link TransformadorDetalhadoResponseDTO} com a entidade Transformador persistida
     */
    @GetMapping("/{numeroSerie}")
    public ResponseEntity<TransformadorDetalhadoResponseDTO> buscarTransformador(@Parameter
    (description = "Número de série único de transformador", example = "TRF-2026-1001")
    @PathVariable String numeroSerie)
    {
        return ResponseEntity.ok(service.buscarTransformador(numeroSerie));
    }

    @Operation(
            summary = "Atualizar limites por número de série",
            description = "Atualiza os limites de temperatura do equipamento por número de série"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Atualizações executadas com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "O recurso solicitado não existe no banco de dados para o identificador ou parâmetro fornecido"
            )
    })
    /**
     * Controller de atualizar limites de transformador por número de série
     * @param numeroSerie parâmetro de busca de transformador
     * @param limiteTempOleo limite a ser atualizado
     * @param limiteTempEnrol limite a ser atualizado
     * @return DTO {@link TransformadorDetalhadoResponseDTO} com a entidade Transformador persistida
     */
    @PutMapping("/numeroSerie")
    public ResponseEntity<TransformadorResponseDTO> atualizarLimites(@Parameter
    (description = "Número de série único de transformador", example = "TRF-2026-1001")
    @PathVariable String numeroSerie,BigDecimal limiteTempOleo, BigDecimal limiteTempEnrol)
    {
        return ResponseEntity.ok(service.atualizarLimites(numeroSerie, limiteTempOleo, limiteTempEnrol));
    }

    @Operation(
            summary = "Deletar transformador pelo número de série",
            description = "Remove um transformador da base de dados pelo número de série"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Remoção bem-sucedida de um recurso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "O recurso solicitado não existe no banco de dados para o identificador ou parâmetro fornecido"
            )

    })
    /**
     * Controller de deletar transformador por número de série
     * @param numeroSerie parâmetro de busca de transformador
     */
    @DeleteMapping("/numeroSerie")
    public ResponseEntity<Void> deletar(@Parameter
    (description = "Número de série único de transformador", example = "TRF-2026-1001")
    @PathVariable String numeroSerie)
    {
        return ResponseEntity.noContent().build();
    }
}
