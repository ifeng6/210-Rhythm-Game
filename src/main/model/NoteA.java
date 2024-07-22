package model;

// represents a note on the far left
public class NoteA extends Note {
    public static final int POSITION = (Level.MAX_WIDTH / 8) - (Note.RECT_WIDTH / 2);

    // constructs a note with x-coordinate POSITION
    public NoteA(int delay) {
        super(POSITION, delay);
    }
}
