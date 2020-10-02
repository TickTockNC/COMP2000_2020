import java.util.*;
import java.awt.*;

public class PolygonIterator implements Iterator<Polygon> {
    ArrayList<Polygon> data;
    int index;
    boolean runOut;


    public PolygonIterator(ArrayList<Polygon> data) {
        this.data = data;
        index = 0;
        runOut = data.isEmpty();
    }

    @Override
    public boolean hasNext() {
        return !runOut;
    }

    @Override
    public Polygon next() {
        Polygon ret = null;
                ret = data.get(index);
                index++;
                if (index >= data.size()) {
                    runOut = true;
                }
        return ret;
    }
}