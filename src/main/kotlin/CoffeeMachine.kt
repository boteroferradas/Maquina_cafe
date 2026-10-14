import CoffeeMachineState
import CoffeeMachineState.*

object CoffeeMachine {
    var currentState: CoffeeMachineState = Idle
    var monedero: Int = 0

    enum class BotonesCafe {CORTO, LECHE, AMERICANO}

    fun gestorEstados(){
        println("Estado actual: $currentState")

        when (currentState) {
            is Idle -> {
                if (monedero > 0) {
                    println("Monedero detectado, pasando a Standby")
                    currentState = Standby
                } else {
                    println("Maquina en estado IDLE, esperando monedas")
                }
            }
            is Standby -> {
                println("La máquina está en espera. Puedes pedir un café.")
            }
            is CafeCorto, is CafeConLeche, is CafeAmericano -> {
                println("El café está en preparación. Espera unos segundos.")
            }
            is CafeListo -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is Limpiando -> {
                println("La máquina está limpiándose. Espera a que termine.")
            }
            is Error -> {
                println("La máquina tiene un error: ${(currentState as Error).message}")
            }
        }
    }

    fun seleccionarCafe(boton: BotonesCafe) {
        if (currentState !is Standby) {
            println("No se puede pedir café en el estado actual: $currentState")
            return
        }

        currentState = when (boton) {
            BotonesCafe.CORTO -> CafeCorto
            BotonesCafe.LECHE -> CafeConLeche
            BotonesCafe.AMERICANO -> CafeAmericano
        }
        println("Preparando café: $currentState")
        Thread.sleep(2000)
        currentState = CafeListo
        println("¡El café está listo!")
    }

    fun insertarMoneda(cantidad: Int) {
        monedero += cantidad
        println("Moneda insertada. Total en monedero: $monedero€")

        // Transición de estado basada en la condición
        if (currentState is Idle && monedero > 0) {
            currentState = Standby
            println("Estado cambiado a: Standby")
        }
    }

    fun limpiar() {
        when (currentState) {
            is CafeCorto,
            is CafeConLeche,
            is CafeAmericano -> {
                currentState = Error("No se puede limpiar la máquina mientras está preparando el café.")
                println("ERROR: La máquina no puede limpiarse en este estado: $currentState")
            }
            else -> {
                println("Limpiando la máquina...")
                currentState = Limpiando
                Thread.sleep(1000)
                currentState = Idle
                println("Máquina limpia. Estado: $currentState")
            }
        }
    }
}
