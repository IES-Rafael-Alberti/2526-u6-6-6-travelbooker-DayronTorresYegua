package org.practicatrim2.dominio

class ReservaHotel private constructor(
    descripcion: String,
    val ubicacion: String,
    val numeroNoches: Int
    ) : Reserva(descripcion) {

    override val detalle: String
        get() = "ID: $id - Descripcion: $descripcion - Ubicacion: $ubicacion ($numeroNoches noches)"

    override fun toString(): String {
        return """Reserva de hotel:
            ID:               $id
            Fecha Creación:   $fechaCreacion
            Descripción:      $descripcion
            Ubicación:        $ubicacion
            Número de noches: $numeroNoches
            Detalle:          $detalle
        """
    }

    companion object {
        fun creaInstancia(
            descripcion: String,
            ubicacion: String,
            numeroNoches: Int
        ): ReservaHotel {
            return ReservaHotel(descripcion, ubicacion, numeroNoches)
        }
    }
}