import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.*;

public class Stage {
    Grid grid;
    ArrayList<Actor> actors;
    List<Cell> cellOverlay;
    List<MenuItem> menuOverlay;
    Optional<Actor> actorInAction;

    State state;
    State stateChoosingActor;
    State stateSelectingNewLocation;
    State stateSelectingMenuItem;
    State stateSelectingTarget;

    ActorState currentState = ActorState.ChoosingActor;

    public Stage() {
        grid = new Grid();
        actors = new ArrayList<Actor>();
        cellOverlay = new ArrayList<Cell>();
        menuOverlay = new ArrayList<MenuItem>();
        
        stateChoosingActor = new StateChoosingActor();
        stateSelectingNewLocation = new StateSelectingNewLocation();
        stateSelectingMenuItem = new StateSelectingMenuItem();
        stateSelectingTarget = new StateSelectingTarget();
        currentState = ActorState.ChoosingActor;
        state = new StateChoosingActor();
        
    }

    public void paint(Graphics g, Point mouseLoc) {
        // do we have AI moves to make
        if (currentState == ActorState.CPUMoving) {
            ActorIterator tempAIMove = new ActorIterator(actors);
            while(tempAIMove.hasNext()){
                Actor a = tempAIMove.next();
            // for (Actor a : actors) {
                if (!a.isTeamRed()) {
                    List<Cell> possibleLocs = getClearRadius(a.loc, a.moves, true);

                    Cell nextLoc = a.strat.chooseNextLoc(possibleLocs);

                    a.setLocation(nextLoc);
                }
            }
            currentState = ActorState.ChoosingActor;
            ActorIterator tempChoosingActors = new ActorIterator(actors);
            // for (Actor a : actors) {
            while (tempChoosingActors.hasNext()) {
                Actor a = tempChoosingActors.next();
                a.turns = 1;
            }
        }

        grid.paint(g, mouseLoc);
        grid.paintOverlay(g, cellOverlay, new Color(0f, 0f, 1f, 0.5f));

        ActorIterator tempPaintActors = new ActorIterator(actors);
        while (tempPaintActors.hasNext()) {
            Actor a = tempPaintActors.next();
            // for (Actor a : actors) {
            a.paint(g);
        }
        // state display
        g.setColor(Color.DARK_GRAY);
        g.drawString(currentState.toString(), 720, 20);

        // display cell
        Optional<Cell> cap = grid.cellAtPoint(mouseLoc);
        if (cap.isPresent()) {
            Cell capc = cap.get();
            g.setColor(Color.DARK_GRAY);
            g.drawString(String.valueOf(capc.col) + String.valueOf(capc.row), 720, 50);
            g.drawString(capc.description, 820, 50);
            g.drawString("crossing time", 720, 65);
            g.drawString(String.valueOf(capc.crossingTime()), 820, 65);
        }
        // agent display
        int yloc = 138;
        int i = 0;
        ActorIterator tempAgent = new ActorIterator(actors);
        while (tempAgent.hasNext()) {
            // for (int i = 0; i < actors.size(); i++) {
            Actor a = tempAgent.next();
            g.drawString(a.getClass().toString(), 720, yloc + 70 * i);
            g.drawString("location:", 730, yloc + 13 + 70 * i);
            g.drawString(Character.toString(a.loc.col) + Integer.toString(a.loc.row), 820, yloc + 13 + 70 * i);
            g.drawString("redness:", 730, yloc + 26 + 70 * i);
            g.drawString(Float.toString(a.redness), 820, yloc + 26 + 70 * i);
            g.drawString("strat:", 730, yloc + 39 + 70 * i);
            g.drawString(a.strat.toString(), 820, yloc + 39 + 70 * i);
            i++;
        }

        // menu overlay (on top of everything else)
        MenuItemIterator tempMenuOverlay = new MenuItemIterator(menuOverlay);
        while (tempMenuOverlay.hasNext()) {
            MenuItem mi = tempMenuOverlay.next();
            // for (MenuItem mi : menuOverlay) {
            mi.paint(g);
        }
    }

    public List<Cell> getClearRadius(Cell from, int size, boolean considerTime) {
        List<Cell> init = grid.getRadius(from, size, considerTime);

        ActorIterator tempClearRadius = new ActorIterator(actors);
        while (tempClearRadius.hasNext()) {
            Actor a = tempClearRadius.next();
            // for (Actor a : actors) {
            init.remove(a.loc);
        }
        return init;
    }

    public void mouseClicked(int x, int y) {
        switch (currentState) {
            case ChoosingActor:
                setState(stateChoosingActor);
                state.ActorInfo(this, x, y);
                break;
            case SelectingNewLocation:
                setState(stateSelectingNewLocation);
                state.ActorInfo(this, x, y);
                break;
            case SelectingMenuItem:
               setState(stateSelectingMenuItem);
               state.ActorInfo(this, x, y);
                break;
            case SelectingTarget:
                setState(stateSelectingTarget);
                state.ActorInfo(this, x, y);
                break;
            default:
                System.out.println(currentState);
                break;
        }
    }

    private void setState(State state) {
        this.state = state;
    }

    public Optional<Actor> actorAt(Cell c) {
        ActorIterator tempActors = new ActorIterator(actors);
        while (tempActors.hasNext()) {
            Actor a = tempActors.next();
            if (a.loc == c) {
                return Optional.of(a);
            }
            // for(Actor a: actors){
        }
        return Optional.empty();
    }
}
