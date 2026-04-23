package Principal;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		 
        CashRegister cashRegister = new CashRegister();
 
        Dispenser candy   = new Dispenser(100, 50);
        Dispenser chips   = new Dispenser(100, 65);
        Dispenser gum     = new Dispenser(75,  45);
        Dispenser cookies = new Dispenser(100, 85);
 
        CandyMachine machine = new CandyMachine();
        machine.run(candy, chips, gum, cookies, cashRegister);
    }
}
