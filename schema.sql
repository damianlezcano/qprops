-- Drop table

-- DROP TABLE PUBLIC.PUBLICACION;

CREATE TABLE PUBLICACION (
	UUID VARCHAR(255),
	AMBIENTES INTEGER,
	BARRIO VARCHAR(255),
	CALLE VARCHAR(255),
	CIUDAD VARCHAR(255),
	DESCRIPCION VARCHAR(255),
	FECHA_CREACION TIMESTAMP,
	HOST VARCHAR(255),
	IMG VARCHAR(255),
	LINK VARCHAR(255),
	M2 INTEGER,
	PRECIO_TIPO_MONEDA VARCHAR(255),
	PRECIO_VALOR DOUBLE
);


INSERT INTO PUBLIC.PUBLICACION
(AMBIENTES, BARRIO, CALLE, CIUDAD, DESCRIPCION, FECHA_CREACION, HOST, IMG, LINK, M2, "UUID")
VALUES(2, 'Flores', 'Carlos Ortiz al 900', 'Capital Federal', 'Carlos Ortiz Al 900 - Excelente Casa Ex-municipal Reciclada Con Cochera! Oportunidad', '2019-09-20 17:28:04.823', NULL, 'https://http2.mlstatic.com/carlos-ortiz-al-900-excelente-casa-ex-municipal-reciclada-con-cochera-oportunidad-D_NQ_NP_808958-MLA32124450308_092019-W.webp', 'https://casa.mercadolibre.com.ar/MLA-813801946-carlos-ortiz-al-900-excelente-casa-ex-municipal-reciclada-con-cochera-oportunidad-_JM#position=1&type=item&tracking_id=948e242f-8a9e-4864-90eb-19e2ef063247', 50, 'Q2FybG9zIE9ydGl6IEFsIDkwMCAtIEV4Y2VsZW50ZSBDYXNhIEV4LW11bmljaXBhbCBSZWNpY2xhZGEgQ29uIENvY2hlcmEhIE9wb3J0dW5pZGFkNTAyQ2FwaXRhbCBGZWRlcmFsIC0gRmxvcmVzIC0gQ2FybG9zIE9ydGl6IGFsIDkwMA==');

INSERT INTO PUBLIC.PUBLICACION
(AMBIENTES, BARRIO, CALLE, CIUDAD, DESCRIPCION, FECHA_CREACION, HOST, IMG, LINK, M2, "UUID")
VALUES(5, 'Almagro', 'Evaristo Carriego al 900', 'Capital Federal', 'Evaristo Carriego 900 - Casa Ex-municipal De 4 Ambientes En El Barrio De Flores - Quincho - Parrilla - Escritorio - Excelente Estado', '2018-02-07 17:28:04.845', NULL, 'https://http2.mlstatic.com/evaristo-carriego-900-casa-ex-municipal-de-4-ambientes-en-el-barrio-de-flores-quincho-parrilla-escritorio-excelente-estado-D_NQ_NP_713691-MLA32092115906_092019-W.webp', 'https://casa.mercadolibre.com.ar/MLA-813276371-evaristo-carriego-900-casa-ex-municipal-de-4-ambientes-en-el-barrio-de-flores-quincho-parrilla-escritorio-excelente-estado-_JM#position=2&type=item&tracking_id=948e242f-8a9e-4864-90eb-19e2ef063247', 100, 'RXZhcmlzdG8gQ2FycmllZ28gOTAwIC0gQ2FzYSBFeC1tdW5pY2lwYWwgRGUgNCBBbWJpZW50ZXMgRW4gRWwgQmFycmlvIERlIEZsb3JlcyAtIFF1aW5jaG8gLSBQYXJyaWxsYSAtIEVzY3JpdG9yaW8gLSBFeGNlbGVudGUgRXN0YWRvMTAwNUNhcGl0YWwgRmVkZXJhbCAtIEFsbWFncm8gLSBFdmFyaXN0byBDYXJyaWVnbyBhbCA5MDA=');

INSERT INTO PUBLIC.PUBLICACION
(AMBIENTES, BARRIO, CALLE, CIUDAD, DESCRIPCION, FECHA_CREACION, HOST, IMG, LINK, M2, "UUID")
VALUES(2, 'Abasto', 'Manuel de Eguia alt Eva Peron 1200', 'Capital Federal', 'Casa Estilo En Refaccion Barrio Flores Casas Municipales', '2019-06-02 17:28:04.846', NULL, 'https://http2.mlstatic.com/casa-estilo-en-refaccion-barrio-flores-casas-municipales-D_NQ_NP_835265-MLA32122049433_092019-W.webp', 'https://casa.mercadolibre.com.ar/MLA-813742850-casa-estilo-en-refaccion-barrio-flores-casas-municipales-_JM#position=3&type=item&tracking_id=948e242f-8a9e-4864-90eb-19e2ef063247', 30, 'Q2FzYSBFc3RpbG8gRW4gUmVmYWNjaW9uIEJhcnJpbyBGbG9yZXMgQ2FzYXMgTXVuaWNpcGFsZXMzMDJDYXBpdGFsIEZlZGVyYWwgLSBBYmFzdG8gLSBNYW51ZWwgZGUgRWd1aWEgYWx0IEV2YSBQZXJvbiAxMjAw');












-- Drop table

-- DROP TABLE PUBLIC.ESTADO;

CREATE TABLE PUBLIC.ESTADO (
	UUID VARCHAR(255),
	NOTAS VARCHAR(255),
	TIPO VARCHAR(255)
);





CREATE TABLE PUBLIC.FILTROS (
	CAMPO VARCHAR(100)
);


CREATE TABLE PUBLIC.PAGINAS_PARSER (
	PAGINA VARCHAR(512) NOT NULL,
	PARSER VARCHAR(100) NOT NULL
);


