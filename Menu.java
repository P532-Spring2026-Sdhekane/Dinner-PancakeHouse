import java.util.ArrayList;

public class Menu extends MenuComponent {

    private final ArrayList<MenuComponent> children = new ArrayList<>();
    private final String name;
    private final String description;

    public Menu(String name, String description) {
        this.name        = name;
        this.description = description;
    }

    @Override public void add(MenuComponent c)    { children.add(c); }
    @Override public void remove(MenuComponent c) { children.remove(c); }
    @Override public MenuComponent getChild(int i) { return children.get(i); }

    @Override public String getName()        { return name; }
    @Override public String getDescription() { return description; }

    public ArrayList<MenuComponent> getChildren() { return children; }

    @Override
    public void print() {
        System.out.println("\n+------------------------------------------+");
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("+------------------------------------------+");
        for (MenuComponent c : children) {
            c.print();
        }
    }
}
