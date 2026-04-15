public class Main {

    public static void main(String[] args) {

        // ── Build the Composite tree ──────────────────────────────────────────
        MenuComponent pancakeMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu   = new Menu("DINER MENU",         "Lunch");
        MenuComponent allMenus    = new Menu("ALL MENUS",          "All menus combined");
        MenuComponent dessertMenu  = new Menu("DESSERT MENU",       "Dessert of course!");

        allMenus.add(pancakeMenu);
        allMenus.add(dinerMenu);
        allMenus.add(new Menu("CAFE MENU", "Dinner"));


        // Pancake House items 
        pancakeMenu.add(new MenuItem("K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs, and toast", true, 2.99));
        pancakeMenu.add(new MenuItem("Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage", false, 2.99));
        pancakeMenu.add(new MenuItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries, and blueberry syrup", true, 3.49));
        pancakeMenu.add(new MenuItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries", true, 3.59));

        // Diner items 
        dinerMenu.add(new MenuItem("Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99));
        dinerMenu.add(new MenuItem("BLT",
                "Bacon with lettuce & tomato on whole wheat", false, 2.99));
        dinerMenu.add(new MenuItem("Soup of the Day",
                "A bowl of the soup of the day, with a side of potato salad", false, 3.29));
        dinerMenu.add(new MenuItem("Hot Dog",
                "A hot dog, with sauerkraut, topped with cheese", false, 3.05));
        dinerMenu.add(new MenuItem("Steamed Veggies and Broccoli",
                "A medley of steamed vegetables with rice", true, 3.99));
        dinerMenu.add(new MenuItem("Pasta",
                "Spaghetti with marinara sauce, and a slice of sourdough bread", true, 3.89));
        dessertMenu.add(new MenuItem("Apple Pie",
                "Apple pie with a flaky crust, topped with vanilla ice cream", true, 1.59));
        dessertMenu.add(new MenuItem("Cheesecake",
                "Creamy cheesecake with a graham cracker crust", true, 1.99));

        dinerMenu.add(dessertMenu);

        Waitress waitress = new Waitress(allMenus);

        System.out.println("\n============================================");
        System.out.println("  COMPOSITE PATTERN - Full Menu Tree");
        System.out.println("============================================");
        waitress.printMenu();
        waitress.printVegetarianMenu();
waitress.printVegetarianMenu2();
        waitress.printBothAlternatingSchedules(dinerMenu);
    }
}
