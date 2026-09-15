package br.com.ctw.api_monitoramento_transformadores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa uma entidade Tecnico persistido pela camada de persistência.
 * <p>Essa classe contém os dados internos de Tecnico que serão usados para persistência no banco de dados</p>
 */

@Entity
@Table(name = "tecnico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TecnicoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(nullable = false, length = 50)
    private String email;

    @ManyToMany(mappedBy = "tecnicos", fetch = FetchType.LAZY)
    private Set<TransformadorEntity> transformadores = new HashSet<>();
}
