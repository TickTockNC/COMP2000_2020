import java.util.*;

public class StateSelectingTarget implements State {
    @Override
    public void ActorInfo(Stage stage, int x, int y) {
        CellIterator tempCellOverlay = new CellIterator(stage.cellOverlay);
        while (tempCellOverlay.hasNext()) {
            Cell c = tempCellOverlay.next();
            // for(Cell c: cellOverlay){
            if (c.contains(x, y)) {
                Optional<Actor> oa = stage.actorAt(c);
                if (oa.isPresent()) {
                    oa.get().makeRedder(0.1f);
                }
            }
        }
        stage.cellOverlay = new ArrayList<Cell>();
        stage.currentState = ActorState.ChoosingActor;
    }
}
