# Actividad: Desarrollo de Proyecto Software en Kotlin

**ID actividad:** 2425_PRO_u4u5u6_taskManager

**Agrupamiento de la actividad**: Individual 

---

### Descripción: **Ejercicio: Gestor de Reservas para una Agencia de Viajes**

En este ejercicio, crearás una plicación en la que aplicarás los conceptos de Programación Orientada a Objetos (POO) en Kotlin, incluyendo herencia, interfaces, clases abstractas, principios SOLID y arquitectura en capas. La aplicación simula un gestor de reservas para una agencia de viajes, permitiendo la creación y consulta de reservas de vuelo y de hotel. 

#### **Contexto y Objetivo**

Desarrolla una aplicación de consola en Kotlin que permita gestionar **reservas** en una agencia de viajes. Estas reservas se dividen en dos tipos: **Reserva de Vuelo** y **Reserva de Hotel**. Ambas derivan de una superclase o interfaz denominada **Reserva**.

La aplicación debe seguir una **arquitectura en capas**, separando claramente:
- **La capa de presentación (UI):** se encarga de la interacción con el usuario a través de la consola.
- **La capa de lógica de aplicación:** gestiona la lógica de negocio (creación, almacenamiento y manejo de reservas).
- **La capa de acceso a datos:** aunque en este ejercicio se puede utilizar un repositorio en memoria, se debe abstraer su acceso mediante interfaces, aplicando el principio de inversión de dependencias.

#### **Requerimientos Funcionales y No Funcionales**

1. **Arquitectura en Capas y Principio de Inversión de Dependencias**
  - La lógica de negocio debe depender de abstracciones (por ejemplo, interfaces de repositorios) y no de implementaciones concretas.
  - La comunicación entre la interfaz de usuario y la lógica de negocio debe estar claramente separada.

2. **Modelo de Dominio: Reserva, Reserva de Vuelo y Reserva de Hotel**
  - **Reserva (Superclase o Interfaz):**
    - Contendrá la lógica común a todas las reservas, aunque no se permitirá la creación de una instancia de la misma.
    - Posee un **id**. Se asigna automáticamente al crear la instancia. No puede ser nula y no se puede modificar.
    - Posee una **fechaCreacion**. Se asigna automáticamente al crear la instancia. No puede ser nula y no se puede modificar.
    - Posee una **descripcion**. No puede ser nula (por ejemplo, descripción del itinerario o servicio).
    - Debe incluir una propiedad `detalle`, cuyo getter utilice la lógica común para concatenar el *id* y la descripción.
  - **Reserva de Vuelo:**
    - Hereda las propiedades de Reserva.
    - Posee atributos propios: **origen**, **destino** y **horaVuelo**.
    - La propiedad **detalle** se genera dinámicamente, por ejemplo:  
      `id + " - " + descripcion + " - " + origen + " -> " + destino [horaVuelo]`.
    - Su constructor es **privado**. Se debe disponer de un método de clase (companion object) llamado `creaInstancia` para generar una nueva instancia.
    - Sobreescribe `toString` para mostrar formateada toda la información de la reserva de vuelo.
  - **Reserva de Hotel:**
    - Hereda las propiedades de Reserva.
    - Posee atributos propios: **ubicacion** y **numeroNoches**.
    - La propiedad **detalle** se genera dinámicamente, por ejemplo:  
      `id + " - " + descripcion + " - " + ubicacion (numeroNoches)`.
    - Al igual que Reserva de Vuelo, su constructor es privado y se debe utilizar el método `creaInstancia` para crear instancias.
    - Sobreescribe `toString` para mostrar formateada toda la información de la reserva de hotel.

3. **Buenas Prácticas y Principios SOLID**
  - Utiliza el principio de **inversión de dependencias**: la lógica de negocio no debe depender de clases concretas para el almacenamiento de las reservas.
  - Documenta y comenta el código de forma clara, explicando las decisiones de diseño, especialmente aquellas que no están explícitamente indicadas en la descripción.
  - Separa los métodos estáticos (en Kotlin se implementan mediante *companion objects*) y asegúrate de que la creación de instancias se haga mediante el método `creaInstancia`.
  - Integra el uso de **clases genéricas** (por ejemplo, en la implementación del repositorio) y **expresiones regulares** para validar ciertos formatos de entrada (por ejemplo, el formato de la hora en la Reserva de Vuelo).

