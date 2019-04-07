CREATE SCHEMA carregamento
    AUTHORIZATION postgres;

CREATE SCHEMA controle_frete
    AUTHORIZATION postgres;
	
CREATE SCHEMA controle_logistica
    AUTHORIZATION postgres;
	
CREATE SEQUENCE carregamento.tb01_solicitacao_transporte_nu_solicitacao_transporte_seq;

ALTER SEQUENCE carregamento.tb01_solicitacao_transporte_nu_solicitacao_transporte_seq
    OWNER TO postgres;
	
CREATE SEQUENCE controle_frete.hibernate_sequence;

ALTER SEQUENCE controle_frete.hibernate_sequence
    OWNER TO postgres;
	
CREATE SEQUENCE controle_logistica.hibernate_sequence;

ALTER SEQUENCE controle_logistica.hibernate_sequence
    OWNER TO postgres;
	
CREATE TABLE carregamento.tb04_empresa_concorrente
(
    nu_empresa_concorrente numeric NOT NULL,
    no_empresa_concorrente character varying(30) COLLATE pg_catalog."default" NOT NULL,
    co_cnpj_empresa character varying(14) COLLATE pg_catalog."default" NOT NULL,
    co_endereco_sistema character varying(120) COLLATE pg_catalog."default" NOT NULL,
    co_usuario_sistema character varying(20) COLLATE pg_catalog."default" NOT NULL,
    co_senha_sistema character varying(12) COLLATE pg_catalog."default" NOT NULL,
    vr_tarifa_frete numeric NOT NULL DEFAULT 5.0,
    CONSTRAINT pk_tb04_empresa_concorrente PRIMARY KEY (nu_empresa_concorrente)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE carregamento.tb04_empresa_concorrente
    OWNER to postgres;
	
CREATE TABLE carregamento.tb02_tipo_situacao_transporte
(
    nu_tipo_situacao_transporte numeric NOT NULL,
    no_tipo_situacao_transporte character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_tb02_tipo_situacao_transpor PRIMARY KEY (nu_tipo_situacao_transporte)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE carregamento.tb02_tipo_situacao_transporte
    OWNER to postgres;
	
CREATE TABLE carregamento.tb01_solicitacao_transporte
(
    nu_solicitacao_transporte numeric NOT NULL DEFAULT nextval('carregamento.tb01_solicitacao_transporte_nu_solicitacao_transporte_seq'::regclass),
    nu_empresa_concorrente numeric,
    nu_tipo_situacao_transporte numeric NOT NULL,
    dh_entrada_solicitacao date NOT NULL,
    co_localizacao_origem character varying(30) COLLATE pg_catalog."default" NOT NULL,
    co_localizacao_destino character varying(30) COLLATE pg_catalog."default" NOT NULL,
    co_keycloak_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    de_tipo_excepcionalidade character varying(20) COLLATE pg_catalog."default",
    co_email_remetente character varying(100) COLLATE pg_catalog."default",
    co_email_destinatario character varying(100) COLLATE pg_catalog."default",
    co_email_solicitante character varying(100) COLLATE pg_catalog."default",
    vr_frete numeric NOT NULL DEFAULT 0,
    qt_prazo_entrega numeric NOT NULL DEFAULT 0,
    CONSTRAINT pk_tb01_solicitacao_transporte PRIMARY KEY (nu_solicitacao_transporte),
    CONSTRAINT fk_tb01_tb02 FOREIGN KEY (nu_tipo_situacao_transporte)
        REFERENCES carregamento.tb02_tipo_situacao_transporte (nu_tipo_situacao_transporte) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_tb01_tb04 FOREIGN KEY (nu_empresa_concorrente)
        REFERENCES carregamento.tb04_empresa_concorrente (nu_empresa_concorrente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE carregamento.tb01_solicitacao_transporte
    OWNER to postgres;
	
CREATE TABLE controle_logistica.tb01_evento_solicitacao
(
    nu_solicitacao_evento numeric NOT NULL DEFAULT nextval('controle_logistica.hibernate_sequence'::regclass),
    nu_tipo_situacao_transporte numeric NOT NULL,
    dh_registro timestamp without time zone NOT NULL,
    dh_envio_evento timestamp without time zone
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE controle_logistica.tb01_evento_solicitacao
    OWNER to postgres;
	
CREATE TABLE controle_logistica.tb02_avaliacao_solicitacao
(
    nu_avaliacao_entrega numeric NOT NULL,
    nu_solicitacao_transporte numeric NOT NULL,
    dh_avaliacao timestamp without time zone NOT NULL,
    vr_nota_avaliacao character varying COLLATE pg_catalog."default" NOT NULL,
    de_avaliacao character varying(500) COLLATE pg_catalog."default" NOT NULL,
    co_email_avaliador character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_tb02 PRIMARY KEY (nu_avaliacao_entrega)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE controle_logistica.tb02_avaliacao_solicitacao
    OWNER to postgres;
	
CREATE TABLE controle_frete.tb01_tarifa_frete
(
    nu_tarifa_frete numeric NOT NULL DEFAULT nextval('controle_frete.hibernate_sequence'::regclass),
    vr_tarifa_frete numeric NOT NULL,
    qt_distancia_concorrente numeric NOT NULL,
    qt_distancia numeric NOT NULL,
    co_cep_origem character varying(8) COLLATE pg_catalog."default" NOT NULL,
    co_cep_destino character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_tb01 PRIMARY KEY (nu_tarifa_frete)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE controle_frete.tb01_tarifa_frete
    OWNER to postgres;
 
   
INSERT INTO carregamento.tb02_tipo_situacao_transporte
(nu_tipo_situacao_transporte, no_tipo_situacao_transporte)
VALUES(1,	'Solicitação Pendente');

INSERT INTO carregamento.tb02_tipo_situacao_transporte
(nu_tipo_situacao_transporte, no_tipo_situacao_transporte)
VALUES(2,	'Coletanto');

INSERT INTO carregamento.tb02_tipo_situacao_transporte
(nu_tipo_situacao_transporte, no_tipo_situacao_transporte)
VALUES(3,	'Coletada');

INSERT INTO carregamento.tb02_tipo_situacao_transporte
(nu_tipo_situacao_transporte, no_tipo_situacao_transporte)
VALUES(4,	'A Caminho');

INSERT INTO carregamento.tb02_tipo_situacao_transporte
(nu_tipo_situacao_transporte, no_tipo_situacao_transporte)
VALUES(5,	'Entregue');

INSERT INTO carregamento.tb02_tipo_situacao_transporte
(nu_tipo_situacao_transporte, no_tipo_situacao_transporte)
VALUES(6,	'Transferido');   
   
INSERT INTO carregamento.tb04_empresa_concorrente
(nu_empresa_concorrente, no_empresa_concorrente, co_cnpj_empresa, co_endereco_sistema, co_usuario_sistema, co_senha_sistema, vr_tarifa_frete)
VALUES(1, 'ABC Trasportadora', '07721559000150', '-', '-', '-', 5.0);

INSERT INTO carregamento.tb04_empresa_concorrente
(nu_empresa_concorrente, no_empresa_concorrente, co_cnpj_empresa, co_endereco_sistema, co_usuario_sistema, co_senha_sistema, vr_tarifa_frete)
VALUES(2, 'Fash Trasportadora', '10100566000149', '-', '-', '-', 5.0);

INSERT INTO carregamento.tb04_empresa_concorrente
(nu_empresa_concorrente, no_empresa_concorrente, co_cnpj_empresa, co_endereco_sistema, co_usuario_sistema, co_senha_sistema, vr_tarifa_frete)
VALUES(3, 'Fedex', '55894346000172', '-', '-', '-', 5.0);