
-- need this to encode existing users passwords
create extension if not exists pgcrypto;

update usr set password = crypt(password, gen_salt('bf', 8));