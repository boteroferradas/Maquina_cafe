sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState()
    object Standby : CoffeeMachineState()
    object CafeCorto : CoffeeMachineState()
    object CafeConLeche : CoffeeMachineState()
    object CafeAmericano : CoffeeMachineState()
    object CafeListo : CoffeeMachineState()
    object Limpiando : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}