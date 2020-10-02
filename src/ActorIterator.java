import java.util.*;

public class ActorIterator implements Iterator<Actor> {
    List<Actor> data;
    int index;
    boolean runOut;

    public ActorIterator(ArrayList<Actor> data) {
        this.data = data;
        index = 0;
        runOut = data.isEmpty();
    }

    @Override
    public boolean hasNext() {
        return !runOut;
    }

    @Override
    public Actor next() {
        Actor ret = null;
        ret = data.get(index);
        index++;
        if (index >= data.size()) {
            runOut = true;
        }
        return ret;
    }
}