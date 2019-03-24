/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  damianri
 * Created: 21/03/2019
 */

-- Database: postgres

-- DROP DATABASE postgres;

CREATE DATABASE postgres
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'es_MX.UTF-8'
    LC_CTYPE = 'es_MX.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE postgres
    IS 'default administrative connection database';


-- SEQUENCE: public.seq_academico

-- DROP SEQUENCE public.seq_academico;

CREATE SEQUENCE public.seq_academico;

ALTER SEQUENCE public.seq_academico
    OWNER TO postgres;

-- SEQUENCE: public.seq_administrador

-- DROP SEQUENCE public.seq_administrador;

CREATE SEQUENCE public.seq_administrador;

ALTER SEQUENCE public.seq_administrador
    OWNER TO postgres;

-- SEQUENCE: public.seq_espacio

-- DROP SEQUENCE public.seq_espacio;

CREATE SEQUENCE public.seq_espacio;

ALTER SEQUENCE public.seq_espacio
    OWNER TO postgres;


-- Table: public.academico

-- DROP TABLE public.academico;

CREATE TABLE public.academico
(
    id_academico bigint NOT NULL DEFAULT nextval('seq_academico'::regclass),
    nombre_completo character varying(210) COLLATE pg_catalog."default" NOT NULL,
    correo_aca character varying(210) COLLATE pg_catalog."default" NOT NULL,
    password character varying(210) COLLATE pg_catalog."default" NOT NULL,
    departamento character varying(100) COLLATE pg_catalog."default" NOT NULL,
    tipo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    no_trabajador bigint NOT NULL,
    fecha_activacion date,
    user_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT academico_pkey PRIMARY KEY (id_academico)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.academico
    OWNER to postgres;

-- Table: public.administrador

-- DROP TABLE public.administrador;

CREATE TABLE public.administrador
(
    id_administrador bigint NOT NULL DEFAULT nextval('seq_administrador'::regclass),
    nombre_completo character varying(210) COLLATE pg_catalog."default" NOT NULL,
    correo_admin character varying(210) COLLATE pg_catalog."default" NOT NULL,
    password character varying(210) COLLATE pg_catalog."default" NOT NULL,
    no_trabajador bigint NOT NULL,
    CONSTRAINT administrador_pkey PRIMARY KEY (id_administrador)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.administrador
    OWNER to postgres;


-- Table: public.espacio

-- DROP TABLE public.espacio;

CREATE TABLE public.espacio
(
    id_espacio bigint NOT NULL DEFAULT nextval('seq_espacio'::regclass),
    nombre_espacio character varying(210) COLLATE pg_catalog."default" NOT NULL,
    edificio character varying(210) COLLATE pg_catalog."default" NOT NULL,
    capacidad integer NOT NULL,
    recursos character varying(255) COLLATE pg_catalog."default" NOT NULL,
    piso integer NOT NULL,
    CONSTRAINT espacio_pkey PRIMARY KEY (id_espacio)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.espacio
    OWNER to postgres;