-- =============================================================================
-- CARGA DE DADOS DE TESTE: TRANSFORMADORES
-- =============================================================================
INSERT INTO transformador ( numero_serie, modelo, subestacao, potencia_kva, limite_temp_oleo, limite_temp_enrol)
VALUES ( 'TRF-2026-1001', 'Transformador Trifásico de Força 15MVA', 'Subestação Central - Setor A', 15000.00, 85.00, 105.00);

INSERT INTO transformador (numero_serie, modelo, subestacao, potencia_kva, limite_temp_oleo, limite_temp_enrol)
VALUES ('TRF-2026-1002', 'Transformador Elevador de Tensão 30MVA', 'Subestação Norte - Setor B', 30000.00, 80.00, 100.00);

-- =============================================================================
-- CARGA DE DADOS DE TESTE: TÉCNICOS
-- =============================================================================
INSERT INTO tecnico (cpf, nome, especialidade, email)
VALUES ( '111.222.333-44', 'Carlos Eduardo Silva', 'Eletrotécnica de Alta Tensão', 'carlos.silva@industria.com');

INSERT INTO tecnico ( cpf, nome, especialidade, email)
VALUES ( '555.666.777-88', 'Mariana Oliveira Santos', 'Termografia e Análise de Óleo', 'mariana.santos@industria.com');

INSERT INTO tecnico (cpf, nome, especialidade, email)
VALUES ( '999.888.777-66', 'Roberto Alves Lima', 'Proteção e Automação', 'roberto.lima@industria.com');

-- =============================================================================
-- CARGA DE DADOS DE TESTE: RELACIONAMENTO MANY-TO-MANY (TRANSFORMADOR_TECNICO)
-- =============================================================================
-- Transformador 1 possui os técnicos 1 e 2
INSERT INTO transformador_tecnico (transformador_id, tecnico_id) VALUES (1, 1);
INSERT INTO transformador_tecnico (transformador_id, tecnico_id) VALUES (1, 2);

-- Transformador 2 possui os técnicos 2 e 3 (Mariana atende a ambos os transformadores)
INSERT INTO transformador_tecnico (transformador_id, tecnico_id) VALUES (2, 2);
INSERT INTO transformador_tecnico (transformador_id, tecnico_id	) VALUES (2, 3);

-- =============================================================================
-- CARGA DE DADOS DE TESTE: LEITURAS E ALERTAS
-- =============================================================================
INSERT INTO leitura_termica ( transformador_id, temp_oleo, temp_enrolamento, data_hora_leitura)
VALUES (1, 89.00, 110.50, '2026-09-10 12:00:00');

INSERT INTO alerta_termico (transformador_id, leitura_id, data_alerta, tipo, descricao)
VALUES (1, 1, '2026-09-10 12:00:05', 'SOBREAQUECIMENTO_CRITICO', 'muito grave');
