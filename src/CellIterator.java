import java.util.*;

public class CellIterator implements Iterator<Cell> {
    Cell[][] dataA;
    List<Cell> dataL;
    int outer;
    int inner;
    int index;
    boolean runOut;
    CellState cellState;

    public CellIterator(Cell[][] data) {
        this.dataA = data;
        outer = 0;
        inner = 0;
        runOut = false;
        cellState = CellState.Array;
    }

    public CellIterator(List<Cell> data) {
        this.dataL = data;
        index = 0;
        runOut = dataL.isEmpty();
        cellState = CellState.List;
    }

    @Override
    public boolean hasNext() {
        return !runOut;
    }

    @Override
    public Cell next() {
        Cell ret = null;

        switch (cellState) {
            case Array:
                ret = dataA[outer][inner];
                inner++;
                if (inner >= dataA[outer].length) {
                    inner = 0;
                    outer++;
                    if (outer >= dataA.length) {
                        runOut = true;
                    }
                }
                break;
            case List:
                ret = dataL.get(index);
                index++;
                if (index >= dataL.size()) {
                    runOut = true;
                }
                break;
        }
        return ret;
    }

    public enum CellState {
        Array, List
    }
}