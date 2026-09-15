package br.com.ctw.api_monitoramento_transformadores.repository;

import br.com.ctw.api_monitoramento_transformadores.entity.TransformadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Transformador que representa a tabela "transformador" no banco de dados
 */

public interface TransformadorRepository extends JpaRepository<TransformadorEntity, Long> {

    /**
     * Query feita no repository para procurar um transformador
     * no banco de dados a partir de seu número de série
     * @param numeroSerie parâmetro de busca de transformador
     * @return Entidade de opcional de Transformador
     */
    <Optional>TransformadorEntity findByNumeroSerie(String numeroSerie);
}
