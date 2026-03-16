package org.practicatrim2.dominio

import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Representa una reserva de vuelo en la agencia de viajes.
 *
 * Hereda de [Reserva] y añade la información específica de un vuelo:
 * el aeropuerto de origen, el de destino y la hora de salida.
 *
 * El constructor es privado para controlar la creación de instancias.
 * Debe usarse el método [creaInstancia] del companion object para instanciar esta clase.
 * La hora de vuelo se almacena internamente como [String] en formato HH:mm.
 *
 * @param descripcion Descripción del itinerario del vuelo. No puede estar vacía.
 * @param origen Ciudad o aeropuerto de origen.
 * @param destino Ciudad o aeropuerto de destino.
 * @param horaVuelo Hora de salida del vuelo en formato HH:mm.
 */
class ReservaVuelo private constructor(
    descripcion: String,
    val origen: String,
    val destino: String,
    val horaVuelo: String
) : Reserva(descripcion) {

    /**
     * Detalle completo de la reserva de vuelo, incluyendo el [id], la [descripcion],
     * el [origen], el [destino] y la [horaVuelo].
     */
    override val detalle: String
        get() = "ID: $id - Descripcion: $descripcion - Origen: $origen -> Destino: $destino - Hora: $horaVuelo"

    override fun toString(): String {
        return """Reserva de vuelo: 
            ID:             $id
            Fecha Creación: $fechaCreacion
            Descripción:    $descripcion
            Origen:         $origen
            Destino:        $destino
            Hora de Vuelo:  $horaVuelo
            Detalle:        $detalle
        """
    }

    companion object {
        private val formatterHora = DateTimeFormatter.ofPattern("HH:mm")

        /**
         * Crea y devuelve una nueva instancia de [ReservaVuelo].
         *
         * Es el único punto de entrada para crear reservas de vuelo,
         * dado que el constructor es privado.
         * Convierte internamente el [horaVuelo] de [LocalTime] a [String] en formato HH:mm.
         *
         * @param descripcion Descripción del itinerario del vuelo. No puede estar vacía.
         * @param origen Ciudad o aeropuerto de origen.
         * @param destino Ciudad o aeropuerto de destino.
         * @param horaVuelo Hora de salida del vuelo como [LocalTime].
         * @return Nueva instancia de [ReservaVuelo].
         */
        fun creaInstancia(
            descripcion: String,
            origen: String,
            destino: String,
            horaVuelo: LocalTime
        ): ReservaVuelo {
            return ReservaVuelo(descripcion, origen, destino, horaVuelo.format(formatterHora))
        }
    }
}