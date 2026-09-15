package br.com.ctw.api_monitoramento_transformadores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa uma entidade LeituraTermica persistido pela camada de persistência.
 * <p>Essa classe contém os dados internos de LeituraTermica que serão usados para persistência no banco de dados</p>
 */

@Entity
@Table(name = "leitura_termica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeituraTermicaEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transformador_id", nullable = false)
    private TransformadorEntity transformador;

    @Column(name = "temp_oleo", nullable = false, precision = 5, scale = 2)
    private BigDecimal tempOleo;

    @Column(name = "temp_enrolamento", nullable = false, precision = 5, scale = 2)
    private BigDecimal tempEnrolamento;

    @Column(name = "data_hora_leitura", nullable = false)
    private LocalDateTime dataHora;

    @OneToOne(mappedBy = "leitura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AlertaTermicoEntity alerta;
}
