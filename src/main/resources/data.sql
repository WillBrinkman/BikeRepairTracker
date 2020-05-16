INSERT INTO users (username, password, enabled)
  values ('user',
    'password',
    1);

INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');