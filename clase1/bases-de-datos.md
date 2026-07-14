# Clase: Introducción a las Bases de Datos con PostgreSQL

---

# 1. ¿Qué es una Base de Datos?

Antes de aprender PostgreSQL debemos entender qué es una base de datos.

Una **Base de Datos (Database)** es un sistema organizado para almacenar información de forma estructurada, permitiendo que posteriormente esa información pueda consultarse, modificarse o eliminarse de manera rápida y segura.

## Una analogía: La biblioteca

Una buena analogía es pensar en una biblioteca.

-  Los libros representan los datos.
-  Los estantes representan la organización.
-  El catálogo permite encontrar rápidamente un libro.

Una base de datos funciona exactamente igual: organiza la información para que pueda encontrarse en cuestión de milisegundos.

---

# ¿Por qué no guardar todo en archivos?

Al principio del curso seguramente trabajamos con listas en memoria.

```java
List<Student> students = new ArrayList<>();
```

Incluso podríamos guardar los datos en un archivo.

```text
students.txt
```

Pero aparecen varios problemas.

---

## Problema 1: La información desaparece

Si cerramos la aplicación:

```text
Programa
    ↓
Memoria RAM
    ↓
Cerrar programa
    ↓
Todos los datos desaparecen
```

Una base de datos almacena la información en disco, por lo que permanece incluso cuando el programa termina.

---

## Problema 2: Buscar información es lento

Supongamos que tenemos **un millón de estudiantes**.

Si están en un archivo de texto habría que recorrer prácticamente todo el archivo para encontrar uno.

En una base de datos existen mecanismos especializados llamados **índices**, que permiten localizar información extremadamente rápido.

---

## Problema 3: Varias personas pueden acceder al mismo tiempo

Imaginemos una aplicación bancaria.

Miles de usuarios consultan su saldo simultáneamente.

Una base de datos fue diseñada precisamente para soportar múltiples usuarios trabajando al mismo tiempo sin corromper la información.

---

## Problema 4: Seguridad

No cualquier usuario debe modificar la información.

Por ejemplo:

- Un cajero puede consultar cuentas.
- Un administrador puede crear usuarios.
- Un cliente solo puede ver su propia información.

Las bases de datos permiten controlar exactamente quién puede realizar cada acción.

---

## Problema 5: Relaciones entre datos

Supongamos que tenemos estudiantes y cursos.

- Un estudiante puede estar inscrito en varios cursos.
- Un curso tiene muchos estudiantes.

La base de datos permite representar estas relaciones de forma eficiente.

---

# ¿Qué tipo de información almacena una base de datos?

Prácticamente cualquier información.

## Netflix

- Usuarios
- Películas
- Series
- Historial

## Banco

- Clientes
- Cuentas
- Transacciones
- Créditos

## Hospital

- Pacientes
- Médicos
- Citas
- Medicamentos

## Universidad

- Estudiantes
- Profesores
- Materias
- Notas

---

# Concepto importante

Una base de datos **no es un programa**.

Tampoco es una aplicación.

Una base de datos es simplemente un conjunto organizado de información.

Podemos imaginarla así:

```text
Base de Datos Universidad

├── Estudiantes
├── Profesores
├── Cursos
├── Matrículas
└── Calificaciones
```

Cada uno de esos elementos almacena datos relacionados entre sí.

---

# Ejemplo visual

Imaginemos la tabla de estudiantes.

| id | nombre | edad |
|----|--------|------|
| 1 | Ana | 20 |
| 2 | Luis | 19 |
| 3 | Carlos | 22 |

Esto ya constituye información organizada.

La base de datos será capaz de responder preguntas como:

- ¿Cuántos estudiantes existen?
- ¿Quién tiene 20 años?
- ¿Cuál es el estudiante con id 3?
- ¿Cuántos estudiantes tienen más de 18 años?

---

# ¿Cómo se comunica Java con una Base de Datos?

Hasta ahora hemos trabajado así:

```text
Controller
      ↓
Service
      ↓
Repository (simulado)
      ↓
List<Student>
```

A partir de este módulo cambiaremos el `Repository`.

```text
Controller
      ↓
Service
      ↓
Repository
      ↓
PostgreSQL
```

Nuestro código prácticamente no cambiará.

Lo que cambia es dónde se guardan los datos.

Antes:

```text
Memoria RAM
```

