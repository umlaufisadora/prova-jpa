package br.com.ctw.api_monitoramento_transformadores.service;

import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorDetalhadoResponseDTO;
import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorRequestDTO;
import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorResponseDTO;
import br.com.ctw.api_monitoramento_transformadores.entity.AlertaTermicoEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.LeituraTermicaEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.TecnicoEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.TransformadorEntity;
import br.com.ctw.api_monitoramento_transformadores.exception.NotFoundException;
import br.com.ctw.api_monitoramento_transformadores.exception.TransformadorJaExiste;
import br.com.ctw.api_monitoramento_transformadores.mapper.TransformadorMapper;
import br.com.ctw.api_monitoramento_transformadores.repository.TransformadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service de Transformador que reune todas as regras de negócio da entidade Transformador
 */
@Service
@AllArgsConstructor
public class TransformadorService
{
    private final TransformadorMapper mapper;
    private final TransformadorRepository repository;

    /**
     * Service de cadastrar um novo transformador
     * @param request requisição levada ao banco de dados para criação de entidade
     * @return DTO {@link TransformadorResponseDTO} com a entidade Transformador persistida
     * @throws TransformadorJaExiste É lançado quando existe um número de série coincidente no banco de dados
     */
    @Transactional
    public TransformadorResponseDTO cadastrar(TransformadorRequestDTO request)
    {
        Set<LeituraTermicaEntity> leituras = new HashSet<>();
        Set<AlertaTermicoEntity> alertas = new HashSet<>();
        Set<TecnicoEntity> tecnicos = new HashSet<>();

        TransformadorEntity entity = mapper.toEntity(request, leituras, alertas, tecnicos);
        String numeroSerie = entity.getNumeroSerie();

        String validar = String.valueOf(repository.findByNumeroSerie(numeroSerie));

        if(validar.equals(numeroSerie))
        {
            throw new TransformadorJaExiste("Já existe um transformador cadastrado com esse número de série");
        }

        TransformadorEntity salvo = repository.save(entity);

        return mapper.toResponse(salvo);
    }

    /**
     * Service para listar todos os transformadores
     * @return Lista de DTOs {@link TransformadorResponseDTO} com a entidade Transformador persistida
     */
    @Transactional(readOnly = true)
    public List<TransformadorResponseDTO> listarTudo()
    {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    /**
     * Service de buscar transformador por número de série
     * @param numeroSerie parâmetro de busca de transformador
     * @return DTO {@link TransformadorDetalhadoResponseDTO} com a entidade Transformador persistida
     * @throws NotFoundException É lançado quando não existe nenhum ID coincidente no banco de dados
     */
    @Transactional(readOnly = true)
    public TransformadorDetalhadoResponseDTO buscarTransformador(String numeroSerie)
    {
        TransformadorEntity entity = repository.findByNumeroSerie(numeroSerie);

        repository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("O recurso solicitado não existe no banco de dados para o identificador ou parâmetro fornecido"));

        return mapper.toDetailedResponse(entity);
    }

    /**
     * Service de atualizar limites de transformador por número de série
     * @param numeroSerie parâmetro de busca de transformador
     * @param limiteTempOleo limite a ser atualizado
     * @param limiteTempEnrol limite a ser atualizado
     * @return DTO {@link TransformadorDetalhadoResponseDTO} com a entidade Transformador persistida
     * @throws NotFoundException É lançado quando não existe nenhum ID coincidente no banco de dados
     */
    @Transactional
    public TransformadorResponseDTO atualizarLimites(String numeroSerie, BigDecimal limiteTempOleo, BigDecimal limiteTempEnrol)
    {
        TransformadorEntity entity = repository.findByNumeroSerie(numeroSerie);

        repository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("O recurso solicitado não existe no banco de dados para o identificador ou parâmetro fornecido"));

        entity.setLimiteTempOleo(limiteTempOleo);
        entity.setLimiteTempEnrol(limiteTempEnrol);

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    /**
     * Service de deletar transformador por número de série
     * @param numeroSerie parâmetro de busca de transformador
     * @throws NotFoundException É lançado quando não existe nenhum ID coincidente no banco de dados
     */
    @Transactional
    public void deletar(String numeroSerie)
    {
        TransformadorEntity entity = repository.findByNumeroSerie(numeroSerie);

        repository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("O recurso solicitado não existe no banco de dados para o identificador ou parâmetro fornecido"));

        repository.delete(entity);
    }
}
