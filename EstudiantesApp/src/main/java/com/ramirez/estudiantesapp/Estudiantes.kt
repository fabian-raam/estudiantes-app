package com.ramirez.estudiantesapp

fun main() {
    // PARTE 1: CREACIÓN DE INPUTS

    print("Ingrese nombre del estudiante: ")
    val nombreEstudiante = readLine() ?: return

    print("Ingrese cantidad de cursos: ")
    val cantidadCursos = readLine()?.toInt() ?: return

    print("Ingrese valor de un crédito: ")
    val valorCredito = readLine()?.toDouble() ?: return

    // Listas para almacenar nombres de cursos y créditos
    val nombresCursos = mutableListOf<String>()
    val creditosCursos = mutableListOf<Double>()

    // Leer datos de cada curso
    for (i in 0..cantidadCursos - 1) {
        print("Nombre del curso ${i + 1}: ")
        nombresCursos.add(readLine() ?: "")

        print("¿Cuántos créditos tiene ${nombresCursos[i]}? ")
        val creditos = readLine()?.toDouble() ?: 0.0
        creditosCursos.add(creditos)
    }

    // PARTE 2: CREACIÓN DE CÁLCULOS

    // Calcular total de créditos
    var totalCreditos = 0.0
    for (i in 0..cantidadCursos - 1) {
        totalCreditos += creditosCursos[i]
    }

    // Calcular total a pagar
    val totalPagar = totalCreditos * valorCredito

    // Determinar carga académica
    val cargaAcademica: String
    var autorizacion = false

    if (totalCreditos == 12.0) {
        cargaAcademica = "M.R"
    } else if (totalCreditos >= 13.0 && totalCreditos <= 18.0) {
        cargaAcademica = "Carga Completa"
    } else if (totalCreditos > 18.0) {
        cargaAcademica = "Requiere Autorización"

        // Validación de autorización
        print("\n️  La carga académica excede el límite. ¿Autoriza esta matrícula? (si/no): ")
        val respuesta = readLine()?.lowercase() ?: ""

        if (respuesta == "si") {
            autorizacion = true
        } else {
            println("\n MATRÍCULA CANCELADA POR EXCESO DE CREDITOS")
            println("Hasta luego, $nombreEstudiante")
            return // Sale del programa inmediatamente
        }
    }

    // Determinar forma de pago (cuotas)
    val formaPago: Int
    if (totalPagar > 2500) {
        formaPago = 3
    } else {
        formaPago = 2
    }
}