-- Role table
CREATE TABLE role
(
  id bigserial NOT NULL,
  name character varying(100) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id),
  CONSTRAINT role_name_key UNIQUE (name)
)
WITH (
  OIDS=FALSE
);

-- Account table
CREATE TABLE account
(
  id bigserial NOT NULL,
  login character varying(100) NOT NULL,
  email character varying(100) NOT NULL,
  password character varying(100) NOT NULL,
  active boolean NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (id),
  CONSTRAINT account_login_key UNIQUE (login)
)
WITH (
  OIDS=FALSE
);

-- AccountRole table
CREATE TABLE account_role
(
  id bigserial NOT NULL,
  account_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT account_role_pkey PRIMARY KEY (id),
  CONSTRAINT account_role_accountrole_key UNIQUE (account_id, role_id),
  CONSTRAINT fk_account_role_accounts FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT fk_account_roles FOREIGN KEY (role_id) REFERENCES role (id)
)
WITH (
  OIDS=FALSE
);

