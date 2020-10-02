import java.util.*;

public class MenuItemIterator implements Iterator<MenuItem> {
    List<MenuItem> data;
    int index;
    boolean runOut;

    public MenuItemIterator(List<MenuItem> data) {
        this.data = data;
        index = 0;
        runOut = data.isEmpty();
    }

    @Override
    public boolean hasNext() {
        return !runOut;
    }

    @Override
    public MenuItem next() {
        MenuItem ret = null;
                ret = data.get(index);
                index++;
                if (index >= data.size()) {
                    runOut = true;
                }
        return ret;
    }
}