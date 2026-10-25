import CoffeeMachine.currentState
import CoffeeMachine.monedero
import CoffeeMachine.seleccion
import org.example.Bebidas

sealed class CoffeeMachineState {

    object Idle : CoffeeMachineState(){
        fun onenter(machine: CoffeeMachine) {
            println("Entrando al estado Idle")
            Thread.sleep(2000)
            if (machine.monedero > 0.0) {
                println("Monedero detectado, pasando a Standby")
                currentState = Standby
            }
        }
        override fun toString(): String {
            return "Idle(monedero=${CoffeeMachine.monedero})"
        }
    }
    object Standby : CoffeeMachineState(){
        fun onenter(machine: CoffeeMachine) {
            println("En Standby, cafe $seleccion seleccionado")

            val precio = machine.seleccion.precio
            if (machine.seleccion != Bebidas.NOBEBIDA && machine.monedero >= precio) {
                machine.currentState = when (machine.seleccion) {
                    Bebidas.CORTO -> CafeCorto
                    Bebidas.LECHE -> CafeConLeche
                    Bebidas.AMERICANO -> CafeAmericano
                    else -> Idle
                }
            }
            else {
                println("-> No tiene saldo suficiente o sin seleccion valida. Volviendo a Idle")
                machine.currentState = Idle
            }
        }

        override fun toString(): String {
            return "Standby"
        }
    }
    object CafeCorto : CoffeeMachineState() {
        fun onenter(machine: CoffeeMachine) {
            println("Cafe Corto en preparacion...")
            Thread.sleep(2000)
            machine.currentState = CafeListo
        }

        override fun toString(): String {
            return "Cafe Corto"
        }
    }

    object CafeConLeche : CoffeeMachineState(){
        fun onenter(machine: CoffeeMachine) {
            println("Cafe con Leche en preparacion...")
            Thread.sleep(2000)
            machine.currentState = CafeListo
        }

        override fun toString(): String {
            return "Cafe con Leche"
        }
    }
    object CafeAmericano : CoffeeMachineState(){
        fun onenter(machine: CoffeeMachine){
            println("Cafe americano en preparacion...")
            Thread.sleep(2000)
            machine.currentState = CafeListo
        }

        override fun toString(): String {
            return "Cafe Americano"
        }
    }
    object CafeListo : CoffeeMachineState(){
        fun onenter(machine: CoffeeMachine){
            val precio = machine.seleccion.precio
            val cambio = machine.monedero - precio
            println("Café servido! Por favor, toma tu café.")
            println("Cambio a percibir: $cambio €")

            machine.monedero = 0.0
            machine.seleccion = Bebidas.NOBEBIDA

            machine.currentState = Limpiando
        }

        override fun toString(): String {
            return "Cafe Listo"
        }
    }
    object Limpiando : CoffeeMachineState(){
        fun onenter(machine: CoffeeMachine){
            println("La máquina está limpiándose. Espera a que termine.")
            Thread.sleep(1000)
            println("-> Limpieza completada, volviendo Idle")
            machine.currentState = Idle
        }

        override fun toString(): String {
            return "Limpiando"
        }
    }
    data class Error(val message: String) : CoffeeMachineState() {
        override fun toString(): String {
            return "Error: $message"
        }
    }
}