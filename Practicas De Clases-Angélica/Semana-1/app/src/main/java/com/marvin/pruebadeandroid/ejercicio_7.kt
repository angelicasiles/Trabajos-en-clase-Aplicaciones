import kotlin.math.pow
import kotlin.math.sqrt

// Función para calcular la distancia entre dos puntos
fun calcularDistancia(puntos: Map<String, Double>): Double? {
    // Verificar que el mapa contiene las coordenadas necesarias
    if (puntos.containsKey("x1") && puntos.containsKey("y1") && puntos.containsKey("x2") && puntos.containsKey("y2")) {
        // Extraer las coordenadas de los puntos desde el mapa
        val x1 = puntos["x1"] ?: 0.0
        val y1 = puntos["y1"] ?: 0.0
        val x2 = puntos["x2"] ?: 0.0
        val y2 = puntos["y2"] ?: 0.0

        // Calcular la distancia utilizando la fórmula de la distancia euclidiana
        return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    } else {
        // Si el mapa no contiene todas las coordenadas necesarias, devolver null o un mensaje de error
        return null
    }
}

fun main() {
    // Ejemplo de uso:
    val puntos = mapOf(
        "x1" to 1.0,
        "y1" to 2.0,
        "x2" to 4.0,
        "y2" to 6.0
    )

    val distancia = calcularDistancia(puntos)

    if (distancia != null) {
        println("La distancia entre los puntos P1 y P2 es: $distancia")
    } else {
        println("Error: El mapa debe contener 'x1', 'y1', 'x2' y 'y2'.")
    }
}