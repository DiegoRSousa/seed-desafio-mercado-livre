insert into usuario (login, senha) values ('usr@mail.com', '$2a$10$nfmXr/Rb8MPFSeft9tuNlu24e2sWceyT04INPLxk2PVOT54BszOD2');
insert into usuario (login, senha) values ('diego@mail.com', '$2a$10$nfmXr/Rb8MPFSeft9tuNlu24e2sWceyT04INPLxk2PVOT54BszOD2');
insert into categoria (nome) values ('Teste');

insert into caracteristica (nome, descricao) values ('Tipo de switch', 'RED');
insert into caracteristica (nome, descricao) values ('Idioma', 'English US');
insert into caracteristica (nome, descricao) values ('Possui retroiluminação', 'Não');

insert into produto (nome, valor, quantidade_disponivel, descricao, categoria_id, usuario_id) 
values ('Teclado mecânico logitech', 1000, 4, 
'O teclado mecânico gamer Logitech traz todo poder dos teclados mecânicos para seu setup', 1, 1);

insert into produto_caracteristicas (produto_id, caracteristicas_id) values (1, 1), (1, 2), (1, 3) 