fun main() {
    println("=== Simulación de la máquina de café ===")

    println("\n1) Estado inicial: Idle")
    CoffeeMachine.currentState = CoffeeMachineState.Idle()
    CoffeeMachine.makeCoffee()

    println("\n2) Intento de preparar café otra vez")
    CoffeeMachine.makeCoffee()

    println("\n3) Limpieza desde Idle")
    CoffeeMachine.clean()

    println("\n4) Limpieza correcta desde Café Listo")
    CoffeeMachine.currentState = CoffeeMachineState.CafeListo("Latte")
    CoffeeMachine.clean()

    println("\n5) Error por intentar limpiar durante la preparación")
    CoffeeMachine.currentState = CoffeeMachineState.CafeAmericano
    CoffeeMachine.clean()

    println("\n6) Recuperación del error")
    CoffeeMachine.currentState = CoffeeMachineState.Idle()
    CoffeeMachine.makeCoffee()

    println("\n=== Fin ===")
}
