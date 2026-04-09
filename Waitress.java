public class Waitress {
    PancakeHouseMenu pancakeMenu;
    DinerMenu        dinerMenu;

    public Waitress(PancakeHouseMenu pancakeMenu, DinerMenu dinerMenu) {
        this.pancakeMenu = pancakeMenu;
        this.dinerMenu   = dinerMenu;
    }

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

    public void printCombinedMenu() {
        System.out.println("\n============================================");
        System.out.println("  TASK 3 - Combined via Iterator Pattern");
        System.out.println("============================================");
        System.out.println("\n+------------------------------------------+");
        System.out.println("|        FULL OBJECTVILLE MENU             |");
        System.out.println("|   (Pancake House  +  Diner combined)     |");
        System.out.println("+------------------------------------------+");

        printMenu(pancakeMenu.createIterator());
        printMenu(dinerMenu.createIterator());
    }

    public void printVegetarianMenu() {
        System.out.println("\n============================================");
        System.out.println("  TASK 3 - Vegetarian Items Only");
        System.out.println("============================================");

        System.out.println("\n--- Pancake House (vegetarian) ---");
        printVegetarian(pancakeMenu.createIterator());

        System.out.println("\n--- Diner (vegetarian) ---");
        printVegetarian(dinerMenu.createIterator());
    }

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
}
