// Composite Pattern - Waitress
// The Waitress only knows about MenuComponent.
// It does not care if it's a leaf (MenuItem) or composite (Menu) -
// it just calls print() and the tree handles the rest.
public class Waitress {

    private final MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    // Composite Pattern: one call prints the entire tree recursively
    public void printMenu() {
        allMenus.print();
    }

    // Walk the tree and print only vegetarian leaves
    public void printVegetarianMenu() {
        System.out.println("\n============================================");
        System.out.println("  VEGETARIAN ITEMS ONLY");
        System.out.println("============================================");
        printVegetarian(allMenus);
    }

    private void printVegetarian(MenuComponent component) {
        try {
            // If it's a leaf (MenuItem), check vegetarian flag
            if (component.isVegetarian()) {
                component.print();
            }
        } catch (UnsupportedOperationException e) {
            // It's a composite (Menu) - print its header and recurse
            System.out.println("\n--- " + component.getName() + " ---");
            for (MenuComponent child : ((Menu) component).getChildren()) {
                printVegetarian(child);
            }
        }
    }

    // Alternating diner menu - still uses the iterator on the diner's children
    public void printAlternatingDinerMenu(MenuComponent dinerMenu, boolean evenDays) {
        String label = evenDays ? "Mon / Wed / Fri / Sun" : "Tue / Thu / Sat";
        System.out.println("\n--- Diner: " + label + " ---");

        // Build a temporary array from the diner menu children for the iterator
        java.util.ArrayList<MenuComponent> kids = ((Menu) dinerMenu).getChildren();
        MenuItem[] items = new MenuItem[kids.size()];
        for (int i = 0; i < kids.size(); i++) items[i] = (MenuItem) kids.get(i);

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
