package model;

/*
 * Represents a rectangular falling note that the user
 */

import java.awt.*;

import static model.Level.MAX_HEIGHT;

public class Note {
    public static final int RECT_WIDTH = (Level.MAX_WIDTH / 4) - (Level.MAX_WIDTH / 4) / 10;
    public static final int RECT_HEIGHT = 40;
    public static final int SPEED = 7;
    public static final Color COLOR = new Color(0,0,0);

    protected int xcoord;
    protected int ycoord;
    protected int width;
    protected int height;
    protected int addDelay;


    // constructs a note
    // EFFECTS: places a rectangular note at (x, y)
    // with width of RECT_WIDTH and height of RECT_HEIGHT
    public Note(int x, int delay) {
        xcoord = x;
        ycoord = 0;
        width = RECT_WIDTH;
        height = RECT_HEIGHT;
        addDelay = delay;
    }

    // EFFECTS: returns the x-coordinate of the note
    public int getNoteXcoord() {
        return xcoord;
    }

    // EFFECTS: returns the y-coordinate of the note
    public int getNoteYcoord() {
        return ycoord;
    }

    // MODIFIES: this
    // EFFECTS: sets the y coordinate to y
    public void setYcoord(int y) {
        ycoord = y;
    }

    // EFFECTS: returns the width of the note
    public int getNoteWidth() {
        return width;
    }

    // EFFECTS: returns the height of the note
    public int getNoteHeight() {
        return height;
    }

    // MODIFIES: this
    // EFFECTS: sets the addDelay of this note to delay
    public void setAddDelay(int delay) {
        addDelay = delay;
    }

    // EFFECTS: returns addDelay of this note
    public int getAddDelay() {
        return addDelay;
    }

    // updates the y-coordinate of the note on clock tick
    // MODIFIES: this
    // EFFECTS: moves the note by SPEED (down the screen)
    public void updateNote() {
        ycoord += SPEED;
    }

    // checks if the y-coordinate of the note is out of bounds
    // EFFECTS: returns true if the note's y-coordinate is out of bounds
    public boolean isOutOfBounds() {
        return ycoord > MAX_HEIGHT;
    }
}
