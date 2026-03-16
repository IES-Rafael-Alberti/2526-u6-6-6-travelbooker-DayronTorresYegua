package org.practicatrim2

import org.practicatrim2.datos.ReservaRepository
import org.practicatrim2.presentacion.IU
import org.practicatrim2.servicios.ReservaService

fun main() {

    val repositorio = ReservaRepository()
    val servicio = ReservaService(repositorio)
    val ui = IU(servicio)

    ui.iniciar()
}