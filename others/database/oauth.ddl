--
--  Oauth sql  -- MYSQL
--

-- oauth2_registered_client  v2.1.1
-- from oauth2-registered-client-schema.sql
CREATE TABLE oauth2_registered_client (
  id varchar(100) NOT NULL,
  client_id varchar(100) NOT NULL,
  client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  client_secret varchar(200) DEFAULT NULL,
  client_secret_expires_at timestamp DEFAULT NULL,
  client_name varchar(200) NOT NULL,
  client_authentication_methods varchar(1000) NOT NULL,
  authorization_grant_types varchar(1000) NOT NULL,
  redirect_uris varchar(1000) DEFAULT NULL,
  scopes varchar(1000) NOT NULL,
  client_settings varchar(2000) NOT NULL,
  token_settings varchar(2000) NOT NULL,
  PRIMARY KEY (id)
);

-- oauth2_authorization  v2.1.1
-- from  oauth2-authorization-schema.sql
CREATE TABLE oauth2_authorization (
  id varchar(100) NOT NULL,
  registered_client_id varchar(100) NOT NULL,
  principal_name varchar(200) NOT NULL,
  authorization_grant_type varchar(100) NOT NULL,
  attributes varchar(4000) DEFAULT NULL,
  state varchar(500) DEFAULT NULL,
  authorization_code_value blob DEFAULT NULL,
  authorization_code_issued_at timestamp DEFAULT NULL,
  authorization_code_expires_at timestamp DEFAULT NULL,
  authorization_code_metadata varchar(2000) DEFAULT NULL,
  access_token_value blob DEFAULT NULL,
  access_token_issued_at timestamp DEFAULT NULL,
  access_token_expires_at timestamp DEFAULT NULL,
  access_token_metadata varchar(2000) DEFAULT NULL,
  access_token_type varchar(100) DEFAULT NULL,
  access_token_scopes varchar(1000) DEFAULT NULL,
  oidc_id_token_value blob DEFAULT NULL,
  oidc_id_token_issued_at timestamp DEFAULT NULL,
  oidc_id_token_expires_at timestamp DEFAULT NULL,
  oidc_id_token_metadata varchar(2000) DEFAULT NULL,
  refresh_token_value blob DEFAULT NULL,
  refresh_token_issued_at timestamp DEFAULT NULL,
  refresh_token_expires_at timestamp DEFAULT NULL,
  refresh_token_metadata varchar(2000) DEFAULT NULL,
  PRIMARY KEY (id)
);

--  oauth2_authorization_consent v2.1.1
-- from oauth2-authorization-consent-schema.sql
CREATE TABLE oauth2_authorization_consent (
  registered_client_id varchar(100) NOT NULL,
  principal_name varchar(200) NOT NULL,
  authorities varchar(1000) NOT NULL,
  PRIMARY KEY (registered_client_id, principal_name)
);


# Drop table  if exists oauth_client_details;
# create table oauth_client_details (
#   client_id VARCHAR(255) PRIMARY KEY,
#   resource_ids VARCHAR(255),
#   client_secret VARCHAR(255),
#   scope VARCHAR(255),
#   authorized_grant_types VARCHAR(255),
#   web_server_redirect_uri VARCHAR(255),
#   authorities VARCHAR(255),
#   access_token_validity INTEGER,
#   refresh_token_validity INTEGER,
#   additional_information TEXT,
#   create_time timestamp default now(),
#   archived tinyint(1) default '0',
#   trusted tinyint(1) default '0',
#   autoapprove VARCHAR (255) default 'false'
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
#
# Drop table  if exists oauth_access_token;
# create table oauth_access_token (
#   create_time timestamp default now(),
#   token_id VARCHAR(255),
#   token BLOB,
#   authentication_id VARCHAR(255) UNIQUE,
#   user_name VARCHAR(255),
#   client_id VARCHAR(255),
#   authentication BLOB,
#   refresh_token VARCHAR(255)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
#
# Drop table  if exists oauth_refresh_token;
# create table oauth_refresh_token (
#   create_time timestamp default now(),
#   token_id VARCHAR(255),
#   token BLOB,
#   authentication BLOB
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
#
# Drop table  if exists oauth_code;
# create table oauth_code (
#   create_time timestamp default now(),
#   code VARCHAR(255),
#   authentication BLOB
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
#
#
# -- Add indexes
# create index token_id_index on oauth_access_token (token_id);
# create index authentication_id_index on oauth_access_token (authentication_id);
# create index user_name_index on oauth_access_token (user_name);
# create index client_id_index on oauth_access_token (client_id);
# create index refresh_token_index on oauth_access_token (refresh_token);
#
# create index token_id_index on oauth_refresh_token (token_id);
#
# create index code_index on oauth_code (code);


