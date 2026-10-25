package org.example

/**
 * Enum que representa las bebidas disponibles en la máquina de café
 */
enum class Bebidas(val nombre: String, val precio: Double) {
    CORTO("Corto", 1.0),
    LECHE("Cafe con Leche", 2.0),
    AMERICANO("Americano", 1.5),
    NOBEBIDA("No bebida", 0.0)
}