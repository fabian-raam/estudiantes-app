package com.ramirez.estudiantesapp

var totalInscritos = 0
var aforoMaximo = 0

fun main() {
    // 1. Validación estricta del Aforo
    var aforoInput = 0
    while (aforoInput <= 0) {
        print("Ingrese el aforo máximo de la institución (debe ser mayor a 0): ")
        val input = readLine()

        if (input.isNullOrEmpty()) {
            println("Error: El campo no puede estar vacío.")
        } else if (!input.all { it.isDigit() }) {
            println("Error: El aforo debe ser un número válido (sin letras).")
        } else {
            aforoInput = input.toInt()
            if (aforoInput <= 0) {
                println("Error: El aforo debe ser mayor a 0.")
            }
        }
    }
    aforoMaximo = aforoInput

    var seguir = true

    do {
        // 2. Validación de Aforo: Si ya llegamos al tope, terminamos
        if (totalInscritos >= aforoMaximo) {
            println("\n======================================")
            println("         AFORO ALCANZADO              ")
            println("======================================")
            println("Se ha completado el aforo de $aforoMaximo estudiantes.")
            println("No se pueden realizar más inscripciones.")
            seguir = false
        } else {
            // PARTE 1: CREACIÓN DE INPUTS
            println("\nAforo actual: $totalInscritos / $aforoMaximo")

            // Validación: Nombre del estudiante (Solo letras)
            var nombreEstudiante = ""
            while (nombreEstudiante.isEmpty()) {
                print("Ingrese nombre del estudiante (solo letras): ")
                val input = readLine() ?: ""

                if (input.isEmpty()) {
                    println("Error: El nombre no puede estar vacío.")
                } else if (!input.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$"))) {
                    println("Error: El nombre solo puede contener letras.")
                } else {
                    nombreEstudiante = input
                }
            }

            // Validación: Cantidad de cursos (Número > 0)
            var cantidadCursos = 0
            while (cantidadCursos <= 0) {
                print("Ingrese cantidad de cursos (debe ser mayor a 0): ")
                val input = readLine() ?: ""

                if (input.isEmpty()) {
                    println("Error: El campo no puede estar vacío.")
                } else if (!input.all { it.isDigit() }) {
                    println("Error: La cantidad debe ser un número entero.")
                } else {
                    cantidadCursos = input.toInt()
                    if (cantidadCursos <= 0) {
                        println("Error: La cantidad debe ser mayor a 0.")
                    }
                }
            }

            // Validación: Valor de crédito (Decimal > 0)
            var valorCredito = 0.0
            while (valorCredito <= 0) {
                print("Ingrese valor de un crédito (debe ser mayor a 0): ")
                val input = readLine() ?: ""

                if (input.isEmpty()) {
                    println("Error: El campo no puede estar vacío.")
                } else if (input.toDoubleOrNull() == null) {
                    println("Error: Ingrese un número válido.")
                } else {
                    valorCredito = input.toDouble()
                    if (valorCredito <= 0) {
                        println("Error: El valor debe ser mayor a 0.")
                    }
                }
            }

            // --- AGREGADO: TURNO ---
            var opcionTurno = 0
            while (opcionTurno !in 1..3) {
                print("\nSeleccione turno (1: Mañana, 2: Tarde, 3: Noche): ")
                val input = readLine() ?: ""

                if (input.isEmpty()) {
                    println("Error: El campo no puede estar vacío.")
                } else if (!input.all { it.isDigit() }) {
                    println("Error: Ingrese un número (1, 2 o 3).")
                } else {
                    opcionTurno = input.toInt()
                    if (opcionTurno !in 1..3) {
                        println("Error: Opción inválida. Elija 1, 2 o 3.")
                    }
                }
            }

            var nombreTurno = ""
            var porcentajeRecargo = 0.0
            when (opcionTurno) {
                1 -> { nombreTurno = "Mañana"; porcentajeRecargo = 0.10 }
                2 -> { nombreTurno = "Tarde"; porcentajeRecargo = 0.15 }
                3 -> { nombreTurno = "Noche"; porcentajeRecargo = 0.20 }
            }

            // --- AGREGADO: CATEGORÍA Y MATRÍCULA ---
            var opcionCategoria = 0
            while (opcionCategoria !in 1..2) {
                print("Categoría (1: Ordinario, 2: Becario): ")
                val input = readLine() ?: ""

                if (input.isEmpty()) {
                    println("Error: El campo no puede estar vacío.")
                } else if (!input.all { it.isDigit() }) {
                    println("Error: Ingrese un número (1 o 2).")
                } else {
                    opcionCategoria = input.toInt()
                    if (opcionCategoria !in 1..2) {
                        println("Error: Opción inválida. Elija 1 o 2.")
                    }
                }
            }

            var esBecario = false
            var costoMatricula = 0.0
            var nombreCategoria = ""

            if (opcionCategoria == 1) {
                esBecario = false
                nombreCategoria = "Ordinario"
                // Validación: Matrícula > 0
                while (costoMatricula <= 0) {
                    print("Costo de matrícula (debe ser mayor a 0): ")
                    val input = readLine() ?: ""

                    if (input.isEmpty()) {
                        println("Error: El campo no puede estar vacío.")
                    } else if (input.toDoubleOrNull() == null) {
                        println("Error: Ingrese un número válido.")
                    } else {
                        costoMatricula = input.toDouble()
                        if (costoMatricula <= 0) {
                            println("Error: El costo debe ser mayor a 0.")
                        }
                    }
                }
            } else {
                esBecario = true
                nombreCategoria = "Becario"
                costoMatricula = 0.0
            }

            // Listas para almacenar datos
            val nombresCursos = mutableListOf<String>()
            val creditosCursos = mutableListOf<Double>()

            for (i in 0..cantidadCursos - 1) {
                // Validación: Nombre del curso
                var nombreCurso = ""
                while (nombreCurso.isEmpty()) {
                    print("Nombre del curso ${i + 1} (solo letras): ")
                    val input = readLine() ?: ""

                    if (input.isEmpty()) {
                        println("Error: El nombre no puede estar vacío.")
                    } else if (!input.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$"))) {
                        println("Error: El nombre solo puede contener letras.")
                    } else {
                        nombreCurso = input
                    }
                }
                nombresCursos.add(nombreCurso)

                // Validación: Créditos del curso (> 0)
                var creditos = 0.0
                while (creditos <= 0) {
                    print("¿Cuántos créditos tiene $nombreCurso? (debe ser mayor a 0): ")
                    val input = readLine() ?: ""

                    if (input.isEmpty()) {
                        println("Error: El campo no puede estar vacío.")
                    } else if (input.toDoubleOrNull() == null) {
                        println("Error: Ingrese un número válido.")
                    } else {
                        creditos = input.toDouble()
                        if (creditos <= 0) {
                            println("Error: Los créditos deben ser mayor a 0.")
                        }
                    }
                }
                creditosCursos.add(creditos)
            }

            // PARTE 2: CÁLCULOS
            var totalCreditos = 0.0
            for (creditos in creditosCursos) {
                totalCreditos += creditos
            }

            val costoCreditos = totalCreditos * valorCredito
            val costoCreditosConTurno = costoCreditos * (1 + porcentajeRecargo)
            val totalSinIgv = if (esBecario) costoCreditosConTurno else costoCreditosConTurno + costoMatricula
            val igv = 0.18
            val totalConIgv = totalSinIgv * (1 + igv)

            var cargaAcademica = ""
            if (totalCreditos == 12.0) {
                cargaAcademica = "M.R"
            } else if (totalCreditos >= 13.0 && totalCreditos <= 18.0) {
                cargaAcademica = "Carga Completa"
            } else if (totalCreditos > 18.0) {
                cargaAcademica = "Requiere Autorización"
                print("\n  La carga académica excede el límite. ¿Autoriza esta matrícula? (si/no): ")
                val respuesta = readLine()?.lowercase() ?: ""
                if (respuesta != "si") {
                    println("\nMATRÍCULA CANCELADA POR EXCESO DE CREDITOS")
                    println("Hasta luego, $nombreEstudiante")
                    return
                }
            } else {
                cargaAcademica = "Carga Mínima"
            }

            val formaPago: Int = if (totalConIgv > 2500) 3 else 2
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

        // Preguntar si desea continuar
        if (seguir && totalInscritos < aforoMaximo) {
            println("\n¿Desea registrar otro estudiante? (si/no): ")
            val opcion = readLine()?.lowercase() ?: ""
            if (opcion != "si") {
                seguir = false
            }
        } else {
            seguir = false
        }

    } while (seguir)

    println("\nFin del sistema.")
}