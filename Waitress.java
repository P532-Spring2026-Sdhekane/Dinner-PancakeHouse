public class Waitress {

    private final MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }

    public void printVegetarianMenu() {
        System.out.println("\n============================================");
        System.out.println("  VEGETARIAN ITEMS ONLY");
        System.out.println("============================================");
        printVegetarian(allMenus);
    }

    private void printVegetarian(MenuComponent component) {
        try {
            if (component.isVegetarian()) {
                component.print();
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("\n--- " + component.getName() + " ---");
            for (MenuComponent child : ((Menu) component).getChildren()) {
                printVegetarian(child);
            }
        }
    }

    public void printAlternatingDinerMenu(MenuComponent dinerMenu, boolean evenDays) {
    String label = evenDays ? "Mon / Wed / Fri / Sun" : "Tue / Thu / Sat";
    System.out.println("\n--- Diner: " + label + " ---");

    java.util.ArrayList<MenuComponent> kids = ((Menu) dinerMenu).getChildren();
    java.util.ArrayList<MenuItem> itemList = new java.util.ArrayList<>();
    for (MenuComponent kid : kids) {
        if (kid instanceof MenuItem) itemList.add((MenuItem) kid);
    }
    MenuItem[] items = itemList.toArray(new MenuItem[0]);

    AlternatingDinerMenuIterator it = new AlternatingDinerMenuIterator(items, evenDays);
    while (it.hasNext()) it.next().print();
}
    public void printBothAlternatingSchedules(MenuComponent dinerMenu) {
        System.out.println("\n============================================");
        System.out.println("  ALTERNATING DINER MENU - Both Schedules");
        System.out.println("============================================");
        printAlternatingDinerMenu(dinerMenu, true);
        printAlternatingDinerMenu(dinerMenu, false);
        System.out.println("\n>> Today: " + AlternatingDinerMenuIterator.todayLabel());
    }
}