Ahora:

```text
PostgreSQL
```

Por eso Spring Boot resulta tan poderoso: **la arquitectura permanece igual**.

---

# 2. ¿Qué es un Motor de Base de Datos?

Ahora sabemos qué es una base de datos.

Pero surge una pregunta importante.

Si la base de datos solamente almacena información...

**¿Quién la administra?**

La respuesta es:

## El Motor de Base de Datos.

### Definición

Un **Motor de Base de Datos** (*Database Engine* o **DBMS - Database Management System**) es el software encargado de crear, almacenar, organizar y administrar una base de datos.

- La base de datos son los datos.
- El motor es quien trabaja con esos datos.

---

# Una analogía

Imaginemos un estacionamiento.

Los vehículos representan la información.

Pero alguien debe:

- abrir la puerta,
- asignar lugares,
- controlar quién entra,
- controlar quién sale,
- evitar accidentes.

Ese "administrador" sería el motor.

```text
Vehículos
      ↓
Administrador
      ↓
Estacionamiento organizado
```

En bases de datos ocurre exactamente igual.

---

# ¿Qué hace un motor?

Un motor de base de datos realiza muchas tareas.

## Crear bases de datos

```sql
CREATE DATABASE universidad;
```

## Crear tablas

```sql
CREATE TABLE estudiantes (...);
```

## Guardar información

```sql
INSERT INTO estudiantes ...
```

## Buscar información

```sql
SELECT * FROM estudiantes;
```

## Actualizar datos

```sql
UPDATE estudiantes ...
```

## Eliminar registros

```sql
DELETE FROM estudiantes ...
```

---

# Proteger la información

Controla permisos de usuarios.

Por ejemplo:

| Rol | Permisos |
|------|-----------|
| Administrador | ✔ Crear tablas |
| Profesor | ✔ Consultar notas |
| Estudiante | ✔ Ver únicamente sus propias notas |

---

# Evitar pérdida de datos

Si dos personas intentan modificar el mismo registro al mismo tiempo, el motor controla el acceso para mantener la información consistente.

---

# Optimizar consultas

No todas las consultas tardan lo mismo.

El motor decide la mejor forma de buscar la información utilizando:

- Índices
- Estadísticas
- Planes de ejecución

---

# Entonces...

Cuando escribimos algo como:

```sql
SELECT * FROM estudiantes;
```

No estamos hablando directamente con el archivo donde están los datos.

Estamos enviando una orden al motor.

```text
Java
   ↓
Spring Boot
   ↓
PostgreSQL
   ↓
Motor
   ↓
Archivos de la Base de Datos
```

El motor interpreta la instrucción, busca los datos y devuelve el resultado.

---

# Motores de Bases de Datos más conocidos

| Motor | Licencia | Muy utilizado en |
|--------|----------|------------------|
| PostgreSQL | Open Source | Empresas, banca, gobierno |
| MySQL | Open Source | Aplicaciones web |
| MariaDB | Open Source | Alternativa a MySQL |
| Oracle Database | Comercial | Grandes corporaciones |
| Microsoft SQL Server | Comercial | Empresas que usan tecnologías Microsoft |
| SQLite | Open Source | Aplicaciones móviles y de escritorio |

Todos ellos hablan SQL (con pequeñas variaciones), pero cada uno implementa características propias.

---

# ¿Por qué utilizaremos PostgreSQL?

Existen muchos motores, pero PostgreSQL destaca por varias razones:

- ✅ Es gratuito y de código abierto.
- ✅ Es uno de los motores más robustos del mercado.
- ✅ Cumple ampliamente con el estándar SQL.
- ✅ Maneja grandes volúmenes de información de forma eficiente.
- ✅ Soporta transacciones, índices, vistas, funciones, procedimientos almacenados y muchos otros mecanismos avanzados.
- ✅ Es ampliamente utilizado en empresas, universidades y proyectos de software profesionales.
- ✅ Se integra perfectamente con Spring Boot mediante Spring Data JPA.

---

Por estas características, **PostgreSQL** es una excelente opción para aprender bases de datos relacionales y desarrollar aplicaciones empresariales.

En las siguientes clases comenzaremos a instalar PostgreSQL, crear nuestras primeras bases de datos y ejecutar consultas SQL que posteriormente integraremos con aplicaciones desarrolladas en Spring Boot.

