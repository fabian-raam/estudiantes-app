package com.ramirez.estudiantesapp
var aforo = 2
fun main() {
    var seguir = true
    var totalInscritos = 0
    do {
        if(totalInscritos>= aforo){
            println("-------------")
            println("Aforo de estudiantes lleno")
            println("-------------")
            seguir = false
        }else{
            // PARTE 1: CREACIÓN DE INPUTS
            println("Aforo de la institucion $aforo")

            print("Ingrese nombre del estudiante: ")
            val nombreEstudiante = readLine() ?: return

            print("Ingrese cantidad de cursos: ")
            val cantidadCursos = readLine()?.toInt() ?: return

            print("Ingrese valor de un crédito: ")
            val valorCredito = readLine()?.toDouble() ?: return

            print("\nSeleccione turno (1: Mañana, 2: Tarde, 3: Noche): ")
            val opcionTurno = readLine()?.toInt() ?: 1

            var porcentajeRecargo = 0.0
            var nombreTurno = ""

            when(opcionTurno) {
                1 -> { nombreTurno = "Mañana"; porcentajeRecargo = 0.10 }
                2 -> { nombreTurno = "Tarde"; porcentajeRecargo = 0.15 }
                3 -> { nombreTurno = "Noche"; porcentajeRecargo = 0.20 }
                else -> {
                    println("Opción inválida. Usando turno Mañana por defecto.")
                    nombreTurno = "Mañana"
                    porcentajeRecargo = 0.10
                }
            }

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
            val totalPagar = (totalCreditos * valorCredito) * (1 + porcentajeRecargo)

            // Determinar carga académica (inicializada con valor por defecto)
            var cargaAcademica: String
            var autorizacion = false

            if (totalCreditos == 12.0) {
                cargaAcademica = "M.R"
            } else if (totalCreditos >= 13.0 && totalCreditos <= 18.0) {
                cargaAcademica = "Carga Completa"
            } else if (totalCreditos > 18.0) {
                cargaAcademica = "Requiere Autorización"

                // Validación de autorización
                print("\n  La carga académica excede el límite. ¿Autoriza esta matrícula? (si/no): ")
                val respuesta = readLine()?.lowercase() ?: ""

                if (respuesta == "si") {
                    autorizacion = true
                } else {
                    println("\nMATRÍCULA CANCELADA POR EXCESO DE CREDITOS")
                    println("Hasta luego, $nombreEstudiante")
                    return // Sal del programa inmediatamente
                }
            } else {
                // Crédidos menores a 12
                cargaAcademica = "Carga Mínima"
            }

            // Determinar forma de pago (cuotas)
            val formaPago: Int
            if (totalPagar > 2500) {
                formaPago = 3
            } else {
                formaPago = 2
            }

            // Calcular valor de cada cuota
            val valorCuota = totalPagar / formaPago
            // PARTE 3: IMPRESIÓN DEL RESULTADO FINAL
            println()
            println("======================================")
            println("         RECIBO DE MATRÍCULA          ")
            println("======================================")
            println()
            println("Estudiante: $nombreEstudiante")
            println()
            println("Turno: $nombreTurno")
            println("Curso\t\t\tCreditos\tCosto")
            println("--------------------------------------")

            for (i in 0..cantidadCursos - 1) {
                val costoCurso = creditosCursos[i] * valorCredito
                val nombreCurso = nombresCursos[i].padEnd(20, ' ')
                val creditosStr = creditosCursos[i].toInt().toString().padStart(8, ' ')
                val costoStr = String.format("%.0f", costoCurso).padStart(10, ' ')
                println("$nombreCurso $creditosStr $costoStr")
            }

            println("--------------------------------------")
            println()
            println("Cursos matriculados: ${nombresCursos.size}")
            println("Total de créditos: ${totalCreditos.toInt()}")
            println("Total a pagar: $totalPagar")
            println("Carga Académica: $cargaAcademica")
            println("Forma de pago: $formaPago cuotas")
            println("Valor de cuota: $valorCuota ")
            println()
            println("======================================")
            totalInscritos++
            println("\nEstudiante registrado. Total inscritos: $totalInscritos")
        }
        // Pregunta si desea seguir
        println("\n¿Desea registrar otro estudiante? (si/no): ")
        val opcion = readLine()?.lowercase() ?: ""
        if (opcion != "si") {
            seguir = false
        }

    } while (seguir)
            println("\nFin del sistema de matrículas.")
    }
