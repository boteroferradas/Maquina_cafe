import org.example.Bebidas

fun main() {
    CoffeeMachine.monedero = 0.50
    CoffeeMachine.gestorEstados()
    CoffeeMachine.seleccion = Bebidas.AMERICANO
    CoffeeMachine.gestorEstados()
    CoffeeMachine.gestorEstados()
    CoffeeMachine.gestorEstados()
    CoffeeMachine.gestorEstados()
}
