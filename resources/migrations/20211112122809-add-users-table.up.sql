CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  first_name character varying DEFAULT ''::character NOT NULL,
  last_name character varying DEFAULT ''::character NOT NULL,
  email character varying NOT NULL,
  crypted_password character varying NOT NULL,
  admin BOOLEAN default false,
  last_login_at timestamp without time zone,
  is_enabled BOOLEAN default true,
  created_at timestamp without time zone NOT NULL,
  updated_at timestamp without time zone NOT NULL);
