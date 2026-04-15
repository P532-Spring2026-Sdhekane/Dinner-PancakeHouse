import java.util.*;

public class CompositeIterator implements Iterator<MenuComponent> {

    private final java.util.Stack<Iterator<MenuComponent>> stack = new java.util.Stack<>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) return false;
        Iterator<MenuComponent> it = stack.peek();
        if (!it.hasNext()) {
            stack.pop();
            return hasNext();
        }
        return true;
    }

    @Override
    public MenuComponent next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        Iterator<MenuComponent> it = stack.peek();
        MenuComponent component = it.next();
        if (component instanceof Menu) {
            stack.push(((Menu) component).getChildren().iterator());
        }
        return component;
    }
    
}
