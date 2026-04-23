package Principal;
import java.util.Scanner;

public class CandyMachine {
	 
    private Scanner console = new Scanner(System.in);
 
    public void run(Dispenser candy, Dispenser chips,
                    Dispenser gum,   Dispenser cookies,
                    CashRegister cashRegister) {
 
        showSelection();
        int choice = console.nextInt();
 
        while (choice != 9) {
            switch (choice) {
                case 1: sellProduct(candy,   cashRegister, "Candy (Dulce)");   break;
                case 2: sellProduct(chips,   cashRegister, "Chips (Papas)");   break;
                case 3: sellProduct(gum,     cashRegister, "Gum (Chicle)");    break;
                case 4: sellProduct(cookies, cashRegister, "Cookies (Galletas)"); break;
                case 0: showInventory(cashRegister, candy, chips, gum, cookies); break;
                default: System.out.println("\n  [!] Selección inválida.\n");
            }
            showSelection();
            choice = console.nextInt();
        }
 
        System.out.println("\n=========================================");
        System.out.println("  ¡Gracias por su visita! Hasta pronto.");
        showInventory(cashRegister, candy, chips, gum, cookies);
    }
 
    public void showSelection() {
        System.out.println("=========================================");
        System.out.println("   *** Bienvenido a Shelly's Candy Shop ***");
        System.out.println("=========================================");
        System.out.println("  Para seleccionar un artículo, ingrese:");
        System.out.println("    1  ->  Candy   (Dulce)    $0.50");
        System.out.println("    2  ->  Chips   (Papas)    $0.65");
        System.out.println("    3  ->  Gum     (Chicle)   $0.45");
        System.out.println("    4  ->  Cookies (Galletas) $0.85");
        System.out.println("    9  ->  Salir");
        System.out.println("=========================================");
        System.out.print("  Su selección: ");
    }
 
    public void sellProduct(Dispenser product, CashRegister cRegister, String productName) {
        if (product.getCount() > 0) {
            int price         = product.getProductCost();
            int coinsRequired = price;
            int coinsInserted = 0;
 
            System.out.printf("%n  >> %s seleccionado. Precio: %d centavos ($%.2f)%n",
                    productName, price, price / 100.0);
 
            while (coinsRequired > 0) {
                System.out.printf("  Por favor deposite %d centavos: ", coinsRequired);
                int deposited = console.nextInt();
                coinsInserted += deposited;
                coinsRequired  = price - coinsInserted;
                if (coinsRequired > 0)
                    System.out.printf("  Faltan %d centavos.%n", coinsRequired);
                else if (coinsInserted > price)
                    System.out.printf("  Cambio: %d centavos%n", coinsInserted - price);
            }
 
            cRegister.acceptAmount(price);
            product.makeSale();
            System.out.println("\n  Recoja su artículo y disfrútelo!");
            System.out.printf("  Artículos restantes de %s: %d%n%n", productName, product.getCount());
 
        } else {
            System.out.printf("%n  [!] Lo sentimos, %s está agotado.%n%n", productName);
        }
    }
 
    public void showInventory(CashRegister cRegister,
                              Dispenser candy, Dispenser chips,
                              Dispenser gum,   Dispenser cookies) {
        System.out.println("\n========== INVENTARIO ==========");
        System.out.printf("  Caja registradora : $%.2f%n", cRegister.currentBalance() / 100.0);
        System.out.printf("  Candy   (Dulce)   : %d unidades%n", candy.getCount());
        System.out.printf("  Chips   (Papas)   : %d unidades%n", chips.getCount());
        System.out.printf("  Gum     (Chicle)  : %d unidades%n", gum.getCount());
        System.out.printf("  Cookies (Galletas): %d unidades%n", cookies.getCount());
        System.out.println("================================\n");
    }
}