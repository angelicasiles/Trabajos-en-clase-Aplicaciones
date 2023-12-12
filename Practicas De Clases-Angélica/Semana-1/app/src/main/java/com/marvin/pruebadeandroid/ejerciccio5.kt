package com.marvin.pruebadeandroid

fun sumaSinOperadorDeSuma(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (y != 0) {
        val carry = x and y
        x = x xor y
        y = carry shl 1
    }
    return x
}

fun main() {
    val num1 = 1
    val num2 = 1
    val resultado = sumaSinOperadorDeSuma(num1, num2)
    println("La suma de $num1 y $num2 es $resultado")
}