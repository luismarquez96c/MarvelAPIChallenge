# Documentación del Proyecto Marvel API

## Descripción
Este proyecto **tiene como objetivo mostrar cómo se evaluan las habilidades de un desarrollador en la implementación de APIs, el manejo de bases de datos y la autenticación de usuarios** y este proyecto es una aplicación que interactúa con la API de Marvel para obtener información sobre personajes y cómics. A continuación, se proporciona una descripción general de las principales componentes y funcionalidades del proyecto.

- Documento de prueba técnica: [click aquí](./src/main/resources/Prueba%20técnica%20para%20desarrollador%20backend%20v1%20-%20davivienda.docx)

## Características
El proyecto de prueba técnica para desarrollador backend consiste en el desarrollo de una API **con tecnología Java utilizando el framework Spring Boot**. Esta API tiene como objetivo consumir la API de Marvel, cuya documentación se encuentra en **[este enlace](https://developer.marvel.com/)**. A continuación, se detallan las principales características de este proyecto:

1. **Consumo de la API de Marvel**: La aplicación desarrollada se encarga de consumir la API de Marvel, obteniendo información relevante sobre personajes, cómics, series y más.

2. **Almacenamiento en Base de Datos**: Se implementa un esquema de base de datos, preferiblemente MySQL, para almacenar toda la información necesaria. Esto incluye datos relacionados con personajes, cómics y series.

3. **API de Suministro de Información**: Se crea una API que ofrece diversas funcionalidades para obtener información de Marvel, incluyendo:
    - Búsqueda de personajes de Marvel por nombre, historietas y series.
    - Obtención del listado de cómics asociados a un personaje específico.
    - Acceso a la imagen y descripción de un personaje en particular.
    - Listado completo de cómics disponibles.
    - Filtrado de cómics por identificador.
    - Visualización de búsquedas relacionadas a historietas realizadas por cualquier usuario.
    - Registro de búsquedas específicas de un usuario particular.

5. **Autenticación con Spring Security**: La API implementa un medio y esquema de autenticación utilizando Spring Security. Esto permite identificar qué usuario está realizando búsquedas de información de Marvel.

6. **Scripts de Base de Datos**: El archivo README de la aplicación incluye scripts de base de datos, que contienen datos iniciales necesarios para ejecutar las APIs en su primer uso.


## Configuración
Antes de ejecutar la aplicación, es necesario configurar las siguientes propiedades en el archivo `application.properties`:

```properties
integration.marvel.public-key=<Tu clave pública de Marvel>
integration.marvel.private-key=<Tu clave privada de Marvel>
```
Asegúrate de obtener estas claves de la API de Marvel ([click para obtener](https://developer.marvel.com/)) y reemplazar `<Tu clave pública de Marvel>` y `<Tu clave privada de Marvel>` con tus propias claves.


Además se debe de configurar en el archivo application-dev.properties las propiedades correspondientes para MySQL:
```properties
spring.datasource.url=jdbc:mysql://<Tu host>:<Tu puerto>/<Tu base de datos>
spring.datasource.username=<Tu username>
spring.datasource.password=<Tu password>
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
```

## Paquetes del Proyecto
El proyecto está organizado en los siguientes paquetes:

- `com.marvel.test.dto.response`: Contiene clases DTO para respuestas.
- `com.marvel.test.exception`: Incluye clases relacionadas con excepciones personalizadas.
- `com.marvel.test.mapper`: Contiene mapeadores para convertir entre entidades y DTO.
- `com.marvel.test.util`: Ofrece utilidades genéricas.
- `com.marvel.test.persistence.entity`: Define entidades de la base de datos.
- `com.marvel.test.persistence.integration.marvel`: Gestiona la integración con la API de Marvel.
- `com.marvel.test.persistence.integration.marvel.dto`: Define DTO específicos para la integración con Marvel.
- `com.marvel.test.persistence.integration.marvel.mapper`: Mapea objetos desde la API de Marvel a objetos locales.
- `com.marvel.test.persistence.integration.marvel.util`: Contiene utilidades para la integración con Marvel.
- `com.marvel.test.persistence.repository`: Define repositorios para acceder a la base de datos.
- `com.marvel.test.security`: Contiene configuraciones de seguridad y autenticación.
- `com.marvel.test.security.validator`: Contiene validadores de seguridad.
- `com.marvel.test.service`: Define interfaces de servicio.
- `com.marvel.test.service.impl`: Implementaciones de servicios.
- `com.marvel.test.web.controller`: Controladores web para manejar las solicitudes de la API.
- `com.marvel.test.web.filter`: Filtros para interceptar solicitudes web.
- `com.marvel.test.web.interceptor`: Interceptores de solicitudes web.

## Clases Principales
A continuación, se describen algunas de las clases principales del proyecto:

### `MarvelAPIConfig`
- Clase de configuración que se encarga de proporcionar la clave pública y privada de Marvel y gestionar la autenticación en las solicitudes a la API de Marvel.

### `ComicRepository`
- Repositorio que interactúa con la API de Marvel para obtener información sobre cómics. Proporciona métodos para buscar cómics relacionados con personajes y buscar cómics por ID.

### `CharacterRepository`
- Repositorio que se conecta a la API de Marvel para obtener información sobre personajes. Contiene métodos para buscar personajes por criterios y obtener información detallada de un personaje por ID.

### `JwtService`
- Servicio para la generación y validación de tokens JWT. Utilizado para la autenticación en la aplicación.

### `ComicService` y `CharacterService`
- Interfaces de servicio que definen operaciones relacionadas con cómics y personajes. Sus implementaciones se encuentran en `ComicServiceImpl` y `CharacterServiceImpl`, respectivamente.

### Controladores
- Los controladores, como `ComicController`, `CharacterController`, y `AuthenticationController`, gestionan las solicitudes HTTP y responden con datos a los clientes.

### Filtros e Interceptores
- Los filtros e interceptores, como `JwtAuthenticationFilter` y `UserInteractionInterceptor`, agregan funcionalidad de seguridad y registro a la aplicación.

### `UserInteractionLog` y Repositorio
- La entidad `UserInteractionLog` y su repositorio gestionan los registros de las interacciones de los usuarios con la aplicación.

## Manejo de Excepciones
El proyecto incluye un sistema de manejo de excepciones que garantiza una respuesta adecuada a diferentes tipos de errores, como errores de autenticación y errores en las solicitudes a la API de Marvel. El manejo de excepciones se realiza a través de la clase GlobalExceptionHandler.

## Scripts SQL
El proyecto incluye la configuración necesaria para que las tablas se creen automaticamente al ejecutar el proyecto.
Por defecto esta configuración se encuentra comentada pero basta con descomentar las siguientes propiedades para que se creen las tablas:

```properties
spring.jpa.hibernate.ddl-auto=update
```

También se puede ejecutar los scripts individualmente:
- Creación de tablas: [click](./src/main/resources/marvel_app.sql)
- Creación de registro (INSERTS): [click](./src/main/resources/data-mysql.sql)

## Spring Security: Usuarios y Roles
Los scripts incluyen la creación de dos roles y dos usuarios. Estos roles son **"CUSTOMER" y "AUDITOR"** y los dos usuarios son **"lmarquez" y "gcanas" con las contraseñas "contrasena123" y "contrasena456"** respectivamente.
Los permisos del role **"CUSTOMER"** son:
- character:read-all
- character:read-detail
- comic:read-all
- comic:read-by-id
- user-interaction:read-my-interactions

Los permisos del role **"AUDITOR"** son:
- character:read-all
- character:read-detail
- comic:read-all
- comic:read-by-id
- user-interaction:read-my-interactions
- user-interaction:read-all
- user-interaction:read-by-username

## Postman
Si se desea, se puede utilizar la siguiente colección para ser importada en postman y así tomar los test del autor como punto de partida. [Click aquí para ver colección de postman](./src/main/resources/Marvel%20Test.postman_collection.json)

## Resumen
Este documento proporciona una visión general de la estructura del proyecto, sus componentes clave y cómo configurar las claves de la API de Marvel. Asegúrate de configurar correctamente las claves antes de ejecutar la aplicación para que funcione correctamente.