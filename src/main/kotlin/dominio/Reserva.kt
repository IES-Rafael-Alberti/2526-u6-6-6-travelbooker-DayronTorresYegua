package org.practicatrim2.dominio

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

abstract class Reserva(var descripcion: String) {

    val id: Int
    val fechaCreacion: String

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