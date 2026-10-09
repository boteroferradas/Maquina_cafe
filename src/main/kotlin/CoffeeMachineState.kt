sealed class CoffeeMachineState {
    data class Idle(val timestamp: Long = System.currentTimeMillis()) : CoffeeMachineState()
    object Standby : CoffeeMachineState()
    object CafeCorto : CoffeeMachineState()
    object CafeConLeche : CoffeeMachineState()
    object CafeAmericano : CoffeeMachineState()
    data class CafeListo(val type: String) : CoffeeMachineState()
    object Limpiando : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}