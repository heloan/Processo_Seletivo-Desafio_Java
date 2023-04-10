create database desafio_java;
use desafio_java;
use desafio_java;

create table sis_usuario(
	usuarioid int auto_increment not null,
    usuario varchar(50) not null,
    nome varchar(100) not null,
    senha varchar(100) not null,
    datacriacao datetime not null,
    dataalteracao datetime,
    PRIMARY KEY (usuarioid)
);

create table sis_grupo(
	grupoid int auto_increment not null,
    nome varchar(100) not null,
    datacriacao datetime not null,
    dataalteracao datetime,
    PRIMARY KEY (grupoid)
);

create table sis_grupo_usuario(
	grupoid int not null,
    usuarioid int not null
);

alter table sis_grupo_usuario add constraint fk_grupoid foreign key (grupoid) REFERENCES sis_grupo(grupoid);
alter table sis_grupo_usuario add constraint fk_usuarioid foreign key (usuarioid) REFERENCES sis_usuario(usuarioid);