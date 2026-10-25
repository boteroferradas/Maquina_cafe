import CoffeeMachineState.*
import org.example.Bebidas

object CoffeeMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle
    public var monedero: Double = 0.0
    public var seleccion: Bebidas = Bebidas.NOBEBIDA

    fun gestorEstados(): CoffeeMachineState{
        println("\n--- Estado actual: $currentState ---")

            when (val state = currentState) {
                is Idle -> {
                    (currentState as Idle).onenter(this)
                }
                is Standby -> {
                    (currentState as Standby).onenter(this)
                }
                is CafeCorto -> {
                    (currentState as CafeCorto).onenter(this)
                }
                is CafeConLeche -> {
                    (currentState as CafeConLeche).onenter(this)
                }
                is CafeAmericano -> {
                    (currentState as CafeAmericano).onenter(this)
                }
                is CafeListo -> {
                    (currentState as CafeListo).onenter(this)
                }
                is Limpiando -> {
                    (currentState as Limpiando).onenter(this)
                }
                is Error -> {
                    println("La máquina tiene un error: ${(currentState as Error).message}")
                }
            }
        return currentState
    }
}

