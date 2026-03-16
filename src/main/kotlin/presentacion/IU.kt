package org.practicatrim2.presentacion

import org.practicatrim2.servicios.ReservaService
import org.slf4j.LoggerFactory
import java.time.LocalTime
import java.time.format.DateTimeParseException
val logger = LoggerFactory.getLogger("Presentacion")

/**
 * Capa de presentación. Gestiona la interacción con el usuario por consola.
 * Se comunica únicamente con ReservaService, sin conocer los detalles
 * de almacenamiento ni de creación de instancias del dominio.
 *
 * @param servicio Servicio de reservas inyectado por el constructor.
 */
class IU(private val servicio: ReservaService) {


    private val regexHora = Regex("""^([01]\d|2[0-3]):[0-5]\d$""")

    /**
     * Inicia el bucle principal de la aplicación.
     * El bucle se mantiene activo hasta que el usuario elige la opción de salir.
     */
    fun iniciar() {
        var salir = false
        while (!salir) {
            mostrarMenu()
            when (readlnOrNull()?.trim()) {
                "1" -> crearReserva()
                "2" -> listarReservas()
                "0" -> salir = true
                else -> logger.info("Opción no válida. Inténtalo de nuevo.")
            }
        }
    }

    /**
     * Muestra el menú principal en consola.
     */
    private fun mostrarMenu() {
        logger.info("""
            GESTOR DE RESERVAS
            Elige una opción:
            1. Crear nueva reserva
            2. Listar todas las reservas
            0. Salir
        """.trimIndent())
    }

    /**
     * Pregunta al usuario el tipo de reserva que desea crear
     * y delega en el método correspondiente.
     */
    private fun crearReserva() {
        logger.info("¿Qué tipo de reserva deseas crear?")
        logger.info("  1. Reserva de Vuelo")
        logger.info("  2. Reserva de Hotel")
        when (readlnOrNull()?.trim()) {
            "1" -> crearReservaVuelo()
            "2" -> crearReservaHotel()
            else -> logger.info("Tipo de reserva no válido.")
        }
    }

    /**
     * Solicita al usuario los datos necesarios para crear una reserva de vuelo
     * y delega la creación en el servicio.
     */
    private fun crearReservaVuelo() {
        logger.info("Nueva Reserva de Vuelo")
        val descripcion = pedirTextoNoVacio("Descripción: ")
        val origen = pedirTextoNoVacio("Origen: ")
        val destino = pedirTextoNoVacio("Destino: ")
        val horaVuelo = pedirHora()

        val reserva = servicio.crearReservaVuelo(descripcion, origen, destino, horaVuelo)
        logger.info("Reserva de vuelo creada:\n$reserva")
    }

    /**
     * Solicita al usuario los datos necesarios para crear una reserva de hotel
     * y delega la creación en el servicio.
     */
    private fun crearReservaHotel() {
        logger.info("Nueva Reserva de Hotel")
        val descripcion = pedirTextoNoVacio("Descripción: ")
        val ubicacion = pedirTextoNoVacio("Ubicación: ")
        val noches = pedirEnteroPositivo()

        val reserva = servicio.crearReservaHotel(descripcion, ubicacion, noches)
        logger.info("Reserva de hotel creada:\n$reserva")
    }

    /**
     * Lista todas las reservas almacenadas mostrando su detalle.
     * Hace uso del polimorfismo: cada reserva imprime su propio detalle.
     */
    private fun listarReservas() {
        val reservas = servicio.obtenerTodas()
        if (reservas.isEmpty()) {
            logger.info("No hay reservas registradas.")
        } else {
            logger.info("Listado de reservas (${reservas.size})")
            reservas.forEach { logger.info(it.detalle) }
        }
    }

    /**
     * Función genérica que solicita una entrada al usuario de forma repetida
     * hasta que la función [transform] devuelva un valor no nulo.
     *
     * Centraliza el patrón "pedir → validar → reintentar"
     *
     * @param mensaje   Texto que se muestra antes de leer la entrada.
     * @param mensajeError  Mensaje mostrado cuando la entrada no es válida.
     * @param transform Función de transformación/validación; devuelve null si la entrada no es válida.
     * @return El valor transformado una vez que la entrada sea válida.
     */
    private fun <T> pedirEntrada(mensaje: String, mensajeError: String, transform: (String) -> T?): T {
        var resultado: T? = null
        while (resultado == null) {
            print(mensaje)
            val entrada = readlnOrNull()?.trim() ?: ""
            resultado = transform(entrada)
            if (resultado == null) logger.warn(mensajeError)
        }
        return resultado
    }

    /**
     * Solicita un texto no vacío al usuario.
     */
    private fun pedirTextoNoVacio(mensaje: String): String =
        pedirEntrada(mensaje, "El valor no puede estar vacío.") {
            it.takeIf { v -> v.isNotBlank() }
        }

    /**
     * Solicita una hora en formato HH:mm validada mediante expresión regular.
     */
    private fun pedirHora(): LocalTime =
        pedirEntrada("Hora de vuelo (HH:mm): ", "Formato de hora incorrecto. Usa HH:mm (ej: 14:30)") { entrada ->
            entrada.takeIf { regexHora.matches(it) }?.let {
                try {
                    LocalTime.parse(it)
                } catch (e: DateTimeParseException) { null }
            }
        }

    /**
     * Solicita un número entero positivo al usuario.
     */
    private fun pedirEnteroPositivo(): Int =
        pedirEntrada("Número de noches: ", "Debe ser un número entero positivo.") {
            it.toIntOrNull()?.takeIf { n -> n > 0 }
        }
}