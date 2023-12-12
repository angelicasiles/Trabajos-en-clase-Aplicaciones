fun buscaMenor(num1: Int, num2: Int): Int {
    return (num1 + num2 - Math.abs(num1 - num2)) / 2
}

fun main(args: Array<String>) {
    println("Digite su primer número:")
    var num1 = readlnOrNull()?.toInt() as Int
    println("Digite su segundo número:")
    var num2 = readlnOrNull()?.toInt() as Int

    var menor = buscaMenor(num1, num2)

    println("El menor de estos dos números es: $menor")
}