4. **Interfaz de Usuario (Consola)**
  - La aplicación debe interactuar con el usuario a través de la consola, mostrando un menú que permita:
    - Crear una nueva reserva (seleccionando entre Reserva de Vuelo o Reserva de Hotel).
    - Listar todas las reservas registradas, mostrando el detalle de cada reserva mediante polimorfismo.
  - La capa de presentación debe comunicarse con la lógica de negocio a través de interfaces o abstracciones.

5. **Lógica de Aplicación**
  - Implementa un servicio (por ejemplo, `ReservaService`) que gestione la creación, almacenamiento (en memoria) y consulta de reservas.
  - Este servicio debe depender de una interfaz de repositorio (por ejemplo, `IReservaRepository`), permitiendo cambiar la implementación del almacenamiento sin afectar la lógica de negocio. Es decir, debéis aplicar el ppio DIP e inyectar en esta clase `ReservaService` un repositorio a través de la abstracción `IReservaRepository`.

#### **Objetivos del Ejercicio**

- Demostrar la comprensión de los fundamentos de la Programación Orientada a Objetos mediante la instanciación y uso de objetos.
- Aplicar conceptos avanzados de POO como herencia, clases abstractas e interfaces.
- Crear y utilizar clases que hagan uso de genéricos.
- Aplicar principios SOLID, especialmente la inversión de dependencias.
- Emplear expresiones regulares para la validación de entradas.
- Integrar y utilizar librerías externas para extender la funcionalidad del proyecto.
- Documentar y presentar el código de manera clara y comprensible.

#### **Trabajo a Realizar**

1. **Definición de Clases y Estructura del Proyecto**
  - Organiza el proyecto en paquetes (o módulos) que representen cada capa:
    - `presentacion` para la interfaz de usuario.
    - `servicios` (o `aplicacion`) para la lógica de negocio.
    - `datos` para la implementación del repositorio (en memoria).
    - `dominio` para definir las clases **Reserva**, **Reserva de Vuelo** y **Reserva de Hotel**.

2. **Implementación del Modelo de Dominio**
  - Define la superclase o interfaz `Reserva` que incluya:
    - Las propiedades comunes (`id`, `fechaCreacion`, `descripcion` y `detalle`).
    - La lógica compartida que consideres necesaria.
  - Implementa las clases `Reserva de Vuelo` y `Reserva de Hotel` siguiendo las especificaciones:
    - Campos inmutables (por ejemplo, `id` y `fechaCreacion` generados automáticamente).
    - Las propiedades específicas de cada una (`origen`, `destino`, `horaVuelo`) y (`ubicacion`, `numeroNoches`)
    - Constructores privados con el método `creaInstancia` en el *companion object*.
    - Propiedad `detalle` que concatene la información de forma dinámica.

3. **Desarrollo de la Lógica de Aplicación**
  - Implementa un servicio (`ReservaService`) que:
    - Utilice una interfaz de repositorio (`IReservaRepository`) para almacenar y recuperar reservas (`agregar` y `obtenerTodas`).
    - Permita la creación de nuevas reservas mediante métodos que invoquen `creaInstancia` de cada clase.
  - Aplica el principio de inversión de dependencias, de modo que el servicio dependa de la abstracción, no de una implementación concreta.

4. **Interfaz de Usuario (Consola)**
  - Desarrolla una interfaz de usuario en consola que muestre un menú con las siguientes opciones:
    - **Opción 1:** Crear nueva reserva (se debe preguntar al usuario si desea crear una Reserva de Vuelo o de Hotel, y solicitar los datos requeridos).
    - **Opción 2:** Listar todas las reservas registradas, mostrando el detalle (id y descripción) de cada reserva.
  - La capa de presentación debe comunicarse con el servicio para realizar las operaciones solicitadas.

5. **Documentación y Pruebas**
  - Comenta el código de forma clara, explicando las decisiones de diseño y la aplicación de los principios SOLID.
  - Realiza pruebas y depura la aplicación para asegurar su correcto funcionamiento.

