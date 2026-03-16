package org.practicatrim2.datos

import org.practicatrim2.dominio.Reserva

interface IReservaRepository {
    fun agregar(reserva: Reserva)
    fun obtenerTodas(): List<Reserva>
}