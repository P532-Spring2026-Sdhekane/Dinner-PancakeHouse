import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class MenuGUI extends JFrame {

    // ── Modern dark theme palette ─────────────────────────────────────────────
    private static final Color BG_DARK       = new Color(18,  18,  24);
    private static final Color BG_CARD       = new Color(30,  30,  40);
    private static final Color BG_CARD_HOVER = new Color(40,  40,  55);
    private static final Color ACCENT_PINK   = new Color(255, 80, 130);
    private static final Color ACCENT_CYAN   = new Color(60, 210, 220);
    private static final Color ACCENT_YELLOW = new Color(255, 210,  60);
    private static final Color TEXT_PRIMARY  = new Color(240, 240, 250);
    private static final Color TEXT_SECONDARY= new Color(150, 150, 175);
    private static final Color VEG_GREEN     = new Color(80,  220, 120);
    private static final Color DIVIDER       = new Color(50,  50,  70);

    private PancakeHouseMenu pancakeMenu;
    private DinerMenu        dinerMenu;
    private JPanel           contentPanel;
    private CardLayout       cardLayout;
    private JLabel           statusLabel;

    public MenuGUI() {
        pancakeMenu = new PancakeHouseMenu();
        dinerMenu   = new DinerMenu();

        setTitle("Objectville — Digital Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        add(buildSidebar(),  BorderLayout.WEST);
        add(buildMain(),     BorderLayout.CENTER);
        add(buildFooter(),   BorderLayout.SOUTH);

        setSize(1000, 680);
        setMinimumSize(new Dimension(820, 500));
        setLocationRelativeTo(null);
        setVisible(true);

        // show pancake menu by default
        showMenu("pancake");
    }

    // ── Sidebar ───────────────────────────────────────────────────────────────
    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_CARD);
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, DIVIDER));

        // Logo area
        JPanel logo = new JPanel(new BorderLayout());
        logo.setBackground(BG_DARK);
        logo.setBorder(BorderFactory.createEmptyBorder(24, 16, 24, 16));
        logo.setMaximumSize(new Dimension(210, 90));

        JLabel brand = new JLabel("<html><center>[ Objectville ]</center></html>", SwingConstants.CENTER);
        brand.setFont(new Font("SansSerif", Font.BOLD, 18));
        brand.setForeground(TEXT_PRIMARY);

        JLabel tagline = new JLabel("Digital Menu", SwingConstants.CENTER);
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
        sidebar.add(makeSidebarBtn(">> Combined Menu", ACCENT_CYAN,   () -> showMenu("combined")));

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(makeDivider());

        JLabel ver = new JLabel("  v2.0 — No Patterns", SwingConstants.LEFT);
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
        btn.setBackground(new Color(0,0,0,0));
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

    // ── Main content area ─────────────────────────────────────────────────────
    private JPanel buildMain() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG_DARK);

        // Top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(BG_DARK);
        topBar.setBorder(BorderFactory.createEmptyBorder(18, 24, 10, 24));

        statusLabel = new JLabel("Pancake House Menu");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        statusLabel.setForeground(TEXT_PRIMARY);

        JLabel hint = new JLabel("(v) = vegetarian  •  prices in USD");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(TEXT_SECONDARY);

        topBar.add(statusLabel, BorderLayout.WEST);
        topBar.add(hint,        BorderLayout.EAST);

        wrapper.add(topBar, BorderLayout.NORTH);

        // Card layout for the three views
        cardLayout  = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BG_DARK);

        contentPanel.add(buildMenuGrid(pancakeMenu.getMenuItems(),    ACCENT_YELLOW), "pancake");
        contentPanel.add(buildMenuGrid(dinerArray(dinerMenu),         ACCENT_PINK),   "diner");
        contentPanel.add(buildMenuGrid(combined(),                    ACCENT_CYAN),   "combined");

        wrapper.add(contentPanel, BorderLayout.CENTER);
        return wrapper;
    }

    // Wrap the diner array into an ArrayList for reuse
    private ArrayList<MenuItem> dinerArray(DinerMenu d) {
        ArrayList<MenuItem> list = new ArrayList<>();
        for (int i = 0; i < d.getNumberOfItems(); i++) list.add(d.getMenuItems()[i]);
        return list;
    }

    private ArrayList<MenuItem> combined() {
        ArrayList<MenuItem> list = new ArrayList<>(pancakeMenu.getMenuItems());
        list.addAll(dinerArray(dinerMenu));
        return list;
    }

    // Grid of cards
    private JScrollPane buildMenuGrid(ArrayList<MenuItem> items, Color accent) {
        JPanel grid = new JPanel(new GridLayout(0, 2, 14, 14));
        grid.setBackground(BG_DARK);
        grid.setBorder(BorderFactory.createEmptyBorder(6, 24, 24, 24));

        for (MenuItem item : items) {
            grid.add(buildCard(item, accent));
        }

        JScrollPane scroll = new JScrollPane(grid,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_DARK);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    // Individual menu item card
    private JPanel buildCard(MenuItem item, Color accent) {
        JPanel card = new JPanel(new BorderLayout(0, 6)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                // accent left bar
                g2.setColor(accent);
                g2.fillRoundRect(0, 0, 5, getHeight(), 6, 6);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 16));

        // Top row: name + price
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

        // Description
        JLabel desc = new JLabel("<html><body style='width:200px'>" + item.getDescription() + "</body></html>");
        desc.setFont(new Font("SansSerif", Font.PLAIN, 12));
        desc.setForeground(TEXT_SECONDARY);

        // Bottom: veg badge
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottom.setOpaque(false);
        if (item.isVegetarian()) {
            JLabel veg = new JLabel(" ✦ Vegetarian ");
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

    // ── Footer ────────────────────────────────────────────────────────────────
    private JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footer.setBackground(BG_CARD);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, DIVIDER));

        footer.add(makeFooterBtn("Pancake House",  ACCENT_YELLOW, () -> showMenu("pancake")));
        footer.add(makeFooterBtn("Diner",          ACCENT_PINK,   () -> showMenu("diner")));
        footer.add(makeFooterBtn("Combined Menu",  ACCENT_CYAN,   () -> showMenu("combined")));

        return footer;
    }

    private JButton makeFooterBtn(String text, Color accent, Runnable action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setForeground(BG_DARK);
        btn.setBackground(accent);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> action.run());
        return btn;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private void showMenu(String key) {
        cardLayout.show(contentPanel, key);
        switch (key) {
            case "pancake"  -> statusLabel.setText("Pancake House Menu");
            case "diner"    -> statusLabel.setText("Diner Menu");
            case "combined" -> statusLabel.setText("Combined Menu - All Items");
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
        SwingUtilities.invokeLater(MenuGUI::new);
    }
}