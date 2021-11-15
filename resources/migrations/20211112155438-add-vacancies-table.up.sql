CREATE TABLE vacancies (
  id BIGSERIAL PRIMARY KEY,
  title character varying DEFAULT ''::character NOT NULL,
  description text DEFAULT ''::character NOT NULL,
  location character varying DEFAULT ''::character NOT NULL,
  salary_min integer DEFAULT 0 NOT NULL,
  salary_max integer default 0 not null,
  remote_available boolean DEFAULT false NOT NULL,
  moderated boolean DEFAULT false NOT NULL,
  expired_at timestamp without time zone NOT NULL,
  created_at timestamp without time zone NOT NULL,
  updated_at timestamp without time zone NOT NULL
);
