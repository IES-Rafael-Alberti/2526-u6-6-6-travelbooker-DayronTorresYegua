package org.practicatrim2.dominio

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservaVuelo private constructor(
    descripcion: String,
    val origen: String,
    val destino: String,
    val horaVuelo: String
    ) : Reserva(descripcion) {

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