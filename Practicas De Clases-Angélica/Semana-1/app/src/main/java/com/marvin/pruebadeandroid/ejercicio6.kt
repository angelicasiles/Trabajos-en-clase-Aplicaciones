package com.marvin.pruebadeandroid

fun countSubsetsFormula(n: Int): Int {
    return 1 shl n // Equivalente a 2^n
}

fun countSubsetsRecursive(n: Int): Int {
    if (n == 0) {
        return 1
    }
    val subsetsWithoutN = countSubsetsRecursive(n - 1)
    return subsetsWithoutN * 2
}

fun main() {
    val n = 3 // Cambia este valor según el tamaño de tu conjunto
    val subsetsFormula = countSubsetsFormula(n)
    val subsetsRecursive = countSubsetsRecursive(n)

    println("Número de subconjuntos usando la fórmula 2^n: $subsetsFormula")
    println("Número de subconjuntos usando enfoque recursivo: $subsetsRecursive")
}