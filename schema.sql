create database filtro;
use filtro;


CREATE TABLE tienda(
id_tienda int primary key auto_increment,
nombre Varchar(255),
ubicacion varchar(255)
);

CREATE TABLE producto(
id_producto int primary key auto_increment,
nombre varchar(255),
precio double,
id_tienda int,
CONSTRAINT fk_id_tienda FOREIGN KEY (id_tienda) REFERENCES tienda(id_tienda)
);


CREATE TABLE cliente(
id_cliente int primary key auto_increment,
nombre varchar(255),
apellido varchar(255),
email varchar(255)
);

CREATE TABLE compra(
id_compra int primary key auto_increment,
id_cliente int,
id_producto int,
fecha_compra timestamp,
cantidad int,
CONSTRAINT fk_id_cliente  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE,
CONSTRAINT fk_id_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE CASCADE
);


ALTER TABLE producto ADD COLUMN stock int;

INSERT INTO tienda (nombre, ubicacion) VALUES
("Nike", "Arkadia Cra. 70 #1-141"),
("Adidas" ,"Arkadia Cra. 70 #1-141"),
("Reebok" ,"Outlets industriales");


INSERT INTO producto (nombre,precio,id_tienda) VALUES ("zapatos nike", 50000, 1);


SELECT producto.* FROM tienda INNER JOIN producto ON tienda.id_tienda = producto.id_tienda WHERE tienda.id_tienda = 1;

SELECT producto.nombre as nombre_producto, producto.precio, producto.id_producto,
cliente.nombre as cliente_nombre , cliente.apellido, cliente.email, cliente.id_cliente,
tienda.nombre as tienda_nombre, tienda.ubicacion, tienda.id_tienda, compra.cantidad, compra.id_compra From compra
INNER JOIN producto ON producto.id_producto = compra.id_producto
INNER JOIN tienda ON tienda.id_tienda = producto.id_tienda
INNER JOIN cliente ON cliente.id_cliente = compra.id_cliente WHERE producto.id_producto = 3 and cliente.id_cliente = 2;

alter table compra modify column fecha_compra timestamp default current_timestamp;