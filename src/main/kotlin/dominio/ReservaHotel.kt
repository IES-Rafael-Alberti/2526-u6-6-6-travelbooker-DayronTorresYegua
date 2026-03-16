package org.practicatrim2.dominio

/**
 * Representa una reserva de hotel en la agencia de viajes.
 *
 * Hereda de [Reserva] y añade la información específica de un alojamiento:
 * la ubicación del hotel y el número de noches de la estancia.
 *
 * El constructor es privado para controlar la creación de instancias.
 * Debe usarse el método [creaInstancia] del companion object para instanciar esta clase.
 *
 * @param descripcion Descripción del servicio de hotel. No puede estar vacía.
 * @param ubicacion Localización del hotel.
 * @param numeroNoches Número de noches de la estancia. Debe ser un valor positivo.
 */
class ReservaHotel private constructor(
    descripcion: String,
    val ubicacion: String,
    val numeroNoches: Int
) : Reserva(descripcion) {

    /**
     * Detalle completo de la reserva de hotel, incluyendo el [id], la [descripcion],
     * la [ubicacion] y el [numeroNoches].
     */
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
        /**
         * Crea y devuelve una nueva instancia de [ReservaHotel].
         *
         * Es el único punto de entrada para crear reservas de hotel,
         * dado que el constructor es privado.
         *
         * @param descripcion Descripción del servicio de hotel. No puede estar vacía.
         * @param ubicacion Localización del hotel.
         * @param numeroNoches Número de noches de la estancia.
         * @return Nueva instancia de [ReservaHotel].
         */
        fun creaInstancia(
            descripcion: String,
            ubicacion: String,
            numeroNoches: Int
        ): ReservaHotel {
            return ReservaHotel(descripcion, ubicacion, numeroNoches)
        }
    }
}