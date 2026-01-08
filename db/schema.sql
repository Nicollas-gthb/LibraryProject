--
-- PostgreSQL database dump
--

\restrict mZNjFFrS87pyJ2CNSP6mjJjyKfMyg9O3QUr07vMR63iHbYGXpJgumkH4KyBVH7U

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2026-01-08 16:35:53

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 24697)
-- Name: cliente; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nome character varying(80) NOT NULL,
    cpf character varying(20) NOT NULL,
    email character varying(80) NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 24696)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 219
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- TOC entry 222 (class 1259 OID 24708)
-- Name: emprestimo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.emprestimo (
    id_emprestimo integer NOT NULL,
    id_livro integer NOT NULL,
    id_cliente integer NOT NULL,
    data_emprestimo date NOT NULL,
    data_devolucao date
);


--
-- TOC entry 221 (class 1259 OID 24707)
-- Name: emprestimo_id_emprestimo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.emprestimo_id_emprestimo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 221
-- Name: emprestimo_id_emprestimo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.emprestimo_id_emprestimo_seq OWNED BY public.emprestimo.id_emprestimo;


--
-- TOC entry 218 (class 1259 OID 24689)
-- Name: livro; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.livro (
    id_livro integer NOT NULL,
    titulo character varying(80) NOT NULL,
    autor character varying(80) NOT NULL,
    ano integer,
    disponivel boolean DEFAULT true
);


--
-- TOC entry 217 (class 1259 OID 24688)
-- Name: livro_id_livro_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.livro_id_livro_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 217
-- Name: livro_id_livro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.livro_id_livro_seq OWNED BY public.livro.id_livro;


--
-- TOC entry 4754 (class 2604 OID 24700)
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- TOC entry 4755 (class 2604 OID 24711)
-- Name: emprestimo id_emprestimo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.emprestimo ALTER COLUMN id_emprestimo SET DEFAULT nextval('public.emprestimo_id_emprestimo_seq'::regclass);


--
-- TOC entry 4752 (class 2604 OID 24692)
-- Name: livro id_livro; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.livro ALTER COLUMN id_livro SET DEFAULT nextval('public.livro_id_livro_seq'::regclass);


--
-- TOC entry 4759 (class 2606 OID 24704)
-- Name: cliente cliente_cpf_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cpf_key UNIQUE (cpf);


--
-- TOC entry 4761 (class 2606 OID 24706)
-- Name: cliente cliente_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_email_key UNIQUE (email);


--
-- TOC entry 4763 (class 2606 OID 24702)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 4765 (class 2606 OID 24713)
-- Name: emprestimo emprestimo_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_pkey PRIMARY KEY (id_emprestimo);


--
-- TOC entry 4757 (class 2606 OID 24695)
-- Name: livro livro_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT livro_pkey PRIMARY KEY (id_livro);


--
-- TOC entry 4766 (class 2606 OID 24719)
-- Name: emprestimo emprestimo_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente) ON DELETE RESTRICT;


--
-- TOC entry 4767 (class 2606 OID 24714)
-- Name: emprestimo emprestimo_id_livro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_id_livro_fkey FOREIGN KEY (id_livro) REFERENCES public.livro(id_livro) ON DELETE RESTRICT;


-- Completed on 2026-01-08 16:35:53

--
-- PostgreSQL database dump complete
--

\unrestrict mZNjFFrS87pyJ2CNSP6mjJjyKfMyg9O3QUr07vMR63iHbYGXpJgumkH4KyBVH7U

