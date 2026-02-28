# Taller Intro Spring — Gestión de Discografía

Aplicación web Java desarrollada con **Spring Framework** y **Jakarta Servlets** como parte del taller introductorio de Spring en el curso de Computación en Red II.

## Integrantes

- Juan Sebastián Poveda
- Santiago Estrada
- Juan Manuel Ramirez

---

## Descripción

El sistema permite gestionar una discografía musical, con dos entidades principales:

- **Artist**: representa a un artista musical con `id`, `name` y `nationality`.
- **Track**: representa una canción con `id`, `title`, `genre`, `duration` y `albumTitle`.

La relación entre ambas entidades es **many-to-many**: un artista puede tener múltiples canciones y una canción puede tener múltiples artistas.

Al iniciar la aplicación, el sistema precarga automáticamente **10 artistas** y **50 canciones**, asignando 5 canciones a cada artista.

---

## Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Context | 6.2.2 |
| Jakarta Servlet API | 6.1.0 |
| Maven | - |
| Empaquetado | WAR |

---

## Estructura del proyecto

```
src/
└── main/
    ├── java/org/example/
    │   ├── config/         # Configuración del contexto Spring
    │   ├── context/        # AppContext: punto de acceso al ApplicationContext
    │   ├── entity/         # Modelos: Artist, Track
    │   ├── repository/     # Interfaces e implementaciones de repositorios
    │   ├── service/        # Interfaces e implementaciones de servicios
    │   └── servlet/        # ArtistServlet, TrackServlet
    └── resources/
        ├── artists.html
        └── tracks.html
```

---

## Funcionalidades

### `/artists` — Gestión de artistas

| Operación | Método HTTP | Descripción |
|---|---|---|
| Listar todos los artistas | GET | Muestra la lista completa de artistas registrados |
| Crear artista | POST | Registra un nuevo artista con `id`, `name` y `nationality` |
| Buscar artista por nombre | POST `/artists/search` | Muestra los datos del artista y todas sus canciones |
| Eliminar artista por ID | POST `/artists/delete` | Elimina un artista dado su `id` |

### `/tracks` — Gestión de canciones

| Operación | Método HTTP | Descripción |
|---|---|---|
| Listar todas las canciones | GET | Muestra todas las canciones con su información y artistas asociados |
| Crear canción | POST | Registra una nueva canción; permite asignar uno o varios artistas por nombre, separados por coma |
| Eliminar canción por ID | POST `/tracks/delete` | Elimina una canción dado su `id` |

---

## Branches y métodos de inicialización de beans

El proyecto cuenta con **tres branches**, cada una demostrando un enfoque distinto para la configuración e inyección de dependencias con Spring:

| Branch | Método de inicialización |
|---|---|
| `main` | **Mix de Configuration + Annotations**: clase `@Configuration` con `@ComponentScan` para que Spring detecte automáticamente los beans anotados con `@Service` y `@Repository` |
| `xml-config` | **XML puro**: todos los beans se declaran manualmente en `applicationContext.xml`, sin ninguna anotación de Spring en las clases |
| `java-config` | **Java Configuration puro**: todos los beans se declaran manualmente con métodos `@Bean` dentro de `AppConfig.java`, sin `@ComponentScan` ni anotaciones en las clases |

---

## Nota importante — Fallo en el primer intento de eliminar artista

Debido a que la inicialización de la aplicación (carga de los 10 artistas y 50 canciones) puede ser tardada al momento en que el servidor registra los servlets, **el formulario de eliminación de artistas puede fallar en el primer intento**.

Si esto ocurre, basta con **intentar la operación por segunda vez** y funcionará correctamente, ya que en ese punto el contexto de Spring ya habrá terminado de inicializarse por completo.

---

## Cómo ejecutar

1. Compilar el proyecto con Maven:
   ```bash
   mvn clean package
   ```
2. Desplegar el archivo `target/Taller_IntroSpring-1.0-SNAPSHOT.war` en un servidor compatible con Jakarta Servlet (por ejemplo, **Apache Tomcat 10+**).
3. Acceder desde el navegador:
   - `http://localhost:8080/Taller_IntroSpring_war/artists`
   - `http://localhost:8080/Taller_IntroSpring_war/tracks`

