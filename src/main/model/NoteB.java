package model;

// represents a note on the middle left
public class NoteB extends Note {
    public static final int POSITION = 3 * (Level.MAX_WIDTH / 8) - (Note.RECT_WIDTH / 2);

    // constructs a note with x-coordinate POSITION
    public NoteB(int delay) {
        super(POSITION, delay);
    }
}
