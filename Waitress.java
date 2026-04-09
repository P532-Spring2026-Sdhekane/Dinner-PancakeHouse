// Iterator Pattern - Step 3: Waitress uses MenuIterator for BOTH menus
// Key benefit: printMenu(iterator) works the same whether the
// backing store is an Array (Diner) or ArrayList (Pancake House).
// The Waitress never needs to know which one it is.
public class Waitress {
    PancakeHouseMenu pancakeMenu;
    DinerMenu        dinerMenu;

    public Waitress(PancakeHouseMenu pancakeMenu, DinerMenu dinerMenu) {
        this.pancakeMenu = pancakeMenu;
        this.dinerMenu   = dinerMenu;
    }

    // Prints both menus using the Iterator pattern
    public void printMenu() {
        MenuIterator pancakeIterator = pancakeMenu.createIterator();
        MenuIterator dinerIterator   = dinerMenu.createIterator();

        System.out.println("\n============================================");
        System.out.println("  TASK 3 - Iterator Pattern: Both Menus");
        System.out.println("============================================");

        System.out.println("\n+------------------------------------------+");
        System.out.println("|     OBJECTVILLE PANCAKE HOUSE MENU       |");
        System.out.println("+------------------------------------------+");
        printMenu(pancakeIterator);

        System.out.println("\n+------------------------------------------+");
        System.out.println("|       OBJECTVILLE DINER MENU             |");
        System.out.println("+------------------------------------------+");
        printMenu(dinerIterator);
    }

    // Prints combined menu - both menus back-to-back via iterators
    public void printCombinedMenu() {
        System.out.println("\n============================================");
        System.out.println("  TASK 3 - Combined via Iterator Pattern");
        System.out.println("============================================");
        System.out.println("\n+------------------------------------------+");
        System.out.println("|        FULL OBJECTVILLE MENU             |");
        System.out.println("|   (Pancake House  +  Diner combined)     |");
        System.out.println("+------------------------------------------+");

        // Same helper method handles both - this is the whole point of the pattern
        printMenu(pancakeMenu.createIterator());
        printMenu(dinerMenu.createIterator());
    }

    // Prints only vegetarian items from both menus
    public void printVegetarianMenu() {
        System.out.println("\n============================================");
        System.out.println("  TASK 3 - Vegetarian Items Only");
        System.out.println("============================================");

        System.out.println("\n--- Pancake House (vegetarian) ---");
        printVegetarian(pancakeMenu.createIterator());

        System.out.println("\n--- Diner (vegetarian) ---");
        printVegetarian(dinerMenu.createIterator());
    }

    // ── private helpers ───────────────────────────────────────────────────────

    // One method handles ANY MenuIterator - Array or ArrayList, doesn't matter
    private void printMenu(MenuIterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void printVegetarian(MenuIterator iterator) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            if (item.isVegetarian()) {
                System.out.println(item);
            }
        }
    }

    // Prints today's alternating diner menu (auto-detects day)
    public void printAlternatingDinerMenu() {
        System.out.println("\n============================================");
        System.out.println("  ALTERNATING DINER MENU");
        System.out.println("  Today: " + AlternatingDinerMenuIterator.todayLabel());
        System.out.println("============================================");
        printMenu(new AlternatingDinerMenuIterator(dinerMenu.getMenuItems()));
    }

    // Shows both schedules so you can see how the array splits
    public void printBothAlternatingSchedules() {
        System.out.println("\n============================================");
        System.out.println("  ALTERNATING DINER MENU - Both Schedules");
        System.out.println("============================================");

        System.out.println("\n--- Mon / Wed / Fri / Sun ---");
        printMenu(new AlternatingDinerMenuIterator(dinerMenu.getMenuItems(), true));

        System.out.println("\n--- Tue / Thu / Sat ---");
        printMenu(new AlternatingDinerMenuIterator(dinerMenu.getMenuItems(), false));

        System.out.println("\n>> Today showing: " + AlternatingDinerMenuIterator.todayLabel());
    }
}
