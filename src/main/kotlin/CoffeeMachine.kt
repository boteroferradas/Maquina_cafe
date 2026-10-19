import CoffeeMachineState.*

object CoffeeMachine {
    var currentState: CoffeeMachineState = Idle

    enum class BotonesCafe {CORTO, LECHE, AMERICANO}

    fun gestorEstados(monedero : Double, boton : BotonesCafe){
        var funcionando = true
        println("Estado actual: $currentState")
        while (funcionando) {
            when (currentState) {
                is Idle -> {
                    if (monedero > 0.60) {
                        println("Monedero detectado, pasando a Standby")
                        currentState = Standby
                    } else {
                        println("Maquina en estado IDLE, esperando monedas")
                    }
                }
                is Standby -> {
                    println("En Standby, cafe " + boton.toString() + " seleccionado")
                    if (boton == BotonesCafe.CORTO) {
                        currentState = CafeCorto
                    }
                    else if (boton == BotonesCafe.LECHE) {
                        currentState = CafeConLeche
                    }
                    else if (boton == BotonesCafe.AMERICANO) {
                        currentState = CafeAmericano
                    }
                }
                is CafeCorto -> {
                    println("Cafe corto en preparacion...")
                    Thread.sleep(5000)
                    currentState = CafeListo
                }
                is CafeConLeche -> {
                    println("Cafe con leche en preparacion...")
                    Thread.sleep(5000)
                    currentState = CafeListo
                }
                is CafeAmericano -> {
                    println("Cafe americano en preparacion...")
                    Thread.sleep(5000)
                    currentState = CafeListo
                }
                is CafeListo -> {
                    println("Café servido. Por favor, toma tu café.")
                    val cambio = monedero - 0.60
                    println("Cambio a percibir: $cambio")
                    currentState = Limpiando
                }
                is Limpiando -> {
                    println("La máquina está limpiándose. Espera a que termine.")
                    Thread.sleep(2000)
                    currentState = Idle
                    funcionando = false
                }
                is Error -> {
                    println("La máquina tiene un error: ${(currentState as Error).message}")
                }
            }
        }
    }
}