# 3. Instalación de PostgreSQL

La descarga oficial puede realizarse desde:

- PostgreSQL: https://www.postgresql.org/download/

---

## Puerto

Por defecto, PostgreSQL utiliza el puerto:

```text
5432
```

Más adelante este número aparecerá en nuestro proyecto Spring Boot.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/universidad
```

---

## Usuario administrador

Normalmente el instalador crea un usuario llamado:

```text
postgres
```

Este usuario será el administrador inicial del servidor.

---

## Contraseña

El instalador solicitará una contraseña para el usuario `postgres`.

Es importante conservarla, ya que será necesaria para conectarse desde **pgAdmin** y posteriormente desde **Spring Boot**.

---

## Stack Builder

En Windows, el instalador suele ofrecer la instalación de **Stack Builder**.

Para este curso **no será necesario instalar componentes adicionales**, por lo que puede omitirse este paso.

---

# ¿Qué es pgAdmin?

Aunque PostgreSQL puede administrarse completamente desde la consola, durante el curso utilizaremos una herramienta gráfica llamada **pgAdmin**.

pgAdmin es el cliente oficial para administrar servidores PostgreSQL mediante una interfaz gráfica. Permite crear bases de datos, tablas, ejecutar consultas SQL, gestionar usuarios y realizar respaldos sin necesidad de escribir todos los comandos manualmente.

## Descarga oficial

- pgAdmin: https://www.pgadmin.org/download/

---

## Documentación oficial

La documentación oficial puede consultarse en:

- https://www.pgadmin.org/docs/

---

# Primer inicio

Al abrir pgAdmin por primera vez normalmente veremos un árbol similar al siguiente:

```text
Servers
```

Todavía no existe ninguna conexión.

Debemos registrar nuestro servidor PostgreSQL.

---

# Registrar un servidor

En el panel izquierdo seleccionamos:

```text
Servers
    ↓
Register
    ↓
Server...
```

---

# Datos de conexión

Generalmente serán los siguientes:

| Campo | Valor |
|--------|--------|
| Host | localhost |
| Puerto | 5432 |
| Usuario | postgres |
| Contraseña | La definida durante la instalación |

Si los datos son correctos aparecerá nuestro servidor conectado.

---

# Conociendo la interfaz de pgAdmin

Una vez conectados observaremos varias secciones.

```text
Servers
│
└── PostgreSQL
      │
      ├── Databases
      ├── Login Roles
      ├── Tablespaces
      └── ...
```

Las más importantes durante el curso serán las siguientes.

---

## Servers

Representa el servidor PostgreSQL al que estamos conectados.

Un mismo pgAdmin puede administrar varios servidores al mismo tiempo.

---

## Databases

Aquí viven todas las bases de datos.

Por ejemplo:

```text
Databases

├── postgres
├── universidad
├── tienda
└── empresa
```

Cada base de datos es completamente independiente de las demás.

---

## Query Tool

Es probablemente la herramienta que más utilizaremos.

Permite escribir instrucciones SQL directamente.

Por ejemplo:

```sql
SELECT version();
```

Al ejecutar esta consulta, PostgreSQL responderá con la versión instalada.

---

## Dashboard

Muestra información del servidor, como por ejemplo:

- Consumo de CPU.
- Sesiones activas.
- Consultas ejecutadas.
- Conexiones.
- Estadísticas.

Resulta especialmente útil cuando se administran bases de datos reales.

---

## Object Browser

Es el panel izquierdo donde podremos navegar por prácticamente todos los objetos de la base de datos.

Entre ellos:

- Bases de datos.
- Esquemas.
- Tablas.
- Vistas.
- Funciones.
- Índices.
- Secuencias.

Prácticamente todo el trabajo del curso comenzará desde este panel.

---

# 4. Schemas y creación de tablas (Tipos de datos)

Cuando hablamos de una base de datos solemos imaginar únicamente tablas. Sin embargo, internamente PostgreSQL organiza todos esos objetos mediante una estructura llamada **Schema**.

Comprender este concepto es importante, ya que aparecerá constantemente al trabajar con PostgreSQL y con Spring Boot.

---

# ¿Qué es un Schema?

Un **Schema** es un espacio de nombres (*namespace*) dentro de una base de datos.

Su función es organizar los objetos que pertenecen a una misma aplicación o módulo.

Podemos imaginar una base de datos como un edificio.

```text
Base de Datos

