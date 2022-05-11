//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package kivaworld;

import edu.duke.Point;

public enum FacingDirection {
    UP(new Point(0, -1)),
    RIGHT(new Point(1, 0)),
    DOWN(new Point(0, 1)),
    LEFT(new Point(-1, 0));

    private Point delta;

    private FacingDirection(Point delta) {
        this.delta = delta;
    }

    public Point getDelta() {
        return this.delta;
    }
}
