package Principal;

public class CashRegister {
	 
    private int cashOnHand; // dinero en caja (en centavos)
 
    /**
     * Constructor por defecto.
     * Inicializa la caja con 500 centavos ($5.00).
     */
    public CashRegister() {
        cashOnHand = 500;
    }
 
    /**
     * Constructor con parámetro.
     * @param cashIn cantidad inicial en centavos (debe ser >= 0)
     */
    public CashRegister(int cashIn) {
        if (cashIn >= 0)
            cashOnHand = cashIn;
        else
            cashOnHand = 500;
    }
 
    /**
     * Retorna el saldo actual de la caja registradora.
     * @return cantidad en centavos
     */
    public int currentBalance() {
        return cashOnHand;
    }
 
    /**
     * Acepta un pago del cliente y actualiza el saldo de la caja.
     * @param amountIn cantidad depositada en centavos
     */
    public void acceptAmount(int amountIn) {
        if (amountIn > 0)
            cashOnHand = cashOnHand + amountIn;
    }
 
    /**
     * Retorna una representación en texto del estado de la caja.
     */
    @Override
    public String toString() {
        return String.format("CashRegister [Saldo: %d centavos ($%.2f)]",
                cashOnHand, cashOnHand / 100.0);
    }
}