├── Schema ventas
│      ├── clientes
│      ├── productos
│      └── facturas
│
├── Schema recursos_humanos
│      ├── empleados
│      ├── cargos
│      └── salarios
│
└── Schema inventario
       ├── bodegas
       ├── movimientos
       └── proveedores
```

Los **Schemas** permiten que dos tablas con el mismo nombre existan dentro de una misma base de datos, siempre que pertenezcan a esquemas distintos.

Por ejemplo:

```text
ventas.clientes

recursos_humanos.clientes
```

No existe conflicto porque pertenecen a espacios de nombres diferentes.

---

# El Schema `public`

Cuando instalamos PostgreSQL, todas las bases de datos incluyen automáticamente un esquema llamado:

```text
public
```

Si no indicamos un esquema específico, PostgreSQL crea todos los objetos dentro de `public`.

Durante este curso trabajaremos inicialmente sobre este esquema para simplificar los ejemplos.

---

# ¿Qué es una tabla?

Una tabla es una estructura formada por filas y columnas donde se almacena información de un mismo tipo.

Por ejemplo, una tabla de estudiantes:

| id | nombre | edad |
|----|--------|------|
| 1 | Ana | 20 |
| 2 | Luis | 18 |
| 3 | Carlos | 22 |

Cada fila representa un estudiante.

Cada columna representa una característica del estudiante.

---

# Tipos de datos en PostgreSQL

Antes de crear una tabla debemos decidir qué tipo de información almacenará cada columna.

Cada columna posee un tipo de dato.

Algunos de los más utilizados son:

| Tipo | Descripción | Ejemplo |
|-------|-------------|----------|
| INTEGER | Números enteros | 15 |
| BIGINT | Enteros grandes | 9876543210 |
| NUMERIC(p,s) | Números con precisión decimal | 1250.75 |
| BOOLEAN | Verdadero o falso | TRUE |
| VARCHAR(n) | Texto de longitud variable | "Carlos" |
| TEXT | Texto sin límite práctico | Descripciones largas |
| DATE | Fecha | 2026-07-14 |
| TIME | Hora | 15:30:00 |
| TIMESTAMP | Fecha y hora | 2026-07-14 15:30:00 |

La documentación oficial sobre los tipos de datos puede consultarse en:

https://www.postgresql.org/docs/current/datatype.html

---

# Crear una tabla

La sintaxis básica es la siguiente:

```sql
CREATE TABLE estudiantes (

    id INTEGER,

    nombre VARCHAR(100),

    edad INTEGER

);
```

Una vez ejecutado este comando aparecerá una nueva tabla dentro del esquema `public`.

---

# ¿Cómo crear una tabla desde pgAdmin?

1. Expandir la base de datos.
2. Expandir **Schemas**.
3. Expandir **public**.
4. Expandir **Tables**.
5. Clic derecho → **Create** → **Table...**

Aunque pgAdmin permite crear tablas gráficamente, durante el curso se recomienda crearlas mediante SQL para aprender el lenguaje estándar utilizado por todos los motores de bases de datos relacionales.

---

# Verificar la tabla

Después de crearla, el árbol de objetos mostrará una estructura similar a la siguiente:

```text
Schemas

└── public

      └── Tables

             └── estudiantes
