import java.util.*;

public class LeftMostMove implements MoveStrategy {

    @Override
    public Cell chooseNextLoc(List<Cell> possibleLocs) {
        Cell currLM = possibleLocs.get(0);
        CellIterator tempPossibleLocs = new CellIterator(possibleLocs);
        // for(Cell c: possibleLocs){
        while (tempPossibleLocs.hasNext()) {
            Cell c = tempPossibleLocs.next();
            if (c.leftOfComparison(currLM) < 0) {
                currLM = c;
            }
        }
        return currLM;
    }

    @Override
    public String toString() {
        return "left-most movement strategy";
    }

}