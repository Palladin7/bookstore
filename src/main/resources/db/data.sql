--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-07-21 15:14:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3019 (class 0 OID 26618)
-- Dependencies: 201
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (id, author, available, pages, price, title, category_id) FROM stdin;
5	Trent Dalton	11	465	11.99	Skies	2
6	T. J. Newman	33	303	14.99	Falling	3
7	Willa Richards	12	400	14.99	The Comfort	3
9	Katie Kitamura	8	240	13.99	Attachment	4
1	Joyse Mayanard	29	464	14.99	Count the Ways	1
8	Emily Austin	31	255	13.99	This Room	3
2	S. A. Cosby	41	336	13.99	Razorblade Tears	1
3	Rachel Yoder	6	256	12.99	Night	1
4	Matt Bell	19	480	13.99	Apple Seed	2
\.


--
-- TOC entry 3021 (class 0 OID 26637)
-- Dependencies: 203
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (id, name) FROM stdin;
2	Adventure
3	Mystery
4	Romance
1	Fantasy
\.


--
-- TOC entry 3025 (class 0 OID 26708)
-- Dependencies: 207
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, address, date, phone, book_id, user_id) FROM stdin;
5	Calista Wise\n7292 Dictum Av.	2021-07-17 10:17:44.575	7834215711	8	2
1	Cecilia Chapman\n711-2880 Nulla St.	2021-07-16 16:02:24.26	5634215735	5	2
4	Theodore Lowe\nAp #867-859 Sit Rd.	2021-07-17 10:15:56.501	2334215732	3	3
3	Celeste Slater\n606-3727 Ullamcorper. Street	2021-07-16 20:24:33.499	6634215765	4	5
2	Iris Watson\nP.O. Box 283 8562 Fusce Rd.	2021-07-16 16:03:51.283	1834215375	1	2
\.


--
-- TOC entry 3023 (class 0 OID 26656)
-- Dependencies: 205
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, first_name, last_name, password, roles) FROM stdin;
2	liam_jones@gmail.com	Liam	Jones	$2a$10$CAVovJfa.DYUOUKCT4wje.g5de9uN7Bnk4bIDcUDGqCdbCW08TNP.	USER
3	tom_brown@gmail.com	Tom	Brown	$2a$10$drc3IgNPJdzIvHJDGoIy6uOIWPdd0Gq.KMsRx02ZgHieY9cK3UDBi	USER
4	admin@gmail.com	Admin	Admin	$2a$10$2rYosGjAQ/20HqtesURkR.5AivnZ.e2HuSTNcsWlfQWxPcRXhe30q	USER,ADMIN
5	arthour_smith@gmail.com	Arthour	Smith	$2a$10$fBKHgvt0pmr6G7l07W7EDOz2TqFMPxwvJpZuypCJ/vWR0crkCtx9.	USER
\.


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 200
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.books_id_seq', 36, true);


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 202
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_id_seq', 71, true);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 206
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 6, true);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 38, true);


-- Completed on 2021-07-21 15:14:07

--
-- PostgreSQL database dump complete
--

