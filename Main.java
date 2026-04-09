import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DinerMenu        dinerMenu   = new DinerMenu();
        PancakeHouseMenu pancakeMenu = new PancakeHouseMenu();

        // ─────────────────────────────────────────
        // TASK 1: Print each menu separately
        // ─────────────────────────────────────────
        System.out.println("\n============================================");
        System.out.println("  TASK 1 - Two Menus Printed Individually");
        System.out.println("============================================");

        pancakeMenu.printMenu();
        dinerMenu.printMenu();

        // ─────────────────────────────────────────
        // TASK 2: Combine both menus and print once
        // ─────────────────────────────────────────
        System.out.println("\n\n============================================");
        System.out.println("  TASK 2 - Combined Menu (All Items)");
        System.out.println("============================================");

        // Build a combined list manually – no pattern, just a loop each time
        ArrayList<MenuItem> combined = new ArrayList<>();

        // Add all pancake items
        for (int i = 0; i < pancakeMenu.getMenuItems().size(); i++) {
            combined.add(pancakeMenu.getMenuItems().get(i));
        }

        // Add all diner items (array)
        for (int i = 0; i < dinerMenu.getNumberOfItems(); i++) {
            combined.add(dinerMenu.getMenuItems()[i]);
        }

        System.out.println("\n+------------------------------------------+");
        System.out.println("|        FULL OBJECTVILLE MENU             |");
        System.out.println("|   (Pancake House  +  Diner combined)     |");
        System.out.println("+------------------------------------------+");
        System.out.printf("%-35s %-7s  %-5s%n", "Item", "Price", "Veg?");
        System.out.println("------------------------------------------");

        for (int i = 0; i < combined.size(); i++) {
            System.out.println(combined.get(i));
        }

        System.out.println("\nTotal items: " + combined.size());
    }
}