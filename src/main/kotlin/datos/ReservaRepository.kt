package org.practicatrim2.datos

import org.practicatrim2.dominio.Reserva

/**
 * Implementación en memoria del repositorio de reservas.
 * Almacena las reservas en una lista mutable interna.
 */
class ReservaRepository : IReservaRepository {

    private val reservas: MutableList<Reserva> = mutableListOf()

    override fun agregar(reserva: Reserva) {
        reservas.add(reserva)
    }

    override fun obtenerTodas(): List<Reserva> {
        return reservas.toList()
    }
}