### Recursos

- Apuntes dados en clase sobre programación orientada a objetos, Kotlin, uso de IDEs, y manejo de librerías.
- Recursos vistos en clase, incluyendo ejemplos de código, documentación de Kotlin, y guías de uso de librerías.

### Evaluación y calificación

**RA y CE evaluados**: Resultados de Aprendizaje 2, 4, 6, 7 y Criterios de Evaluación asociados.

**Conlleva presentación**: SI

**Rúbrica**: Más adelante se enviará o mostrará la rúbrica de esta práctica.

### Entrega

> **La entrega tiene que cumplir las condiciones de entrega para poder ser calificada. En caso de no cumplirlas podría calificarse como no entregada.**
>
- **Conlleva la entrega de URL a repositorio:** El contenido se entregará en un repositorio GitHub. 
- **Respuestas a las preguntas:** Deben contestarse, de manera clara y detallada en este fichero, README.md

    - Al final del documento, incluid un nuevo apartado, que se llame: "Entrega de la Práctica", dónde nos realicéis una pequeña introducción explicativa de vuestro tema, es decir, el problema que vais a solucionar y cómo lo habéis resuelto. Podéis incluir los subapartados que consideréis necesarios (estructura de carpetas, explicación y organización de clases, instrucciones de instalación, manual de usuario, ejemplos de funcionamiento, etc.)

    - **MUY IMPORTANTE!!** Incluir un subapartado ("Respuestas a las preguntas planteadas") dónde se resuelvan las preguntas de evaluación que os realizamos a continuación. De forma clara y detallada, incluyendo enlaces al código que justifica vuestra respuesta si es necesario.

# Preguntas para la Evaluación

