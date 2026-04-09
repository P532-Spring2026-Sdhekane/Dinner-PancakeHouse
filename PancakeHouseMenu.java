import java.util.ArrayList;

// Task 1 – Objectville Pancake House Menu uses an ArrayList (no design pattern)
public class PancakeHouseMenu {
    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItem("K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs, and toast",
                true, 2.99);
        addItem("Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage",
                false, 2.99);
        addItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries, and blueberry syrup",
                true, 3.49);
        addItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries",
                true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        menuItems.add(new MenuItem(name, description, vegetarian, price));
    }

    public ArrayList<MenuItem> getMenuItems() { return menuItems; }

    // Task 1 - print directly, no Iterator pattern
    public void printMenu() {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|     OBJECTVILLE PANCAKE HOUSE MENU       |");
        System.out.println("+------------------------------------------+");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(menuItems.get(i));
        }
    }
}