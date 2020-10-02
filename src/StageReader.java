import java.io.*;
import java.util.*;
import java.util.regex.*;

class StageReader {
    public static Stage readStage(String path) {
        Stage stage = new Stage();
        try {
            Properties props = (new Properties());
            props.load(new FileInputStream(path));
            Iterator<String> tempKey = props.stringPropertyNames().iterator(); // Depature
            while (tempKey.hasNext()) {
                String temp = tempKey.next();
                // for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(temp);
                Pattern range = Pattern.compile("(.)(\\d+)->(.)(\\d+)");
                Pattern cell = Pattern.compile("(.)(\\d+)");
                List<Cell> cellsInQuestion = new ArrayList<Cell>();
                Matcher rangeMatcher = range.matcher(temp);
                Matcher cellMatcher = cell.matcher(temp);
                if (rangeMatcher.matches()) {
                    cellsInQuestion = stage.grid.cellsInRange(rangeMatcher.group(1).charAt(0),
                            Integer.parseInt(rangeMatcher.group(2)), rangeMatcher.group(3).charAt(0),
                            Integer.parseInt(rangeMatcher.group(4)));
                } else if (cellMatcher.matches()) {
                    stage.grid.cellAtColRow(cellMatcher.group(1).charAt(0), Integer.parseInt(cellMatcher.group(2)))
                            .ifPresent(cellsInQuestion::add);
                } else {
                    System.out.println("no match");
                }
                CellIterator tempCells = new CellIterator(cellsInQuestion);
                while (tempCells.hasNext()) {
                    // for (Cell c : cellsInQuestion) {
                    Cell c = tempCells.next();
                    switch (value) {
                        case "wall":
                            // if (value.equals("wall")) {
                            stage.grid.replaceCell(c, new Wall(c.col, c.row, c.x, c.y));
                            break;
                        case "oasis":
                            stage.grid.replaceCell(c, new Oasis(c.col, c.row, c.x, c.y));
                            break;
                        case "palm tree":
                            stage.grid.replaceCell(c, new PalmTree(c.col, c.row, c.x, c.y));
                            break;
                        case "puppy red":
                            stage.actors.add(new Puppy(c, 1.0f));
                            break;
                        case "puppy blue":
                            stage.actors.add(new Puppy(c, 0.0f));
                            break;
                        case "lion red":
                            stage.actors.add(new Lion(c, 1.0f));
                            break;
                        case "lion blue":
                            stage.actors.add(new Lion(c, 0.0f));
                            break;
                        case "bunny red":
                            stage.actors.add(new Rabbit(c, 1.0f));
                            break;
                        case "bunny blue":
                            stage.actors.add(new Rabbit(c, 0.0f));
                            break;
                    }
                }
            }
        } catch (IOException e) {
        }
        return stage;
    }
}