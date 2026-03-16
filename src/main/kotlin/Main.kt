package org.practicatrim2

import org.practicatrim2.dominio.*
import org.slf4j.LoggerFactory
import java.time.LocalTime

val logger = LoggerFactory.getLogger("Travel booker")


fun main() {

    val vuelo = ReservaVuelo.creaInstancia("Vuelo a Madrid", "Madrid", "Barcelona", LocalTime.of(10, 0))
    val hotel = ReservaHotel.creaInstancia("Hotel en madrid", "Madrid", 4)

    logger.info(vuelo.toString())
    logger.info(hotel.toString())

}