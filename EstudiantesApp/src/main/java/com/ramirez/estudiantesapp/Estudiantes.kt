package com.ramirez.estudiantesapp

fun main(){
    val scanner = java.util.Scanner(System.`in`)

    print("Ingrese nombre del estudiante: ")
    val nombreEstudiante = scanner.nextLine()

    print("Ingrese cantidad de cursos: ")
    val cantidadCursos = scanner.nextInt()
    scanner.nextLine() // limpiar buffer

    print("Ingrese valor de un crédito: ")
    val valorCredito = scanner.nextDouble()

    // Listas para almacenar nombres de cursos y créditos
    val nombresCursos = mutableListOf<String>()
    val creditosCursos = mutableListOf<Double>()

}