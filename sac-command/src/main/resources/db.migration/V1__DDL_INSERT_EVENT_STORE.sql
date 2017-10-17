CREATE TABLE public.event_store
(
  id bigint NOT NULL DEFAULT nextval('event_store_id_seq'::regclass),
  aggregate character varying(255),
  aggregate_id character varying(255) NOT NULL,
  created_at timestamp without time zone,
  event_id character varying(255) NOT NULL,
  event_type character varying(255) NOT NULL,
  event jsonb NOT NULL,
  CONSTRAINT event_store_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.event_store
  OWNER TO postgres;

CREATE INDEX aggregateindex
  ON public.event_store
  USING btree
  (aggregate COLLATE pg_catalog."default", aggregate_id COLLATE pg_catalog."default", created_at);