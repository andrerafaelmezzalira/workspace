CREATE TABLE person
(
  id serial,
  name text NOT NULL,
  CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE TABLE workspace
(
  id serial,
  person_id integer NOT NULL,
  state text,
  city text,
  neighborhood text,
  address text,
  tableammount text,
  chairammount text,
  costperhour text,
  avaliableperiods text,
  avaliablehours text,
  description integer,
  CONSTRAINT pk_workspace PRIMARY KEY (id)
);

ALTER TABLE workspace ADD
  CONSTRAINT fk_workspace_person FOREIGN KEY (person_id)
      REFERENCES person(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;
