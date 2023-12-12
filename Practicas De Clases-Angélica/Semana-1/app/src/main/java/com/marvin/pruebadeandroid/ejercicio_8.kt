package com.marvin.pruebadeandroid

fun textoANumero(expresion: String): Int {
    val diccionario = mapOf(
        "cero" to 0, "uno" to 1, "dos" to 2, "tres" to 3, "cuatro" to 4, "cinco" to 5, "seis" to 6,
        "siete" to 7, "ocho" to 8, "nueve" to 9, "diez" to 10, "once" to 11, "doce" to 12, "trece" to 13,
        "catorce" to 14, "quince" to 15, "dieciséis" to 16, "diecisiete" to 17, "dieciocho" to 18,
        "diecinueve" to 19, "veinte" to 20, "treinta" to 30, "cuarenta" to 40, "cincuenta" to 50,
        "sesenta" to 60, "setenta" to 70, "ochenta" to 80, "noventa" to 90, "cien" to 100,
        "ciento" to 100, "doscientos" to 200, "trescientos" to 300, "cuatrocientos" to 400,
        "quinientos" to 500, "seiscientos" to 600, "setecientos" to 700, "ochocientos" to 800,
        "novecientos" to 900
    )

    val palabras = expresion.toLowerCase().split(" ")
    var resultado = 0
    var temp = 0

    for (palabra in palabras) {
        val valor = diccionario[palabra]
        if (valor != null) {
            temp += valor
        } else if (palabra == "mil") {
            temp *= 1000
        } else if (palabra == "millón") {
            temp *= 1000000
            resultado += temp
            temp = 0
        }
    }

    resultado += temp

    return resultado
}

fun main() {
    val expresion = "Seiscientos Treinta y Uno"
    val numero = textoANumero(expresion)
    println("$expresion => $numero")
}