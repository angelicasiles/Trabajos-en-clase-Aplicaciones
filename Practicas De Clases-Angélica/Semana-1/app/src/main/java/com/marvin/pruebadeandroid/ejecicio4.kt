package com.marvin.pruebadeandroid
fun sonIguales(num1: Int, num2: Int): Boolean {
    val diferencia = num1.inv() and num2.inv()  // Calcula la inversa de ambos números y realiza una operación AND
    return diferencia == 0
}

fun main() {
    val numero1 = 10
    val numero2 = 10

    println("Número 1: $numero1")
    println("Número 2: $numero2")

    val iguales = sonIguales(numero1, numero2)

    println("¿Son iguales? $iguales")
}


