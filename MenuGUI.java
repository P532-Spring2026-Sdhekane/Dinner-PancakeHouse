import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuGUI extends JFrame {

    private static final Color BG_DARK       = new Color(18,  18,  24);
    private static final Color BG_CARD       = new Color(30,  30,  40);
    private static final Color BG_CARD_HOVER = new Color(40,  40,  55);
    private static final Color ACCENT_PINK   = new Color(255,  80, 130);
    private static final Color ACCENT_CYAN   = new Color( 60, 210, 220);
    private static final Color ACCENT_YELLOW = new Color(255, 210,  60);
    private static final Color ACCENT_ORANGE = new Color(255, 140,  40);
    private static final Color TEXT_PRIMARY  = new Color(240, 240, 250);
    private static final Color TEXT_SECONDARY= new Color(150, 150, 175);
    private static final Color VEG_GREEN     = new Color( 80, 220, 120);
    private static final Color DIVIDER       = new Color( 50,  50,  70);
    private static final Color BTN_BAR_BG    = new Color( 22,  22,  32);

    private final MenuComponent allMenus;
    private final MenuComponent pancakeMenu;
    private final MenuComponent dinerMenu;
    private final Waitress      waitress;

    private JPanel    contentPanel;
    private CardLayout cardLayout;
    private JLabel    statusLabel;

    public MenuGUI(MenuComponent allMenus, MenuComponent pancakeMenu, MenuComponent dinerMenu) {
        this.allMenus    = allMenus;
        this.pancakeMenu = pancakeMenu;
        this.dinerMenu   = dinerMenu;
        this.waitress    = new Waitress(allMenus);

        setTitle("Objectville - Digital Menu (Composite Pattern)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        add(buildSidebar(), BorderLayout.WEST);
        add(buildMain(),    BorderLayout.CENTER);
        add(buildFooter(),  BorderLayout.SOUTH);

        setSize(1100, 720);
        setMinimumSize(new Dimension(900, 550));
        setLocationRelativeTo(null);
        setVisible(true);

        showMenu("pancake");
    }

    // ── Sidebar ───────────────────────────────────────────────────────────────
    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_CARD);
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, DIVIDER));

        JPanel logo = new JPanel(new BorderLayout());
        logo.setBackground(BG_DARK);
        logo.setBorder(BorderFactory.createEmptyBorder(24, 16, 24, 16));
        logo.setMaximumSize(new Dimension(210, 90));

        JLabel brand = new JLabel("<html><center>[ Objectville ]</center></html>", SwingConstants.CENTER);
        brand.setFont(new Font("SansSerif", Font.BOLD, 18));
        brand.setForeground(TEXT_PRIMARY);

        JLabel tagline = new JLabel("Composite Pattern", SwingConstants.CENTER);
        tagline.setFont(new Font("SansSerif", Font.ITALIC, 11));
        tagline.setForeground(ACCENT_CYAN);

        logo.add(brand,   BorderLayout.CENTER);
        logo.add(tagline, BorderLayout.SOUTH);
        sidebar.add(logo);

        sidebar.add(makeDivider());
        sidebar.add(Box.createVerticalStrut(12));

        JLabel navLabel = new JLabel("  MENUS");
        navLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        navLabel.setForeground(TEXT_SECONDARY);
        navLabel.setMaximumSize(new Dimension(210, 20));
        sidebar.add(navLabel);
        sidebar.add(Box.createVerticalStrut(6));

        sidebar.add(makeSidebarBtn(">> Pancake House", ACCENT_YELLOW, () -> showMenu("pancake")));
        sidebar.add(Box.createVerticalStrut(4));
        sidebar.add(makeSidebarBtn(">> Diner",         ACCENT_PINK,   () -> showMenu("diner")));
        sidebar.add(Box.createVerticalStrut(4));
        sidebar.add(makeSidebarBtn(">> All Menus",     ACCENT_CYAN,   () -> showMenu("all")));
        sidebar.add(Box.createVerticalStrut(4));
        sidebar.add(makeSidebarBtn(">> Alternating",   ACCENT_ORANGE, () -> showMenu("alternating")));

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(makeDivider());

        JLabel ver = new JLabel("  v3.0 - Composite Pattern", SwingConstants.LEFT);
        ver.setFont(new Font("SansSerif", Font.PLAIN, 10));
        ver.setForeground(new Color(90, 90, 110));
        ver.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        ver.setMaximumSize(new Dimension(210, 30));
        sidebar.add(ver);

        return sidebar;
    }

    private JButton makeSidebarBtn(String text, Color accent, Runnable action) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2.setColor(BG_CARD_HOVER);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                    g2.setColor(accent);
                    g2.fillRoundRect(0, 0, 4, getHeight(), 4, 4);
                }
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setForeground(TEXT_PRIMARY);
        btn.setBackground(new Color(0, 0, 0, 0));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btn.setMaximumSize(new Dimension(210, 40));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> action.run());
        return btn;
    }

    // ── Main content ──────────────────────────────────────────────────────────
    private JPanel buildMain() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG_DARK);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(BG_DARK);
        topBar.setBorder(BorderFactory.createEmptyBorder(18, 24, 10, 24));

        statusLabel = new JLabel("Pancake House Menu");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        statusLabel.setForeground(TEXT_PRIMARY);

        JLabel hint = new JLabel("(v) = vegetarian   prices in USD");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(TEXT_SECONDARY);

        topBar.add(statusLabel, BorderLayout.WEST);
        topBar.add(hint,        BorderLayout.EAST);
        wrapper.add(topBar, BorderLayout.NORTH);

        cardLayout   = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BG_DARK);

        contentPanel.add(buildMenuGrid(childItems(pancakeMenu), ACCENT_YELLOW), "pancake");
        contentPanel.add(buildMenuGrid(childItems(dinerMenu),   ACCENT_PINK),   "diner");
        contentPanel.add(buildMenuGrid(childItems(allMenus),    ACCENT_CYAN),   "all");
        contentPanel.add(buildAlternatingPanel(),                               "alternating");

        wrapper.add(contentPanel, BorderLayout.CENTER);
        return wrapper;
    }

    // Recursively collect all MenuItem leaves from a MenuComponent subtree
    private ArrayList<MenuComponent> childItems(MenuComponent component) {
        ArrayList<MenuComponent> result = new ArrayList<>();
        if (component instanceof MenuItem) {
            result.add(component);
        } else {
            for (MenuComponent child : ((Menu) component).getChildren()) {
                result.addAll(childItems(child));
            }
        }
        return result;
    }

    private JScrollPane buildMenuGrid(ArrayList<MenuComponent> items, Color accent) {
        JPanel grid = new JPanel(new GridLayout(0, 2, 14, 14));
        grid.setBackground(BG_DARK);
        grid.setBorder(BorderFactory.createEmptyBorder(6, 24, 24, 24));
        for (MenuComponent item : items) grid.add(buildCard(item, accent));

        JScrollPane scroll = new JScrollPane(grid,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_DARK);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JPanel buildCard(MenuComponent item, Color accent) {
        JPanel card = new JPanel(new BorderLayout(0, 6)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                g2.setColor(accent);
                g2.fillRoundRect(0, 0, 5, getHeight(), 6, 6);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 16));

        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        JLabel name = new JLabel(item.getName());
        name.setFont(new Font("SansSerif", Font.BOLD, 14));
        name.setForeground(TEXT_PRIMARY);

        JLabel price = new JLabel(String.format("$%.2f", item.getPrice()));
        price.setFont(new Font("SansSerif", Font.BOLD, 14));
        price.setForeground(accent);

        topRow.add(name,  BorderLayout.WEST);
        topRow.add(price, BorderLayout.EAST);

        JLabel desc = new JLabel("<html><body style='width:200px'>" + item.getDescription() + "</body></html>");
        desc.setFont(new Font("SansSerif", Font.PLAIN, 12));
        desc.setForeground(TEXT_SECONDARY);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottom.setOpaque(false);
        if (item.isVegetarian()) {
            JLabel veg = new JLabel(" * Vegetarian ");
            veg.setFont(new Font("SansSerif", Font.BOLD, 10));
            veg.setForeground(BG_DARK);
            veg.setBackground(VEG_GREEN);
            veg.setOpaque(true);
            veg.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
            bottom.add(veg);
        }

        card.add(topRow, BorderLayout.NORTH);
        card.add(desc,   BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);
        return card;
    }

    // Alternating panel: shows both schedules side by side
    private JPanel buildAlternatingPanel() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(BG_DARK);

        JLabel info = new JLabel("Today: " + AlternatingDinerMenuIterator.todayLabel(), SwingConstants.CENTER);
        info.setFont(new Font("SansSerif", Font.BOLD, 13));
        info.setForeground(ACCENT_ORANGE);
        info.setBorder(BorderFactory.createEmptyBorder(6, 24, 6, 24));
        outer.add(info, BorderLayout.NORTH);

        JPanel cols = new JPanel(new GridLayout(1, 2, 14, 0));
        cols.setBackground(BG_DARK);
        cols.setBorder(BorderFactory.createEmptyBorder(0, 24, 24, 24));

        cols.add(buildScheduleCol("Mon / Wed / Fri / Sun",
                alternatingItems(true), ACCENT_YELLOW));
        cols.add(buildScheduleCol("Tue / Thu / Sat",
                alternatingItems(false), ACCENT_ORANGE));

        outer.add(cols, BorderLayout.CENTER);
        return outer;
    }

    private ArrayList<MenuComponent> alternatingItems(boolean evenDays) {
        ArrayList<MenuComponent> kids = ((Menu) dinerMenu).getChildren();
        MenuItem[] arr = new MenuItem[kids.size()];
        for (int i = 0; i < kids.size(); i++) arr[i] = (MenuItem) kids.get(i);

        ArrayList<MenuComponent> result = new ArrayList<>();
        AlternatingDinerMenuIterator it = new AlternatingDinerMenuIterator(arr, evenDays);
        while (it.hasNext()) result.add(it.next());
        return result;
    }

    private JPanel buildScheduleCol(String title, ArrayList<MenuComponent> items, Color accent) {
        JPanel col = new JPanel(new BorderLayout(0, 8));
        col.setBackground(BG_DARK);

        JLabel lbl = new JLabel(title, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setForeground(accent);
        lbl.setBorder(BorderFactory.createEmptyBorder(4, 0, 8, 0));
        col.add(lbl, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 12));
        cards.setBackground(BG_DARK);
        for (MenuComponent item : items) cards.add(buildCard(item, accent));

        JScrollPane scroll = new JScrollPane(cards,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_DARK);
        col.add(scroll, BorderLayout.CENTER);
        return col;
    }

    // ── Footer ────────────────────────────────────────────────────────────────
    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(BTN_BAR_BG);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, DIVIDER));

        // Row 1: switch view
        JPanel viewRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        viewRow.setBackground(BTN_BAR_BG);
        JLabel viewLbl = new JLabel("View:");
        viewLbl.setForeground(TEXT_SECONDARY);
        viewLbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        viewRow.add(viewLbl);
        viewRow.add(makeFooterBtn("Pancake House", ACCENT_YELLOW, () -> showMenu("pancake")));
        viewRow.add(makeFooterBtn("Diner",         ACCENT_PINK,   () -> showMenu("diner")));
        viewRow.add(makeFooterBtn("All Menus",     ACCENT_CYAN,   () -> showMenu("all")));
        viewRow.add(makeFooterBtn("Alternating",   ACCENT_ORANGE, () -> showMenu("alternating")));

        // Row 2: console print buttons
        JPanel printRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        printRow.setBackground(new Color(14, 14, 20));
        JLabel printLbl = new JLabel("Print to console:");
        printLbl.setForeground(TEXT_SECONDARY);
        printLbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        printRow.add(printLbl);

        printRow.add(makeConsoleBtn("Composite - Full Tree", e ->
            waitress.printMenu()
        ));
        printRow.add(makeConsoleBtn("Vegetarian Only", e ->
            waitress.printVegetarianMenu()
        ));
        printRow.add(makeConsoleBtn("Alternating Diner Menu", e ->
            waitress.printBothAlternatingSchedules(dinerMenu)
        ));

        footer.add(viewRow,  BorderLayout.NORTH);
        footer.add(printRow, BorderLayout.SOUTH);
        return footer;
    }

    private JButton makeFooterBtn(String text, Color accent, Runnable action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setForeground(BG_DARK);
        btn.setBackground(accent);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(7, 16, 7, 16));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> action.run());
        return btn;
    }

    private JButton makeConsoleBtn(String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btn.setForeground(TEXT_PRIMARY);
        btn.setBackground(new Color(45, 45, 60));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DIVIDER, 1),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(action);
        return btn;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private void showMenu(String key) {
        cardLayout.show(contentPanel, key);
        switch (key) {
            case "pancake"     -> statusLabel.setText("Pancake House Menu");
            case "diner"       -> statusLabel.setText("Diner Menu");
            case "all"         -> statusLabel.setText("All Menus - Full Tree");
            case "alternating" -> statusLabel.setText("Alternating Diner - " + AlternatingDinerMenuIterator.todayLabel());
        }
    }

    private JSeparator makeDivider() {
        JSeparator sep = new JSeparator();
        sep.setForeground(DIVIDER);
        sep.setBackground(DIVIDER);
        sep.setMaximumSize(new Dimension(210, 1));
        return sep;
    }

    // ── Entry point ───────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Build composite tree
        MenuComponent pancakeMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu   = new Menu("DINER MENU",         "Lunch");
        MenuComponent allMenus    = new Menu("ALL MENUS",          "All menus combined");

        allMenus.add(pancakeMenu);
        allMenus.add(dinerMenu);

        pancakeMenu.add(new MenuItem("K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs, and toast", true, 2.99));
        pancakeMenu.add(new MenuItem("Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage", false, 2.99));
        pancakeMenu.add(new MenuItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries, and blueberry syrup", true, 3.49));
        pancakeMenu.add(new MenuItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries", true, 3.59));

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

        // Console output
        new Waitress(allMenus).printMenu();

        // Launch GUI
        SwingUtilities.invokeLater(() -> new MenuGUI(allMenus, pancakeMenu, dinerMenu));
    }
}
