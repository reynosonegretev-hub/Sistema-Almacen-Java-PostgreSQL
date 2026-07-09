--
-- PostgreSQL database dump
--

\restrict e67Xn5skAUn4RJ2t6g2mvrfmNZHTYJe8iQwYt79c4heaGvEEzjqWXzsdtjRqQIO

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-07-08 19:30:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16422)
-- Name: almacen; Type: SCHEMA; Schema: -; Owner: edmp
--

CREATE SCHEMA almacen;


ALTER SCHEMA almacen OWNER TO edmp;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16423)
-- Name: producto; Type: TABLE; Schema: almacen; Owner: edmp
--

CREATE TABLE almacen.producto (
    id_producto integer NOT NULL,
    clave character varying(10) NOT NULL,
    descripcion character varying(80) NOT NULL,
    precio double precision NOT NULL,
    existencia integer NOT NULL
);


ALTER TABLE almacen.producto OWNER TO edmp;

--
-- TOC entry 223 (class 1255 OID 16431)
-- Name: fn_buscar_producto(character varying); Type: FUNCTION; Schema: almacen; Owner: postgres
--

CREATE FUNCTION almacen.fn_buscar_producto(a_clave character varying) RETURNS SETOF almacen.producto
    LANGUAGE sql
    AS $$
SELECT id_producto, clave, descripcion, precio, existencia
	FROM almacen.producto
	WHERE clave=a_clave;
$$;


ALTER FUNCTION almacen.fn_buscar_producto(a_clave character varying) OWNER TO postgres;

--
-- TOC entry 5031 (class 0 OID 0)
-- Dependencies: 223
-- Name: FUNCTION fn_buscar_producto(a_clave character varying); Type: COMMENT; Schema: almacen; Owner: postgres
--

COMMENT ON FUNCTION almacen.fn_buscar_producto(a_clave character varying) IS 'Regresa el producto buscado';


--
-- TOC entry 221 (class 1259 OID 16439)
-- Name: proveedor; Type: TABLE; Schema: almacen; Owner: edmp
--

CREATE TABLE almacen.proveedor (
    id_proveedor integer NOT NULL,
    clave_proveedor character varying(10) CONSTRAINT proveedor_clave_not_null NOT NULL,
    descripcion_proveedor character varying(80) CONSTRAINT proveedor_descripcion_not_null NOT NULL,
    precio_proveedor double precision CONSTRAINT proveedor_precio_not_null NOT NULL,
    ciudad_proveedor character varying(80) CONSTRAINT proveedor_ciudad_not_null NOT NULL
);


ALTER TABLE almacen.proveedor OWNER TO edmp;

--
-- TOC entry 230 (class 1255 OID 16456)
-- Name: fn_buscar_proveedor(character varying); Type: FUNCTION; Schema: almacen; Owner: edmp
--

CREATE FUNCTION almacen.fn_buscar_proveedor(a_clave_proveedor character varying) RETURNS SETOF almacen.proveedor
    LANGUAGE sql
    AS $$
SELECT id_proveedor, clave_proveedor, descripcion_proveedor, precio_proveedor, ciudad_proveedor
	FROM almacen.proveedor
	WHERE clave_proveedor=a_clave_proveedor;
$$;


ALTER FUNCTION almacen.fn_buscar_proveedor(a_clave_proveedor character varying) OWNER TO edmp;

--
-- TOC entry 224 (class 1255 OID 16432)
-- Name: fn_listar_productos(); Type: FUNCTION; Schema: almacen; Owner: edmp
--

CREATE FUNCTION almacen.fn_listar_productos() RETURNS SETOF almacen.producto
    LANGUAGE sql
    AS $$
SELECT id_producto, clave, descripcion, precio, existencia
	FROM almacen.producto;
$$;


ALTER FUNCTION almacen.fn_listar_productos() OWNER TO edmp;

--
-- TOC entry 5032 (class 0 OID 0)
-- Dependencies: 224
-- Name: FUNCTION fn_listar_productos(); Type: COMMENT; Schema: almacen; Owner: edmp
--

COMMENT ON FUNCTION almacen.fn_listar_productos() IS 'crea una lista de productos';


--
-- TOC entry 228 (class 1255 OID 16452)
-- Name: fn_listar_proveedores(); Type: FUNCTION; Schema: almacen; Owner: edmp
--

CREATE FUNCTION almacen.fn_listar_proveedores() RETURNS SETOF almacen.proveedor
    LANGUAGE sql
    AS $$SELECT id_proveedor, clave_proveedor, descripcion_proveedor, precio_proveedor, ciudad_proveedor
	FROM almacen.proveedor;$$;


