package model;

// represents a note on the middle right
public class NoteC extends Note {
    public static final int POSITION = 5 * (Level.MAX_WIDTH / 8) - (Note.RECT_WIDTH / 2);

    // constructs a note with x-coordinate POSITION
    public NoteC(int delay) {
        super(POSITION, delay);
    }
}
