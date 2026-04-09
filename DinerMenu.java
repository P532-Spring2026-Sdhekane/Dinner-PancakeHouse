// Task 1 – Objectville Diner Menu uses a plain Array (no design pattern)
public class DinerMenu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat",
                true, 2.99);
        addItem("BLT",
                "Bacon with lettuce & tomato on whole wheat",
                false, 2.99);
        addItem("Soup of the Day",
                "A bowl of the soup of the day, with a side of potato salad",
                false, 3.29);
        addItem("Hot Dog",
                "A hot dog, with sauerkraut, topped with cheese",
                false, 3.05);
        addItem("Steamed Veggies and Broccoli",
                "A medley of steamed vegetables with rice",
                true, 3.99);
        addItem("Pasta",
                "Spaghetti with marinara sauce, and a slice of sourdough bread",
                true, 3.89);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full! Can't add item: " + name);
        } else {
            menuItems[numberOfItems] = new MenuItem(name, description, vegetarian, price);
            numberOfItems++;
        }
    }

    public MenuItem[] getMenuItems() { return menuItems; }
    public int getNumberOfItems()    { return numberOfItems; }

    // Task 1 - print directly, no Iterator pattern
    public void printMenu() {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|       OBJECTVILLE DINER MENU             |");
        System.out.println("+------------------------------------------+");
        for (int i = 0; i < numberOfItems; i++) {
            System.out.println(menuItems[i]);
        }
    }
}