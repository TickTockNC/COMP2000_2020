If you must depart from it, then you must describe why this was necessary in a file DEPARTURES1.md which names the pattern and the ways you departed from it, as well as explains why you made each change.

Depatures

Grid:
1. cellAtPoint(Point p)
    The Composite pattern was half refractored for this section, however it couldn't be fully implemented as "this.itertator" refers to type Grid which can't be coverted easily to the CellIterator type.
    
    
2. doToEachCell(Consumer<Cell> func)
    Similarly to cellAtPoint(), doToEachCell can also only half implement the Composite pattern as it also uses "this.iterator" which disallows Frid to be converted easily to the CellIterator type.
        
3. cellsInRange(char c1, int r1, char c2, int r2)
    This function needs the nested loop to work with the variables its using, thus an iterator can't be used.

4. getRadius(Cell from, int size, boolean considerTime)
    getRadius departs from the Composite pattern as it uses a Set which is incompatible with Arrays, Lists and ArrayLists. Moreover, the array is instiated within the for loop, preventing us from creating an iterator.

StageReader:
1. Iterator<String> tempKey = props.stringPropertyNames().iterator();
    As with getRadius, tempKey also implements a Set which disallows it from using any type specific iterator. While it can still use a generic iterator, this still departs from the Composite pattern which requries the implementation of specific type iterators.

********

Changes made from Refractoring to Composite Pattern

Actor:
-Paint

Grid:
-Grid
-CellAtPoint
-doToEachCell
-paintOverlay

LeftMostMove:
-chooseNextLoc

Stage:
-Paint
    -> tempAIMove
    -> tempChoosingActors
    -> tempPaintActors
    -> tempAgent
    -> tempMenuOverlay
-getClearRadius
    -> tempClearRadius
-mouseClicked
    -> tempChoosingActor
    -> tempClicked
    -> tempMenuItem
    -> tempCellOverlay
-actorAt
    -> tempActors

StageReader:
-Stage
    -> tempKey
    -> tempCells

New Classes
-ActorIterator
-CellIterator
-MenuItemIterator
-PolygonIterator
