package model;

// represents a note on the right
public class NoteD extends Note {
    public static final int POSITION = 7 * (Level.MAX_WIDTH / 8) - (Note.RECT_WIDTH / 2);

    // constructs a note with x-coordinate POSITION
    public NoteD(int delay) {
        super(POSITION, delay);
    }
}
