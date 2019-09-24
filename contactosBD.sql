create database Final
use Final

create table Contactos(
	id int not null primary key auto_increment,
    foto varchar(512),
    nombre varchar(25) not null,
    apellido varchar(25),
    compania varchar(30),
    posicion varchar(20),
    email varchar(50),
    telefono varchar(11) not null,
    notas varchar(100)
);
drop table Contactos
alter table Contactos modify column nombre varchar(25) not null

insert into Contactos(id, nombre, apellido, compania, posicion, email, telefono, notas)
 values(1, 'Julio', 'Putín', 'Claro', 'Conserje', 'vivaRusia@gmail.com','1029342823','Abajo América')
 
 select * from Contactos
 
 select max(id) as ultimo from Contactos
 
 update Contactos set foto='/home/ariel/Descargas/Usuario-Icono.jpg' where id=1
 
 insert into Contactos(id, nombre, telefono) values (23, 'Ariel', '8298988989')
 
 UPDATE Contactos SET nombre='Androide 18', apellido = 'Genos', compania='',posicion='',
	email='', telefono='123424', notas='' WHERE id=35