ALTER FUNCTION almacen.fn_listar_proveedores() OWNER TO edmp;

--
-- TOC entry 225 (class 1255 OID 16433)
-- Name: sp_agregar_producto(character varying, character varying, double precision, integer); Type: PROCEDURE; Schema: almacen; Owner: edmp
--

CREATE PROCEDURE almacen.sp_agregar_producto(IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer)
    LANGUAGE sql
    AS $$
INSERT INTO almacen.producto(
	clave, descripcion, precio, existencia)
	VALUES (a_clave, a_descripcion, a_precio, a_existencia);
$$;


ALTER PROCEDURE almacen.sp_agregar_producto(IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer) OWNER TO edmp;

--
-- TOC entry 5033 (class 0 OID 0)
-- Dependencies: 225
-- Name: PROCEDURE sp_agregar_producto(IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer); Type: COMMENT; Schema: almacen; Owner: edmp
--

COMMENT ON PROCEDURE almacen.sp_agregar_producto(IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer) IS 'inserta un registro en la tabla de producto';


--
-- TOC entry 229 (class 1255 OID 16453)
-- Name: sp_agregar_proveedor(character varying, character varying, double precision, character varying); Type: PROCEDURE; Schema: almacen; Owner: edmp
--

CREATE PROCEDURE almacen.sp_agregar_proveedor(IN a_clave_proveedor character varying, IN a_descripcion_proveedor character varying, IN a_precio_proveedor double precision, IN a_ciudad_proveedor character varying)
    LANGUAGE sql
    AS $$INSERT INTO almacen.proveedor(
	clave_proveedor, descripcion_proveedor, precio_proveedor, ciudad_proveedor)
	VALUES (a_clave_proveedor, a_descripcion_proveedor, a_precio_proveedor, a_ciudad_proveedor);
$$;


ALTER PROCEDURE almacen.sp_agregar_proveedor(IN a_clave_proveedor character varying, IN a_descripcion_proveedor character varying, IN a_precio_proveedor double precision, IN a_ciudad_proveedor character varying) OWNER TO edmp;

--
-- TOC entry 226 (class 1255 OID 16434)
-- Name: sp_eliminar_producto(integer); Type: PROCEDURE; Schema: almacen; Owner: edmp
--

CREATE PROCEDURE almacen.sp_eliminar_producto(IN a_id_producto integer)
    LANGUAGE sql
    AS $$
DELETE FROM almacen.producto
	WHERE id_producto= a_id_producto;

$$;


ALTER PROCEDURE almacen.sp_eliminar_producto(IN a_id_producto integer) OWNER TO edmp;

--
-- TOC entry 5034 (class 0 OID 0)
-- Dependencies: 226
-- Name: PROCEDURE sp_eliminar_producto(IN a_id_producto integer); Type: COMMENT; Schema: almacen; Owner: edmp
--

COMMENT ON PROCEDURE almacen.sp_eliminar_producto(IN a_id_producto integer) IS 'Elimina un registro en la tabla de producto';


--
-- TOC entry 231 (class 1255 OID 16457)
-- Name: sp_eliminar_proveedor(integer); Type: PROCEDURE; Schema: almacen; Owner: edmp
--

CREATE PROCEDURE almacen.sp_eliminar_proveedor(IN a_id_proveedor integer)
    LANGUAGE sql
    AS $$DELETE FROM almacen.proveedor
	WHERE id_proveedor= a_id_proveedor;
$$;


ALTER PROCEDURE almacen.sp_eliminar_proveedor(IN a_id_proveedor integer) OWNER TO edmp;

--
-- TOC entry 227 (class 1255 OID 16435)
-- Name: sp_modificar_producto(integer, character varying, character varying, double precision, integer); Type: PROCEDURE; Schema: almacen; Owner: edmp
--

CREATE PROCEDURE almacen.sp_modificar_producto(IN a_id_producto integer, IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer)
    LANGUAGE sql
    AS $$
UPDATE almacen.producto
	SET clave=a_clave, descripcion=a_descripcion, precio= a_precio, existencia=a_existencia
	WHERE id_producto=a_id_producto;

$$;


ALTER PROCEDURE almacen.sp_modificar_producto(IN a_id_producto integer, IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer) OWNER TO edmp;

--
-- TOC entry 5035 (class 0 OID 0)
-- Dependencies: 227
-- Name: PROCEDURE sp_modificar_producto(IN a_id_producto integer, IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer); Type: COMMENT; Schema: almacen; Owner: edmp
--

