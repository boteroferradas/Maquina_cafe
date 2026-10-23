import CoffeeMachineState.*
import org.example.Bebidas

object CoffeeMachine {
    public var currentState: CoffeeMachineState = Idle
    public var monedero: Double = 0.0
    public var seleccion: Bebidas = Bebidas.NOBEBIDA

    fun gestorEstados(): CoffeeMachineState{
        println("Estado actual: $currentState")

            when (currentState) {
                is Idle -> {
                    (currentState as Idle).onenter()
                }
                is Standby -> {
                    (currentState as Standby).onenter()
                }
                is CafeCorto -> {
                    (currentState as CafeCorto).onenter()
                }
                is CafeConLeche -> {
                    (currentState as CafeConLeche).onenter()
                }
                is CafeAmericano -> {
                    (currentState as CafeAmericano).onenter()
                }
                is CafeListo -> {
                    (currentState as CafeListo).onenter()
                }
                is Limpiando -> {
                    (currentState as Limpiando).onenter()
                }
                is Error -> {
                    println("La máquina tiene un error: ${(currentState as Error).message}")
                }
            }
        return currentState
    }
}