```

Esto confirma que la tabla ha sido creada correctamente.

---

# Relación con Spring Boot

Más adelante, cuando definamos una entidad Java:

```java
@Entity
@Table(name = "estudiantes")
public class Student {
    ...
}
```

Hibernate utilizará esta información para relacionar la clase `Student` con la tabla `estudiantes` almacenada en PostgreSQL.

# 5. SQL y sus Sublenguajes

Hasta ahora hemos trabajado principalmente desde Java utilizando un repositorio simulado.

Surge entonces una pregunta importante:

**¿Cómo se comunica realmente una aplicación Java con un motor de base de datos?**

La respuesta es:

**Mediante SQL.**

---

# ¿Qué es SQL?

**SQL** significa **Structured Query Language** (Lenguaje de Consulta Estructurado).

Es el lenguaje estándar utilizado por prácticamente todos los motores de bases de datos relacionales.

Con SQL podemos:

- Crear bases de datos.
- Crear tablas.
- Insertar información.
- Consultar datos.
- Actualizar registros.
- Eliminar información.
- Administrar usuarios.
- Controlar transacciones.

En otras palabras, **SQL es el idioma con el que nos comunicamos con PostgreSQL**.

---

# Un ejemplo sencillo

Cuando escribimos la siguiente instrucción:

```sql
SELECT * FROM empleados;
```

Ocurre algo similar a una conversación.

Nosotros le decimos a PostgreSQL:

> "Por favor, muéstrame todos los empleados."

Y PostgreSQL responde devolviendo la información solicitada.

---

# SQL se divide en varios sublenguajes

Aunque normalmente hablamos simplemente de **SQL**, en realidad está compuesto por varios grupos de instrucciones, cada uno con una finalidad específica.

Los cuatro sublenguajes principales son:

```text
SQL
│
├── DDL
├── DML
├── DCL
└── TCL
```

Cada uno se utiliza para realizar un conjunto diferente de operaciones sobre la base de datos.

---

# DDL (Data Definition Language)

El **DDL** contiene las instrucciones que permiten definir la estructura de una base de datos.

Con DDL construimos el "esqueleto" de nuestra aplicación.

Las instrucciones más utilizadas son:

- `CREATE`
- `ALTER`
- `DROP`
- `TRUNCATE`

Durante esta clase utilizaremos principalmente **CREATE** y **DROP**.

---

# Crear una base de datos

Comenzaremos creando una base de datos llamada **empresa**.

```sql
CREATE DATABASE empresa;
```

Después de ejecutar esta sentencia debemos actualizar el panel de **pgAdmin** para verla aparecer.

---

# ¿Qué ocurrió?

PostgreSQL creó una nueva base de datos completamente vacía.

Todavía no contiene tablas.

```text
empresa

└── (vacía)
```

A partir de este momento podremos crear tablas, insertar información y ejecutar consultas dentro de ella.

---

# Crear una tabla

Ahora abrimos el **Query Tool** de la base de datos **empresa**.

Crearemos nuestra primera tabla.

```sql
CREATE TABLE departamentos (

    id SERIAL PRIMARY KEY,

    nombre VARCHAR(100) NOT NULL

);
```

Al ejecutar esta instrucción, PostgreSQL creará la tabla **departamentos** dentro del esquema correspondiente de la base de datos **empresa**.

---

# ¿Qué hace esta instrucción?

La sentencia anterior define una nueva tabla con dos columnas:

| Columna | Descripción |
|----------|-------------|
| `id` | Identificador único del departamento. Se genera automáticamente mediante el tipo `SERIAL` y actúa como llave primaria (`PRIMARY KEY`). |
| `nombre` | Nombre del departamento. Es un texto de hasta 100 caracteres y no puede quedar vacío (`NOT NULL`). |

# Segunda tabla

Ahora construiremos una segunda tabla que utilizaremos durante gran parte del curso.

```sql
CREATE TABLE empleados (

    id SERIAL PRIMARY KEY,

    nombre VARCHAR(80) NOT NULL,

    salario NUMERIC(10,2),

    fecha_ingreso DATE,

    departamento_id INTEGER

);
```

---

# ¿Por qué existe `departamento_id`?

Por ahora, **`departamento_id`** será simplemente una columna de tipo entero.

Más adelante la convertiremos en una **llave foránea (Foreign Key)** para establecer una relación entre las tablas `empleados` y `departamentos`.

Este enfoque nos permitirá comprender primero cómo crear tablas y almacenar información, antes de estudiar las relaciones entre ellas.

---

# Resultado

Después de crear ambas tablas, la estructura de la base de datos será similar a la siguiente:

```text
empresa
│
├── departamentos
└── empleados
```

---

# Eliminar una tabla

También es posible eliminar una tabla utilizando la instrucción `DROP TABLE`.

```sql
DROP TABLE empleados;
```

Después de ejecutar esta sentencia, la tabla desaparecerá completamente de la base de datos.

---

## Advertencia

La instrucción `DROP TABLE` elimina:

- La estructura de la tabla.
- Todos los registros almacenados en ella.

No existe una papelera de reciclaje.

Una vez ejecutada la instrucción, la información no podrá recuperarse a menos que exista un respaldo.

---

# Eliminar una base de datos

También es posible eliminar una base de datos completa.

```sql
DROP DATABASE empresa;
```

Esta sentencia elimina absolutamente todo lo que contiene la base de datos:

- Tablas.
- Datos.
- Vistas.
- Índices.
- Funciones.
- Procedimientos almacenados.

Debe utilizarse con extremo cuidado.

---

# DML (Data Manipulation Language)

Una vez creada la estructura de la base de datos, necesitamos comenzar a trabajar con la información.

Aquí entra en juego el **DML (Data Manipulation Language)**.

El DML permite:

- Insertar registros.
- Consultar información.
- Modificar datos.
- Eliminar registros.

Los comandos principales son:

- `INSERT`
- `SELECT`
- `UPDATE`
- `DELETE`

---

# INSERT

Comenzaremos agregando un departamento.

```sql
INSERT INTO departamentos (nombre)

