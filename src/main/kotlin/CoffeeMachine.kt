object CoffeeMachine {
    var currentState: CoffeeMachineState = CoffeeMachineState.Idle()

    fun makeCoffee() {
        println("Estado actual: $currentState")

        when (currentState) {
            is CoffeeMachineState.Idle -> {
                val idleState = currentState as CoffeeMachineState.Idle
                println("Máquina encendida desde: ${idleState.timestamp}. Preparando café...")
                currentState = CoffeeMachineState.Standby
                println("La máquina queda en standby y está lista para servir café.")
                currentState = CoffeeMachineState.CafeCorto
                Thread.sleep(2000)
                currentState = CoffeeMachineState.CafeListo("Nescafé")
                println("¡Café listo! Estado: $currentState")
            }
            is CoffeeMachineState.Standby -> {
                println("La máquina está en espera. Puedes pedir un café.")
            }
            is CoffeeMachineState.CafeCorto,
            is CoffeeMachineState.CafeConLeche,
            is CoffeeMachineState.CafeAmericano -> {
                println("El café está en preparación. Espera unos segundos.")
            }
            is CoffeeMachineState.CafeListo -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is CoffeeMachineState.Limpiando -> {
                println("La máquina está limpiándose. Espera a que termine.")
            }
            is CoffeeMachineState.Error -> {
                println("La máquina tiene un error: ${(currentState as CoffeeMachineState.Error).message}")
            }
        }
    }

    fun clean() {
        when (currentState) {
            is CoffeeMachineState.CafeCorto,
            is CoffeeMachineState.CafeConLeche,
            is CoffeeMachineState.CafeAmericano -> {
                currentState = CoffeeMachineState.Error("No se puede limpiar la máquina mientras está preparando el café.")
                println("ERROR: La máquina no puede limpiarse en este estado: $currentState")
            }
            else -> {
                println("Limpiando la máquina...")
                currentState = CoffeeMachineState.Limpiando
                Thread.sleep(1000)
                currentState = CoffeeMachineState.Idle()
                println("Máquina limpia. Estado: $currentState")
            }
        }
    }
}
