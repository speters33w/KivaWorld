package kivaworld;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import edu.duke.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FloorMap {
    private static final char KIVA_REPRESENTATION = 'K';
    private char[][] map;
    private Point initialKivaLocation;
    private Point podLocation;
    private Point dropZoneLocation;

    public FloorMap(String inputMap) {
        this.map = this.populateMap(inputMap);
    }

    public FloorMapObject getObjectAtLocation(Point location) {
        if (location.getX() < 0) {
            throw new InvalidFloorMapLocationException(String.format("Cannot access a negative column: %d", location.getX()));
        } else if (location.getX() > this.map[0].length - 1) {
            throw new InvalidFloorMapLocationException(String.format("Cannot access beyond max column (%d): %d", this.map[0].length - 1, location.getX()));
        } else if (location.getY() < 0) {
            throw new InvalidFloorMapLocationException(String.format("Cannot access a negative row: %d", location.getY()));
        } else if (location.getY() > this.map.length - 1) {
            throw new InvalidFloorMapLocationException(String.format("Cannot access beyond max row (%d): %d", this.map.length - 1, location.getY()));
        } else {
            Optional<FloorMapObject> obj = FloorMapObject.fromChar(this.map[location.getY()][location.getX()]);
            return (FloorMapObject)obj.orElseThrow(() -> {
                return new InvalidMapLayoutException(String.format("Unrecognized MapFloorObject char representation at (%d, %d): '%c'", location.getX(), location.getY(), this.map[location.getY()][location.getX()]));
            });
        }
    }

    public Point getInitialKivaLocation() {
        return new Point(this.initialKivaLocation.getX(), this.initialKivaLocation.getY());
    }

    public Point getPodLocation() {
        return new Point(this.podLocation.getX(), this.podLocation.getY());
    }

    public Point getDropZoneLocation() {
        return new Point(this.dropZoneLocation.getX(), this.dropZoneLocation.getY());
    }

    public int getMinColNum() {
        return 0;
    }

    public int getMaxColNum() {
        return this.map[0].length - 1;
    }

    public int getMinRowNum() {
        return 0;
    }

    public int getMaxRowNum() {
        return this.map.length - 1;
    }

    public String toString() {
        StringBuilder mapStr = new StringBuilder();

        for(int y = 0; y < this.map.length; ++y) {
            for(int x = 0; x < this.map[y].length; ++x) {
                if (x == this.initialKivaLocation.getX() && y == this.initialKivaLocation.getY()) {
                    mapStr.append('K');
                } else {
                    mapStr.append(this.map[y][x]);
                }
            }

            if (y < this.map.length - 1) {
                mapStr.append(System.lineSeparator());
            }
        }

        return mapStr.toString();
    }

    private char[][] populateMap(String inputMap) {
        boolean foundKiva = false;
        boolean foundPod = false;
        boolean foundDropZone = false;
        List<String> rowList = this.readAndValidateMapRows(inputMap);
        char[][] newMap = new char[rowList.size()][];

        for(int rowNum = 0; rowNum < rowList.size(); ++rowNum) {
            String line = (String)rowList.get(rowNum);
            newMap[rowNum] = new char[line.length()];

            for(int colNum = 0; colNum < newMap[rowNum].length; ++colNum) {
                char mapChar = line.charAt(colNum);
                if ('K' == mapChar) {
                    foundKiva = this.foundKivaLocation(colNum, rowNum, foundKiva);
                    mapChar = FloorMapObject.EMPTY.toChar();
                } else {
                    Optional<FloorMapObject> mapObjectOptional = FloorMapObject.fromChar(mapChar);
                    if (!mapObjectOptional.isPresent()) {
                        throw new InvalidMapLayoutException(String.format("Unrecognized MapFloorObject char representation at (%d, %d): '%c'", colNum, rowNum, mapChar));
                    }

                    FloorMapObject mapObj = (FloorMapObject)mapObjectOptional.get();
                    if (FloorMapObject.POD == mapObj) {
                        foundPod = this.foundPodLocation(colNum, rowNum, foundPod);
                    } else if (FloorMapObject.DROP_ZONE == mapObj) {
                        foundDropZone = this.foundDropZoneLocation(colNum, rowNum, foundDropZone);
                    }
                }

                newMap[rowNum][colNum] = mapChar;
            }
        }

        if (!foundKiva) {
            throw new InvalidMapLayoutException("Didn't find a Kiva!");
        } else if (!foundPod) {
            throw new InvalidMapLayoutException("Didn't find a Pod!");
        } else if (!foundDropZone) {
            throw new InvalidMapLayoutException("Didn't find a Drop Zone!");
        } else {
            return newMap;
        }
    }

    private List<String> readAndValidateMapRows(String inputMap) {
        int rowLength = 0;
        int rowNum = -1;
        List<String> rowList = new ArrayList();
        String[] var5 = inputMap.split("\r?\n");
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String line = var5[var7];
            ++rowNum;
            if (rowNum == 0) {
                rowLength = line.length();
            } else if (line.length() != rowLength) {
                throw new InvalidMapLayoutException(String.format("Previously encountered row(s) of length %d, but row %d is of length %d", rowLength, rowNum, line.length()));
            }

            rowList.add(line);
        }

        return rowList;
    }

    private boolean foundKivaLocation(int colNum, int rowNum, boolean foundKivaPreviously) {
        if (foundKivaPreviously) {
            throw new InvalidMapLayoutException(String.format("Found a second Kiva at (%d, %d)", colNum, rowNum));
        } else {
            this.initialKivaLocation = new Point(colNum, rowNum);
            return true;
        }
    }

    private boolean foundPodLocation(int colNum, int rowNum, boolean foundPodPreviously) {
        if (foundPodPreviously) {
            throw new InvalidMapLayoutException(String.format("Found a second POD at (%d, %d)", colNum, rowNum));
        } else {
            this.podLocation = new Point(colNum, rowNum);
            return true;
        }
    }

    private boolean foundDropZoneLocation(int colNum, int rowNum, boolean foundDropZonePreviously) {
        if (foundDropZonePreviously) {
            throw new InvalidMapLayoutException(String.format("Found a second DROP_ZONE at (%d, %d)", colNum, rowNum));
        } else {
            this.dropZoneLocation = new Point(colNum, rowNum);
            return true;
        }
    }
}

