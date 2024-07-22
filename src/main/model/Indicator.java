package model;

import java.awt.*;

/*
 * Represents a stationary indicator. When a note overlaps the indicator,
 * the user should press a corresponding key input to successfully match the note.
 */

public class Indicator {
    public static final int IND_WIDTH = (Level.MAX_WIDTH / 4) - (Level.MAX_WIDTH / 4) / 10;
    public static final int IND_HEIGHT = 40;
    public static final int IND_YPOS = Level.MAX_HEIGHT - IND_HEIGHT;
    public static final Color BORDER_COLOR = new Color(255,0,0);

    protected int xcoord;
    protected int ycoord;
    protected int width;
    protected int height;

    // constructs a rectangular stationary indicator at (x, MAX_HEIGHT - 40)
    // with width and height
    public Indicator(int x) {
        xcoord = x;
        ycoord = IND_YPOS;
        width = IND_WIDTH;
        height = IND_HEIGHT;
    }

    // EFFECTS: returns the x-coordinate of the indicator
    public int getXcoord() {
        return xcoord;
    }

    // EFFECTS: returns the y-coordinate of the indicator
    public int getYcoord() {
        return ycoord;
    }

    // EFFECTS: returns the width of the indicator
    public int getWidth() {
        return width;
    }

    // EFFECTS: returns the height of the indicator
    public int getHeight() {
        return height;
    }
}
