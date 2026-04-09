import java.time.DayOfWeek;
import java.time.LocalDate;

// Alternating Iterator: steps through the diner menu array by 2
// Even days (Mon/Wed/Fri/Sun) -> starts at index 0: items 0, 2, 4 ...
// Odd days  (Tue/Thu/Sat)     -> starts at index 1: items 1, 3, 5 ...
public class AlternatingDinerMenuIterator implements MenuIterator {
    MenuItem[] items;
    int position;
    final int step = 2;

    // Auto-detects today's day
    public AlternatingDinerMenuIterator(MenuItem[] items) {
        this.items = items;
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        boolean evenDay = (today == DayOfWeek.MONDAY
                        || today == DayOfWeek.WEDNESDAY
                        || today == DayOfWeek.FRIDAY
                        || today == DayOfWeek.SUNDAY);
        position = evenDay ? 0 : 1;
    }

    // Force a specific schedule (used for printing both in Main/GUI)
    public AlternatingDinerMenuIterator(MenuItem[] items, boolean evenDays) {
        this.items = items;
        position   = evenDays ? 0 : 1;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public MenuItem next() {
        MenuItem item = items[position];
        position += step;
        return item;
    }

    // Helper label for display
    public static String todayLabel() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        boolean evenDay = (today == DayOfWeek.MONDAY
                        || today == DayOfWeek.WEDNESDAY
                        || today == DayOfWeek.FRIDAY
                        || today == DayOfWeek.SUNDAY);
        return evenDay
                ? "Mon/Wed/Fri/Sun menu  (today: " + today + ")"
                : "Tue/Thu/Sat menu  (today: " + today + ")";
    }
}
