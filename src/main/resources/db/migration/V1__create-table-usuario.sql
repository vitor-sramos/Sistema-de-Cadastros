create table tb_usuario(
	id binary(16),
    usuario varchar(100) not null unique,
    email varchar(100) not null,
    senha varchar(100) not null,
    primary key(id)
);