COMMENT ON PROCEDURE almacen.sp_modificar_producto(IN a_id_producto integer, IN a_clave character varying, IN a_descripcion character varying, IN a_precio double precision, IN a_existencia integer) IS 'Modifica un registro en la tabla de producto';


--
-- TOC entry 232 (class 1255 OID 16458)
-- Name: sp_modificar_proveedor(integer, character varying, character varying, double precision, character varying); Type: PROCEDURE; Schema: almacen; Owner: edmp
--

CREATE PROCEDURE almacen.sp_modificar_proveedor(IN a_id_proveedor integer, IN a_clave_proveedor character varying, IN a_descripcion_proveedor character varying, IN a_precio_proveedor double precision, IN a_ciudad_proveedor character varying)
    LANGUAGE sql
    AS $$UPDATE almacen.proveedor
	SET clave_proveedor=a_clave_proveedor, descripcion_proveedor=a_descripcion_proveedor, precio_proveedor= a_precio_proveedor, ciudad_proveedor=a_ciudad_proveedor
	WHERE id_proveedor=a_id_proveedor;$$;


ALTER PROCEDURE almacen.sp_modificar_proveedor(IN a_id_proveedor integer, IN a_clave_proveedor character varying, IN a_descripcion_proveedor character varying, IN a_precio_proveedor double precision, IN a_ciudad_proveedor character varying) OWNER TO edmp;

--
-- TOC entry 220 (class 1259 OID 16436)
-- Name: producto_id_producto_seq; Type: SEQUENCE; Schema: almacen; Owner: edmp
--

ALTER TABLE almacen.producto ALTER COLUMN id_producto ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME almacen.producto_id_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 16449)
-- Name: proveedor_id_proveedor_seq; Type: SEQUENCE; Schema: almacen; Owner: edmp
--

ALTER TABLE almacen.proveedor ALTER COLUMN id_proveedor ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME almacen.proveedor_id_proveedor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 5022 (class 0 OID 16423)
-- Dependencies: 219
-- Data for Name: producto; Type: TABLE DATA; Schema: almacen; Owner: edmp
--

COPY almacen.producto (id_producto, clave, descripcion, precio, existencia) FROM stdin;
5	1004	Memoria USB 32GB	99.5	50
6	1005	Audífonos Bluetooth	450	27
12	321	audifonos	1500	67
13	246	ipad	30000	89
14	642	iphone	15000	23
17	c-018	camiseta picachu	300	18
19	008	blusa	200	18
23	1002	toalla	1299	28
32	9843	computadora Gamer Rebajas	45000	1
22	9848	camara portatil	6000	20
\.


--
-- TOC entry 5024 (class 0 OID 16439)
-- Dependencies: 221
-- Data for Name: proveedor; Type: TABLE DATA; Schema: almacen; Owner: edmp
--

COPY almacen.proveedor (id_proveedor, clave_proveedor, descripcion_proveedor, precio_proveedor, ciudad_proveedor) FROM stdin;
22	l000	botella	67	mexico
25	p99	cera	1223	mexico
26	0011	perchero	12000	mexico
29	246	silla	123	mexico
30	222	mesa	34	mexico
31	124	telefono	123	mexico
\.


--
-- TOC entry 5036 (class 0 OID 0)
-- Dependencies: 220
-- Name: producto_id_producto_seq; Type: SEQUENCE SET; Schema: almacen; Owner: edmp
--

SELECT pg_catalog.setval('almacen.producto_id_producto_seq', 38, true);


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 222
-- Name: proveedor_id_proveedor_seq; Type: SEQUENCE SET; Schema: almacen; Owner: edmp
--

SELECT pg_catalog.setval('almacen.proveedor_id_proveedor_seq', 41, true);


--
-- TOC entry 4872 (class 2606 OID 16438)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: almacen; Owner: edmp
--

ALTER TABLE ONLY almacen.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);


--
-- TOC entry 4874 (class 2606 OID 16448)
-- Name: proveedor proveedor_pkey; Type: CONSTRAINT; Schema: almacen; Owner: edmp
--

ALTER TABLE ONLY almacen.proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (id_proveedor);


-- Completed on 2026-07-08 19:30:41

--
-- PostgreSQL database dump complete
--

\unrestrict e67Xn5skAUn4RJ2t6g2mvrfmNZHTYJe8iQwYt79c4heaGvEEzjqWXzsdtjRqQIO

