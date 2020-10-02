import java.util.*;

public class StateSelectingMenuItem implements State {
    @Override
    public void ActorInfo(Stage stage, int x, int y) {
        MenuItemIterator tempMenuItem = new MenuItemIterator(stage.menuOverlay);
        while (tempMenuItem.hasNext()) {
            // for (MenuItem mi : menuOverlay) {
            MenuItem mi = tempMenuItem.next();
            if (mi.contains(x, y)) {
                mi.action.run();
                stage.menuOverlay = new ArrayList<MenuItem>();
            }
        }
    }
}
