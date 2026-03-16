package org.practicatrim2.servicios

import org.practicatrim2.datos.IReservaRepository
import org.practicatrim2.dominio.Reserva
import org.practicatrim2.dominio.ReservaHotel
import org.practicatrim2.dominio.ReservaVuelo
import java.time.LocalTime

/**
 * Servicio que gestiona la lógica de negocio de las reservas.
 * Depende de la abstracción IReservaRepository (principio DIP).
 *
 * @param repositorio Repositorio inyectado por el constructor.
 */
class ReservaService(private val repositorio: IReservaRepository) {

    /**
     * Crea una reserva de vuelo y la almacena en el repositorio.
     */
    fun crearReservaVuelo(
        descripcion: String,
        origen: String,
        destino: String,
        horaVuelo: LocalTime
    ): ReservaVuelo {
        val reserva = ReservaVuelo.creaInstancia(descripcion, origen, destino, horaVuelo)
        repositorio.agregar(reserva)
        return reserva
    }

    /**
     * Crea una reserva de hotel y la almacena en el repositorio.
     */
    fun crearReservaHotel(
        descripcion: String,
        ubicacion: String,
        numeroNoches: Int
    ): ReservaHotel {
        val reserva = ReservaHotel.creaInstancia(descripcion, ubicacion, numeroNoches)
        repositorio.agregar(reserva)
        return reserva
    }

    /**
     * Devuelve todas las reservas almacenadas.
     */
    fun obtenerTodas(): List<Reserva> {
        return repositorio.obtenerTodas()
    }
}