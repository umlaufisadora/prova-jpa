package br.com.ctw.api_monitoramento_transformadores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa uma entidade Transformador persistido pela camada de persistência.
 * <p>Essa classe contém os dados internos de Transformados que serão usados para persistência no banco de dados</p>
 */
@Entity
@Table(name = "transformador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransformadorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_serie", nullable = false,length = 50, unique = true)
    private String numeroSerie;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(nullable = false, length = 100)
    private String subestacao;

    @Column(name = "potencia_kva", nullable = false, precision = 10, scale = 2)
    private BigDecimal potenciaKva;

    @Column(name = "limite_temp_oleo", nullable = false, precision = 5, scale = 2)
    private BigDecimal limiteTempOleo;

    @Column(name = "limite_temp_enrol", nullable = false, precision = 5, scale = 2)
    private BigDecimal limiteTempEnrol;

    @OneToMany(mappedBy = "transformador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LeituraTermicaEntity> leituras = new HashSet<>();

    @OneToMany(mappedBy = "transformador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlertaTermicoEntity> alertas = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "transformador_tecnico",
    joinColumns = @JoinColumn(name = "transformador_id"), inverseJoinColumns = @JoinColumn(name = "tecnico_id"))
    private Set<TecnicoEntity> tecnicos = new HashSet<>();
}
