/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  rossa
 * Created: 3/03/2019
 */

--Database: BaseDatos
--DROP DATABASE "basedatos"
--create database "basedatos"

drop schema if exists basedatos cascade;
create schema basedatos;

begin;

drop table if exists basedatos.administrador cascade;
create table basedatos.administrador(
	id serial primary key,
	idusuario text not null, 
	correo text not null unique,
	contrasena text not null, 
	nombre text not null, 
	apellidoP text not null,
	apellidoM text not null, 
        numTrabajador varchar(13) not null
);

commit;






