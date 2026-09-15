package br.com.ctw.api_monitoramento_transformadores.mapper;

import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorDetalhadoResponseDTO;
import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorRequestDTO;
import br.com.ctw.api_monitoramento_transformadores.dto.TransformadorResponseDTO;
import br.com.ctw.api_monitoramento_transformadores.entity.AlertaTermicoEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.LeituraTermicaEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.TecnicoEntity;
import br.com.ctw.api_monitoramento_transformadores.entity.TransformadorEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TransformadorMapper
{
    /**
     * Transformar uma requisição (request) de Transformador em uma entidade Transformador
     * @param request requisição usada para transição de request para entity
     * @param leituras Set de LeituraTermica
     * @param alertas Set de AlertaTermico
     * @param tecnicos Set de Tecnicos
     * @return Entidade transicionada de Transformador
     */
    public TransformadorEntity toEntity(TransformadorRequestDTO request, Set<LeituraTermicaEntity> leituras, Set<AlertaTermicoEntity> alertas, Set<TecnicoEntity> tecnicos)
    {
        return TransformadorEntity.builder()
                .numeroSerie(request.numeroSerie())
                .modelo(request.modelo())
                .subestacao(request.subestacao())
                .potenciaKva(request.potenciaKva())
                .limiteTempOleo(request.limiteTempOleo())
                .limiteTempEnrol(request.limiteTempEnrol())
                .leituras(leituras)
                .alertas(alertas)
                .tecnicos(tecnicos)
                .build();
    }

    /**
     * Transformar uma entidade de Transformador em uma resposta (response) de Transformador
     * @param entity entidade usada para transição de entity para resposta
     * @return Resposta transicionada de Transformador
     */
    public TransformadorResponseDTO toResponse(TransformadorEntity entity)
    {
        return new TransformadorResponseDTO(
                entity.getId(), entity.getNumeroSerie(), entity.getModelo(), entity.getSubestacao(),
                entity.getPotenciaKva(), entity.getLimiteTempOleo(), entity.getLimiteTempEnrol(),
                entity.getLeituras(), entity.getAlertas(), entity.getTecnicos()
        );
    }

    public TransformadorDetalhadoResponseDTO toDetailedResponse(TransformadorEntity entity)
    {
        return new TransformadorDetalhadoResponseDTO(
                entity.getId(), entity.getNumeroSerie(), entity.getLeituras(), entity.getTecnicos()
        );
    }
}