Este conjunto de preguntas está diseñado para ayudarte a reflexionar sobre cómo has aplicado los criterios de evaluación en tu proyecto. Al responderlas, [**asegúrate de hacer referencia y enlazar al código relevante**](https://docs.github.com/es/get-started/writing-on-github/working-with-advanced-formatting/creating-a-permanent-link-to-a-code-snippet) en tu `README.md`, facilitando así la evaluación de tu trabajo.

#### **Criterio global 1: Instancia objetos y hacer uso de ellos**
- **(2.a, 2.b, 2.c, 2.d, 2.f, 2.h, 4.e, 4.f)**: Describe cómo has instanciado y utilizado objetos en tu proyecto. ¿Cómo has aplicado los constructores y pasado parámetros a los métodos? Proporciona ejemplos específicos de tu código.

He instanciado objetos usando el metodo crearInstancia que he definido en el companion object de cada clase.
He usado el constructor privado para que nadie pueda crear una reserva directamente con ReservaVuelo(), de forma que para crear una instacia siempre se tenga que usar el metodo creaInstancia. Los parámetros se pasan directamente al método y este se encarga de construir el objeto. En ReservaService es donde se llama a estos métodos pasándole los datos que el usuario ha introducido por consola.
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/servicios/ReservaService.kt#L20-L29

#### **Criterio global 2: Crear y llamar métodos estáticos**
- **(4.h)**: ¿Has definido algún método/propiedad estático en tu proyecto? ¿Cuál era el objetivo y por qué consideraste que debía ser estático en lugar de un método/propiedad de instancia?

He definido métodos estáticos usando companion object en las clases ReservaVuelo y ReservaHotel. El método se llama creaInstancia y su objetivo es controlar cómo se crean las instancias, ya que el constructor es privado. Lo hice estático porque no necesita acceder a ninguna instancia concreta para funcionar: simplemente recibe unos datos y devuelve un objeto nuevo. Si fuera un método de instancia no tendría sentido porque necesitarías ya tener un objeto para poder crear otro.
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/dominio/ReservaVuelo.kt#L47-L72

También he declarado como estáticos el contador y el formatter en Reserva, ya que el contador debe ser compartido entre todas las reservas para que los IDs sean únicos y correlativos, y el formatter no cambia entre instancias.
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/dominio/Reserva.kt#L36-L41

- **(2.e)**: ¿En qué parte del código se llama a un método estático o se utiliza la propiedad estática?

El método estático creaInstancia se llama desde ReservaService, en los métodos crearReservaVuelo y crearReservaHotel.
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/servicios/ReservaService.kt#L26
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/servicios/ReservaService.kt#L39

#### **Criterio global 3: Uso de entornos**
- **(2.i)**: ¿Cómo utilizaste el IDE para el desarrollo de tu proyecto? Describe el proceso de creación, compilación, y prueba de tu programa.

El proceso ha sido el siguiente: primero creé el proyecto con Gradle usando la plantilla de Kotlin JVM, lo que generó automáticamente los archivos build.gradle.kts y la estructura de carpetas. Después fui creando los paquetes y las clases una a una, empezando por el dominio y terminando por la presentación. Para compilar usé el botón de build del propio IDE. Para probar la aplicación la ejecuté directamente desde el Main.kt pulsando el botón de play que aparece junto a la función main. El IDE también me ayudó a detectar errores en tiempo real subrayando en rojo las líneas con problemas antes de compilar.

#### **Criterio global 4: Definir clases y su contenido**
- **(4.a, 4.b, 4.c, 4.d, 4.g)**: Explica sobre un ejemplo de tu código, cómo definiste las clases en tu proyecto, es decir como identificaste las de propiedades, métodos y constructores y modificadores del control de acceso a métodos y propiedades, para representar al objeto del mundo real. ¿Cómo contribuyen estas clases a la solución del problema que tu aplicación aborda?

Voy a explicarlo con el ejemplo de ReservaHotel. Esta clase representa una reserva de hotel del mundo real, que tiene una ubicación y un número de noches. Para definirla pensé en qué información necesita guardar (propiedades), qué puede hacer (métodos) y quién puede acceder a qué (modificadores de acceso).

Las propiedades ubicacion y numeroNoches son val porque una vez hecha la reserva no tiene sentido cambiarlas. El constructor es private para que nadie pueda crear una instancia directamente desde fuera. El método toString es público porque lo necesita cualquiera que quiera mostrar la reserva. La propiedad detalle sobreescribe la de la clase padre para añadir la información específica del hotel.

Esta clase contribuye a la solución porque permite representar de forma clara y organizada una reserva de hotel, separando sus datos de la lógica de negocio y de la interfaz.
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/dominio/ReservaHotel.kt#L16-L38

#### **Criterio global 5: Herencia y uso de clases abstractas e interfaces**
- **(4.g, 7.a, 7.b, 7.c, 7.i, 7.j)**: Describe sobre tu código cómo has implementado la herencia y/o utilizado interfaces en tu proyecto. ¿Por qué elegiste este enfoque y cómo beneficia a la estructura de tu aplicación? ¿De qué manera has utilizado los principios SOLID para mejorar el diseño de tu proyecto? Mostrando tu código, contesta qué principios has utilizado y qué beneficio has obtenido.

He implementado herencia usando una clase abstracta Reserva como superclase. Elegí clase abstracta en lugar de interfaz porque Reserva tiene propiedades con estado (id, fechaCreacion) y lógica en el init, cosas que una interfaz no puede tener. Tanto ReservaVuelo como ReservaHotel heredan de ella con : Reserva(descripcion).

Los principios SOLID que he aplicado son:

**SRP (Responsabilidad Única):** cada clase tiene una sola responsabilidad. IU solo gestiona la consola, ReservaService solo gestiona la lógica de negocio, ReservaRepository solo gestiona el almacenamiento.

**OCP (Abierto/Cerrado):** si quisiera añadir un nuevo tipo de reserva como ReservaExcursion solo tendría que crear una nueva clase que herede de Reserva, sin tocar el código existente.

**DIP (Inversión de Dependencias):** ReservaService no depende de ReservaRepository directamente, sino de la interfaz IReservaRepository. Esto significa que podría cambiar el almacenamiento a una base de datos sin tocar el servicio.
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/datos/IReservaRepository.kt#L5-L8
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/servicios/ReservaService.kt#L15

#### **Criterio global 6: Diseño de jerarquía de clases**
- **(7.d, 7.e, 7.f, 7.g)**: Presenta la jerarquía de clases que diseñaste. ¿Cómo probaste y depuraste esta jerarquía para asegurar su correcto funcionamiento? ¿Qué tipo de herencia has utilizado: Especificación, Especialización, Extensión, Construcción?

La jerarquía es la siguiente:

Reserva (abstract)
├── ReservaVuelo
└── ReservaHotel

El tipo de herencia que he utilizado es Especialización: las subclases añaden propiedades y comportamiento específico que la superclase no tiene (origen, destino, horaVuelo en el caso del vuelo; ubicacion, numeroNoches en el hotel). La superclase define el contrato común (id, fecha, descripción, detalle) y cada subclase lo especializa.
Para depurarla ejecuté la aplicación y comprobé que al crear una reserva de vuelo y listarla después el detalle mostraba correctamente todos los campos específicos del vuelo, y lo mismo con el hotel. También verifiqué que no se podía instanciar Reserva directamente.

#### **Criterio global 7: Librerías de clases**
- **(2.g, 4.i)**: Describe cualquier librería externa que hayas incorporado en tu proyecto. Explica cómo y por qué las elegiste, y cómo las incorporaste en tu proyecto. ¿Cómo extendió la funcionalidad de tu aplicación? Proporciona ejemplos específicos de su uso en tu proyecto.

He incorporado dos librerías externas, ambas añadidas en el build.gradle.kts:
SLF4J + Logback: es un sistema de logging para Java/Kotlin. La elegí porque es el estándar más usado y permite controlar el nivel de los mensajes (INFO, WARN, ERROR) y guardarlos en un archivo de log automáticamente. Sin ella tendría que usar println para todo, sin poder diferenciar entre mensajes informativos y errores.

https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/presentacion/IU.kt#L7
https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/build.gradle.kts#L14-L15

#### **Criterio global 8: Documentado**
- **(7.h)**: Muestra ejemplos de cómo has documentado y comentado tu código. ¿Que herramientas has utilizado? ¿Cómo aseguras que tu documentación aporte valor para la comprensión, mantenimiento y depuración del código?

He documentado el código usando KDoc, que es el sistema de documentación estándar de Kotlin. Cada clase tiene un comentario explicando su propósito y cada función tiene comentarios con @param y @return cuando es necesario. He usado IntelliJ IDEA para escribir la documentación, que ayuda autocompletando las etiquetas. La documentación aporta valor porque cualquier persona que lea el código entiende qué hace cada clase y método sin tener que leer toda la implementación.

https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/presentacion/IU.kt#L109-L119

#### **Criterio global 9: Genéricos**
- **(6.f)**: Muestra ejemplos de tu código sobre cómo has implementado una clase con genéricos. ¿Qué beneficio has obtenido?

He usado genéricos en la función pedirEntrada de IU.kt. Al escribir fun <T> pedirEntrada() le digo a Kotlin que esta función puede trabajar con cualquier tipo de dato, ya sea String, LocalTime, Int o cualquier otro. El beneficio es que no necesito escribir una función distinta para cada tipo de dato que quiera pedir al usuario: con una sola función genérica cubro todos los casos.

https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/presentacion/IU.kt#L120-L128

#### **Criterio global 10: Expresiones Regulares**
- **(6.g)**: Muestra ejemplos de tu código donde hayas utilizado las expresiones regulares. ¿Qué beneficio has obtenido?

He usado expresiones regulares en IU.kt para validar que la hora que introduce el usuario tenga el formato correcto HH:mm. La regex es:

https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/presentacion/IU.kt#L19

Esta expresión comprueba que la hora sea un número entre 00 y 23, seguido de dos puntos, seguido de minutos entre 00 y 59. Si el usuario escribe algo como 25:70 o abc, la regex lo rechaza y se le vuelve a pedir. El beneficio es que no tengo que escribir manualmente todas las comprobaciones numéricas: con una sola línea valido el formato completo.

https://github.com/IES-Rafael-Alberti/2526-u6-6-6-travelbooker-DayronTorresYegua/blob/e7a560b83d6142fc6b2d9cf9b24f2b337f89069d/src/main/kotlin/presentacion/IU.kt#L142-L149
