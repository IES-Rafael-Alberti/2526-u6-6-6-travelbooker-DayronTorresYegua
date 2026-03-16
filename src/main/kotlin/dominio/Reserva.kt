package org.practicatrim2.dominio

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Clase abstracta que representa una reserva genérica en la agencia de viajes.
 *
 * Define las propiedades y comportamiento común a todos los tipos de reserva.
 * No puede instanciarse directamente; debe heredarse para crear tipos concretos
 * como [ReservaVuelo] o [ReservaHotel].
 *
 * El [id] y la [fechaCreacion] se asignan automáticamente en el momento de creación
 * usando un contador estático compartido entre todas las instancias.
 *
 * @param descripcion Descripción del itinerario o servicio reservado. No puede estar vacía.
 */
abstract class Reserva(val descripcion: String) {

    val id: Int
    val fechaCreacion: String

    /**
     * Detalle resumido de la reserva que concatena el [id] y la [descripcion].
     * Las subclases deben sobreescribir esta propiedad para incluir su información específica.
     */
    open val detalle: String
        get() = "[$id] $descripcion"

    init {
        require(descripcion.isNotBlank()) { "La descripción no puede ser nula o vacía." }
        id = ++contador
        fechaCreacion = LocalDateTime.now().format(formatter)
    }

    companion object {
        private var contador: Int = 0

        private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }
}