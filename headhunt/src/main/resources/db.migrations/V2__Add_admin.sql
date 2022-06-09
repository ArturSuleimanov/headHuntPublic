insert into usr (id, username, firstname, lastname, password, active, email)
    values (1, 'admin', 'Admin', 'Admin', 'Frpk2017)!', true, 'assuleimanoff2017@gmail.com');

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');