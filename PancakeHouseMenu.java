import java.util.ArrayList;

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

    // Iterator Pattern - each menu creates its own iterator
    public MenuIterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }


    public void printMenu() {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|     OBJECTVILLE PANCAKE HOUSE MENU       |");
        System.out.println("+------------------------------------------+");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(menuItems.get(i));
        }
    }
}
