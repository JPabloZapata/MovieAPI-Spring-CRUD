-- Database: Cine_DB

-- DROP DATABASE IF EXISTS "Cine_DB";

CREATE DATABASE "Cine_DB"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "Cine_DB"
    IS 'Simple CRUD for technical testing';

-- Table: public.movie

-- DROP TABLE IF EXISTS public.movie;

CREATE TABLE IF NOT EXISTS public.movie
(
    id_pelicula integer NOT NULL DEFAULT nextval('peliculas_id_pelicula_seq'::regclass),
    titulo character varying(250) COLLATE pg_catalog."default" NOT NULL,
    genero character varying(100) COLLATE pg_catalog."default",
    director character varying(100) COLLATE pg_catalog."default",
    anio_lanzamiento integer,
    duracion integer,
    fecha_creacion timestamp without time zone,
    CONSTRAINT peliculas_pkey PRIMARY KEY (id_pelicula)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movie
    OWNER to postgres;

COMMENT ON TABLE public.movie
    IS 'Simple CRUD for technical testing';

--INSERT TABLE movie

INSERT INTO public.movie (titulo, genero, director, anio_lanzamiento, duracion, fecha_creacion) 
VALUES ('Titanic', 'Drama', 'James Cameron', 1997, 195, CURRENT_TIMESTAMP);

INSERT INTO public.movie (titulo, genero, director, anio_lanzamiento, duracion, fecha_creacion) 
VALUES ('El padrino', 'Drama', 'Francis Ford Coppola', 1972, 175, CURRENT_TIMESTAMP);

INSERT INTO public.movie (titulo, genero, director, anio_lanzamiento, duracion, fecha_creacion) 
VALUES ('Interestelar', 'Ciencia ficción', 'Christopher Nolan', 2014, 169, CURRENT_TIMESTAMP);

INSERT INTO public.movie (titulo, genero, director, anio_lanzamiento, duracion, fecha_creacion) 
VALUES ('Matrix', 'Ciencia ficción', 'Lana Wachowski, Lilly Wachowski', 1999, 136, CURRENT_TIMESTAMP);

INSERT INTO public.movie (titulo, genero, director, anio_lanzamiento, duracion, fecha_creacion) 
VALUES ('La lista de Schindler', 'Drama', 'Steven Spielberg', 1993, 195, CURRENT_TIMESTAMP);

--SELECT
SELECT * FROM public.movie