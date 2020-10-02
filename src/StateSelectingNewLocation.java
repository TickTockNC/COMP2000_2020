import java.util.*;

public class StateSelectingNewLocation implements State {
    @Override
    public void ActorInfo(Stage stage, int x, int y) {
        Optional<Cell> clicked = Optional.empty();
        CellIterator tempClicked = new CellIterator(stage.cellOverlay);
        while (tempClicked.hasNext()) {
            Cell c = tempClicked.next();
            // for (Cell c : cellOverlay) {
            if (c.contains(x, y)) {
                clicked = Optional.of(c);
            }
        }
        if (clicked.isPresent() && stage.actorInAction.isPresent()) {
            stage.cellOverlay = new ArrayList<Cell>();
            stage.actorInAction.get().setLocation(clicked.get());
            stage.actorInAction.get().turns--;
            stage.menuOverlay.add(new MenuItem("Fire", x, y, () -> {
                stage.cellOverlay = stage.grid.getRadius(stage.actorInAction.get().loc, stage.actorInAction.get().range,
                        false);
                stage.cellOverlay.remove(stage.actorInAction.get().loc);
                stage.currentState = ActorState.SelectingTarget;
            }));
            stage.currentState = ActorState.SelectingMenuItem;
        }
    }
}