VALUES ('Tecnología');
```

También podemos insertar varios registros en una sola instrucción.

```sql
INSERT INTO departamentos (nombre)

VALUES
('Recursos Humanos'),
('Contabilidad'),
('Ventas');
```

---

# ¿Por qué no escribimos el `id`?

La columna `id` fue definida como:

```sql
SERIAL
```

Esto significa que PostgreSQL genera automáticamente un identificador único para cada nuevo registro.

Los valores creados serán similares a:

```text
1
2
3
4
...
```

No es necesario asignarlos manualmente.

---

# Insertar empleados

Ahora agregaremos algunos empleados.

```sql
INSERT INTO empleados
(nombre, salario, fecha_ingreso, departamento_id)

VALUES

('Ana',3200,'2025-03-10',1),

('Luis',2800,'2024-08-15',1),

('Carlos',2500,'2026-01-20',2),

('María',4500,'2023-11-02',3),

('Pedro',3900,'2024-06-18',4);
```

Cada empleado queda asociado a un departamento mediante el campo `departamento_id`.

---

# SELECT

La instrucción `SELECT` permite consultar información almacenada en la base de datos.

---

## Consultar todos los departamentos

```sql
SELECT *

FROM departamentos;
```

Resultado aproximado:

| id | nombre |
|----|----------------------|
| 1 | Tecnología |
| 2 | Recursos Humanos |
| 3 | Contabilidad |
| 4 | Ventas |

---

## Consultar todos los empleados

```sql
SELECT *

FROM empleados;
```

Esta consulta devuelve todas las columnas y todos los registros de la tabla.

---

## Consultar únicamente algunos campos

```sql
SELECT

nombre,

salario

FROM empleados;
```

En este caso únicamente se mostrarán las columnas `nombre` y `salario`.

---

## Consultar utilizando una condición

```sql
SELECT *

FROM empleados

WHERE salario > 3000;
```

La cláusula `WHERE` permite filtrar los registros que cumplen una determinada condición.

En este ejemplo únicamente se mostrarán los empleados cuyo salario sea mayor a 3000.

---

## Consultar ordenando resultados

```sql
SELECT *

FROM empleados

ORDER BY salario DESC;
```

La cláusula `ORDER BY` organiza los resultados.

En este caso se ordenan por salario de mayor a menor (`DESC`).

---

# UPDATE

La instrucción `UPDATE` permite modificar información existente.

Por ejemplo:

```sql
UPDATE empleados

SET salario = 5000

WHERE id = 2;
```

Esta consulta actualiza el salario del empleado cuyo identificador es `2`.

---

## Advertencia importante

La cláusula `WHERE` es fundamental.

Si se omite:

```sql
UPDATE empleados

SET salario = 5000;
```

**Todos los registros de la tabla serán actualizados**, asignando el mismo salario a todos los empleados.

Antes de ejecutar un `UPDATE`, siempre debe verificarse que la cláusula `WHERE` seleccione únicamente los registros deseados.

---

# DELETE

La instrucción `DELETE` permite eliminar registros de una tabla.

Por ejemplo:

```sql
DELETE FROM empleados

WHERE id = 3;
```

Esta consulta elimina únicamente el empleado cuyo identificador es `3`.

---

## Advertencia importante

Al igual que ocurre con `UPDATE`, la cláusula `WHERE` es indispensable.

Si se ejecuta:

```sql
DELETE FROM empleados;
```

**Todos los registros de la tabla serán eliminados**, aunque la estructura de la tabla permanecerá intacta.

Por esta razón, antes de ejecutar un `DELETE`, siempre debe comprobarse que la condición especificada en `WHERE` sea la correcta.

---
