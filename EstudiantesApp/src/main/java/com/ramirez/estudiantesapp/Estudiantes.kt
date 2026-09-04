package com.ramirez.estudiantesapp

var totalInscritos = 0
var aforoMaximo = 0

fun main() {
    // 1. Se pide el aforo UNA SOLA VEZ al inicio
    print("Ingrese el aforo máximo de la institución: ")
    aforoMaximo = readLine()?.toInt() ?: 0

    var seguir = true

    do {
        // 2. Validación de Aforo: Si ya llegamos al tope, terminamos inmediatamente
        if (totalInscritos >= aforoMaximo) {
            println("\n======================================")
            println("         AFORO ALCANZADO              ")
            println("======================================")
            println("Se ha completado el aforo de $aforoMaximo estudiantes.")
            println("No se pueden realizar más inscripciones.")
            seguir = false
        } else {
            // PARTE 1: CREACIÓN DE INPUTS (Solo si hay cupo)
            println("\nAforo actual: $totalInscritos / $aforoMaximo")

            print("Ingrese nombre del estudiante: ")
            val nombreEstudiante = readLine() ?: return

            // Validación: Cantidad de cursos no puede ser 0 o negativo
            var cantidadCursos = 0
            while (cantidadCursos <= 0) {
                print("Ingrese cantidad de cursos (debe ser mayor a 0): ")
                val input = readLine()?.toInt()
                if (input != null && input > 0) {
                    cantidadCursos = input
                } else {
                    println("Error: La cantidad debe ser mayor a 0.")
                }
            }

            // Validación: Valor de crédito no puede ser 0 o negativo
            var valorCredito = 0.0
            while (valorCredito <= 0) {
                print("Ingrese valor de un crédito (debe ser mayor a 0): ")
                val input = readLine()?.toDouble()
                if (input != null && input > 0) {
                    valorCredito = input
                } else {
                    println("Error: El valor debe ser mayor a 0.")
                }
            }

            // --- AGREGADO: TURNO ---
            print("\nSeleccione turno (1: Mañana, 2: Tarde, 3: Noche): ")
            val opcionTurno = readLine()?.toInt() ?: 1
            var porcentajeRecargo = 0.0
            var nombreTurno = ""
            when (opcionTurno) {
                1 -> { nombreTurno = "Mañana"; porcentajeRecargo = 0.10 }
                2 -> { nombreTurno = "Tarde"; porcentajeRecargo = 0.15 }
                3 -> { nombreTurno = "Noche"; porcentajeRecargo = 0.20 }
                else -> { println("Opción inválida, asumiendo Mañana"); nombreTurno = "Mañana"; porcentajeRecargo = 0.10 }
            }

            // --- AGREGADO: CATEGORÍA Y MATRÍCULA ---
            print("Categoría (1: Ordinario, 2: Becario): ")
            val opcionCategoria = readLine()?.toInt() ?: 1
            var esBecario = false
            var costoMatricula = 0.0
            var nombreCategoria = ""

            if (opcionCategoria == 1) {
                esBecario = false
                nombreCategoria = "Ordinario"
                // Validación: Matrícula no puede ser 0 o negativa
                while (costoMatricula <= 0) {
                    print("Costo de matrícula (debe ser mayor a 0): ")
                    val input = readLine()?.toDouble()
                    if (input != null && input > 0) {
                        costoMatricula = input
                    } else {
                        println("Error: El costo debe ser mayor a 0.")
                    }
                }
            } else {
                esBecario = true
                nombreCategoria = "Becario"
                costoMatricula = 0.0
            }

            // Listas para almacenar nombres de cursos y créditos
            val nombresCursos = mutableListOf<String>()
            val creditosCursos = mutableListOf<Double>()

            // Leer datos de cada curso
            for (i in 0..cantidadCursos - 1) {
                print("Nombre del curso ${i + 1}: ")
                nombresCursos.add(readLine() ?: "")

                // Validación: Créditos del curso no pueden ser 0 o negativos
                var creditos = 0.0
                while (creditos <= 0) {
                    print("¿Cuántos créditos tiene ${nombresCursos[i]}? (debe ser mayor a 0): ")
                    val input = readLine()?.toDouble()
                    if (input != null && input > 0) {
                        creditos = input
                    } else {
                        println("Error: Los créditos deben ser mayor a 0.")
                    }
                }
                creditosCursos.add(creditos)
            }

            // PARTE 2: CREACIÓN DE CÁLCULOS

            // Calcular total de créditos
            var totalCreditos = 0.0
            for (i in 0..cantidadCursos - 1) {
                totalCreditos += creditosCursos[i]
            }

            // Calcular costo base de créditos
            val costoCreditos = totalCreditos * valorCredito

            // Aplicar recargo por turno al costo de créditos
            val costoCreditosConTurno = costoCreditos * (1 + porcentajeRecargo)

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

            // Calcular total sin IGV (Créditos con turno + Matrícula si aplica)
            val totalSinIgv = if (esBecario) costoCreditosConTurno else costoCreditosConTurno + costoMatricula

            // Aplicar IGV 18%
            val igv = 0.18
            val totalConIgv = totalSinIgv * (1 + igv)

            // Determinar forma de pago (cuotas) basado en el total con IGV
            val formaPago: Int
            if (totalConIgv > 2500) {
                formaPago = 3
            } else {
                formaPago = 2
            }

            // Calcular valor de cada cuota
            val valorCuota = totalConIgv / formaPago

            // PARTE 3: IMPRESIÓN DEL RESULTADO FINAL
            println()
            println("======================================")
            println("         RECIBO DE MATRÍCULA          ")
            println("======================================")
            println()
            println("Estudiante: $nombreEstudiante")
            println("Turno: $nombreTurno")
            println("Categoría: $nombreCategoria")

            println()
            println("Curso\t\t\tCreditos\tCosto")
            println("--------------------------------------")

            for (i in 0..cantidadCursos - 1) {
                // El costo del curso en el desglose incluye el recargo del turno
                val costoCurso = creditosCursos[i] * valorCredito * (1 + porcentajeRecargo)
                val nombreCurso = nombresCursos[i].padEnd(20, ' ')
                val creditosStr = creditosCursos[i].toInt().toString().padStart(8, ' ')
                val costoStr = String.format("%.0f", costoCurso).padStart(10, ' ')
                println("$nombreCurso $creditosStr $costoStr")
            }

            println("--------------------------------------")
            println()
            if (!esBecario) {
                println("Matrícula: $costoMatricula")
            } else {
                println("Matrícula: No aplica (Becario)")
            }
            println("Cursos matriculados: ${nombresCursos.size}")
            println("Total de créditos: ${totalCreditos.toInt()}")
            println("Total sin IGV: $totalSinIgv")
            println("Total a pagar (con IGV 18%): $totalConIgv")
            println("Carga Académica: $cargaAcademica")
            println("Forma de pago: $formaPago cuotas")
            println("Valor de cuota: $valorCuota")
            println()
            println("======================================")

            totalInscritos++
            println("\nEstudiante registrado. Total inscritos: $totalInscritos / $aforoMaximo")
        }

        // Pregunta si desea seguir, SOLO si aún hay cupo disponible
        if (seguir && totalInscritos < aforoMaximo) {
            println("\n¿Desea registrar otro estudiante? (si/no): ")
            val opcion = readLine()?.lowercase() ?: ""
            if (opcion != "si") {
                seguir = false
            }
        } else if (seguir && totalInscritos >= aforoMaximo) {
            // Si llegamos al aforo, no preguntamos, simplemente terminamos
            seguir = false
        }

    } while (seguir)

    println("\nFin del sistema.")
}