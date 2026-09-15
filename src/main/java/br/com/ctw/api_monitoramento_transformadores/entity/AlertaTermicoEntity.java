package br.com.ctw.api_monitoramento_transformadores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Representa uma entidade AlertaTermico persistido pela camada de persistência.
 * <p>Essa classe contém os dados internos de AlertaTermico que serão usados para persistência no banco de dados</p>
 */
@Entity
@Table(name = "alerta_termico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertaTermicoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transformador_id", nullable = false)
    private TransformadorEntity transformador;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitura_id", nullable = false)
    private LeituraTermicaEntity leitura;

    @Column(name = "data_alerta", nullable = false)
    private LocalDateTime dataAlerta;

    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(nullable = false, length = 255)
    private String descricao;

}
