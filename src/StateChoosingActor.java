import java.util.*;

public class StateChoosingActor implements State {
    @Override
    public void ActorInfo(Stage stage, int x, int y) {
        stage.actorInAction = Optional.empty();
        ActorIterator tempChoosingActor = new ActorIterator(stage.actors);
        while (tempChoosingActor.hasNext()) {
            // for (Actor a : actors) {
            Actor a = tempChoosingActor.next();
            if (a.loc.contains(x, y) && a.isTeamRed() && a.turns > 0) {
                stage.cellOverlay = stage.grid.getRadius(a.loc, a.moves, true);
                stage.actorInAction = Optional.of(a);
                stage.currentState = ActorState.SelectingNewLocation;
            }
        }
        if (stage.actorInAction.isEmpty()) {
            stage.currentState = ActorState.SelectingMenuItem;
            stage.menuOverlay.add(new MenuItem("Oops", x, y, () -> stage.currentState = ActorState.ChoosingActor));
            stage.menuOverlay.add(new MenuItem("End Turn", x, y + MenuItem.height, () -> stage.currentState = ActorState.CPUMoving));
            stage.menuOverlay.add(new MenuItem("End Game", x, y + MenuItem.height * 2, () -> System.exit(0)));
        }
    }
}
