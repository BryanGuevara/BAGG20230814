CREATE DATABASE BAGGTareaDB;

Use BAGGTareaDB;

CREATE TABLE Tarea(
ID INT IDENTITY(1,1) PRIMARY KEY,
Nombre VARCHAR(50),
Descripcion VARCHAR(100),
Completada BIT,
FechaCreacion DATETIME
);

INSERT INTO Tarea (Nombre, Descripcion, Completada, FechaCreacion)
VALUES ('proyecto Java Web', 'Terminar el proyecto Java Web', 0, GETDATE());