import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        PancakeHouseMenu pancakeMenu = new PancakeHouseMenu();
        DinerMenu        dinerMenu   = new DinerMenu();

        // ─────────────────────────────────────────
        // TASK 1: Print each menu separately (no pattern)
        // ─────────────────────────────────────────
        System.out.println("\n============================================");
        System.out.println("  TASK 1 - Two Menus Printed Individually");
        System.out.println("============================================");

        pancakeMenu.printMenu();
        dinerMenu.printMenu();

        // ─────────────────────────────────────────
        // TASK 2: Combine both menus (no pattern)
        // ─────────────────────────────────────────
        System.out.println("\n\n============================================");
        System.out.println("  TASK 2 - Combined Menu (No Pattern)");
        System.out.println("============================================");

        ArrayList<MenuItem> combined = new ArrayList<>();
        for (int i = 0; i < pancakeMenu.getMenuItems().size(); i++) {
            combined.add(pancakeMenu.getMenuItems().get(i));
        }
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

        // ─────────────────────────────────────────
        // TASK 3: Iterator Pattern
        // ─────────────────────────────────────────
        Waitress waitress = new Waitress(pancakeMenu, dinerMenu);

        waitress.printMenu();           // separate menus via iterator
        waitress.printCombinedMenu();   // combined via iterator
        waitress.printVegetarianMenu(); // vegetarian filter via iterator
        waitress.printBothAlternatingSchedules(); // alternating diner menu
    }
}

