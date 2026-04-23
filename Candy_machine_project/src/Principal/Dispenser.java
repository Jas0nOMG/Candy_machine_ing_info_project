package Principal;

public class Dispenser {
	 
    private int numberOfItems; 
    private int cost;          
 
   
    public Dispenser() {
        numberOfItems = 50;
        cost = 50;
    }
 
    
    public Dispenser(int setNoOfItems, int setCost) {
        if (setNoOfItems >= 0)
            numberOfItems = setNoOfItems;
        else
            numberOfItems = 50;
 
        if (setCost >= 0)
            cost = setCost;
        else
            cost = 50;
    }
 
    
    public int getCount() {
        return numberOfItems;
    }
 
   
    public int getProductCost() {
        return cost;
    }
 
    
    public boolean isEmpty() {
        return numberOfItems == 0;
    }
 
    
    public void makeSale() {
        if (numberOfItems > 0)
            numberOfItems--;
    }
 
    
    public void restock(int amount) {
        if (amount > 0)
            numberOfItems += amount;
    }
 
    
    @Override
    public String toString() {
        return String.format("Dispenser [Artículos: %d | Precio: %d centavos ($%.2f)]",
                numberOfItems, cost, cost / 100.0);
    }
}
