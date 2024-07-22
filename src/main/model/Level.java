package model;

import ui.MenuPanel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents a level with indicators and notes.
 */

public class Level {
    public static final int MAX_HEIGHT = 800;
    public static final int MAX_WIDTH = 400;
    public static final int COUNTER = 3;
    public static final int INITIAL_SCORE = 0;
    public static final int POINTS_PER_NOTE = 10;

    private int levelNumber;
    private int counter;
    private int score;
    private boolean gameOverLogged;
    private List<Integer> scores;
    private List<Note> activeNotes;
    private List<Note> notesToBeAdded;
    private List<Indicator> indicators;

    // constructs a level with empty notes, an empty list of indicators,
    // a counter of COUNTER, and score of INITIAL_SCORE, an empty list of scores, and level number of 1
    public Level() {
        counter = COUNTER;
        score = INITIAL_SCORE;
        scores = new ArrayList<>();
        activeNotes = new ArrayList<>();
        notesToBeAdded = new ArrayList<>();
        indicators = new ArrayList<>();
        levelNumber = 1;
        gameOverLogged = false;
    }

    // EFFECTS: returns the number of the level
    public int getLevelNumber() {
        return levelNumber;
    }

    // MODIFIES: this
    // EFFECTS: adds a note to the list of active notes
    public void addActiveNote(Note note) {
        activeNotes.add(note);
        EventLog.getInstance().logEvent(new Event("Added note is now being displayed"));
    }

    // MODIFIES: this
    // EFFECTS: adds a note to the list of notes to be added
    public void addNoteToBeAdded(Note note) {
        notesToBeAdded.add(note);
        EventLog.getInstance().logEvent(new Event("Note added to the current level"));
    }

    // EFFECTS: returns the number of notes in the list of notes
    public int getNotesLength() {
        return activeNotes.size();
    }

    // EFFECTS: returns the list of notes
    public List<Note> getActiveNotes() {
        return activeNotes;
    }

    // EFFECTS: returns the notes that are going to be added to the list of notes
    public List<Note> getNotesToBeAdded() {
        return notesToBeAdded;
    }

    // MODIFIES: this
    // EFFECTS: adds an indicator to the list of indicators
    public void addIndicator(Indicator indicator) {
        indicators.add(indicator);
        EventLog.getInstance().logEvent(new Event("Indicator added to the current level"));
    }

    // EFFECTS: returns the list of indicators
    public List<Indicator> getIndicators() {
        return indicators;
    }

    // EFFECTS: returns the number of indicators in the indicators list
    public int getIndicatorsLength() {
        return indicators.size();
    }

    // EFFECTS: returns the value of the counter
    public int getCounter() {
        return counter;
    }

    // EFFECTS: returns false if the counter is 0 or lower
    public boolean isCounterValid() {
        return counter > 0;
    }

    // MODIFIES: this
    // EFFECTS: decreases counter by 1
    public void decrementCounter() {
        counter -= 1;
        EventLog.getInstance().logEvent(new Event("Counter decreased by 1"));
    }

    // EFFECTS: returns the score
    public int getScore() {
        return score;
    }

    // MODIFIES: this
    // EFFECTS: increases score by points per note
    public void incrementScore() {
        score += POINTS_PER_NOTE;
        EventLog.getInstance().logEvent(new Event("Score increased"));
    }

    // MODIFIES: this
    // EFFECTS: decreases score by the points per note
    public void decrementScore() {
        score -= POINTS_PER_NOTE;
        EventLog.getInstance().logEvent(new Event("Score decreased"));
    }

    // MODIFIES: this
    // EFFECTS: adds a score to a list of scores
    public void addScore(Integer score) {
        scores.add(score);
    }

    // MODIFIES: this
    // EFFECTS: removes all existing scores in the list of scores
    public void clearScores() {
        scores.removeAll(scores);
    }

    // EFFECTS: returns the number of elements in scores list
    public int getScoresSize() {
        return scores.size();
    }

    // MODIFIES: this
    // EFFECTS: makes an event log when the game finishes
    public void logGameOver() {
        if (!gameOverLogged) {
            EventLog.getInstance().logEvent(new Event("All notes removed because the game is over"));
            gameOverLogged = true;
        }
    }

    // MODIFIES: this and all notes in the list of notes
    // EFFECTS: updates all of the notes and adds them to the list of current notes if appropriate
    //          removes the notes that are added to the active notes list
    public void updateNotesToBeAdded() {
        List<Note> notesToRemove = new ArrayList<>();
        for (Note next : notesToBeAdded) {
            if (next.getAddDelay() <= 0) {
                activeNotes.add(next);
                notesToRemove.add(next);
            } else {
                int newAddDelay = next.getAddDelay();
                newAddDelay -= 1;
                next.setAddDelay(newAddDelay);
            }
        }

        notesToBeAdded.removeAll(notesToRemove);
    }

    // MODIFIES: this and all notes in the list of notes
    // EFFECTS: updates all the active notes in the level
    public void updateActiveNotes() {
        List<Note> notesToRemove = new ArrayList<>();
        for (Note next : activeNotes) { // need to add remove note functionality
            next.updateNote();
            if (next.isOutOfBounds()) {
                decrementCounter();
                notesToRemove.add(next);
                EventLog.getInstance().logEvent(new Event("Active note removed because it is out of bounds"));
            }
        }

        activeNotes.removeAll(notesToRemove);
    }

    // EFFECTS: prints out all the scores
    public String displayScores() {
        String s = "";
        for (Object score : scores) {
            s = s.concat(score.toString()).concat(", ");
        }
        return s;
    }

    // MODIFIES: this
    // EFFECTS: handles the key press
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_A) {
            checkCollision(NoteA.POSITION);
        } else if (key == KeyEvent.VK_S) {
            checkCollision(NoteB.POSITION);
        } else if (key == KeyEvent.VK_D) {
            checkCollision(NoteC.POSITION);
        } else if (key == KeyEvent.VK_F) {
            checkCollision(NoteD.POSITION);
        }
    }

    // MODIFIES: this
    // EFFECTS: checks collision for respective key press
    //          if the key press is valid, increments the score, otherwise decrements the score
    public void checkCollision(int lane) {
        java.util.List<Note> notesToRemove = new ArrayList<>();
        for (Note next : getActiveNotes()) {
            if (next.getNoteXcoord() == lane) {
                if (checkNoteWithinIndicator(next)) {
                    notesToRemove.add(next);
                    EventLog e = EventLog.getInstance();
                    e.logEvent(new Event("Active note removed from a well-timed key press"));
                }
            }
        }
        getActiveNotes().removeAll(notesToRemove);

        if (notesToRemove.isEmpty()) {
            decrementScore();
        } else {
            incrementScore();
        }
    }

    // EFFECTS: checks if the note is within the indicator
    //          if it is, returns true, otherwise returns false
    public boolean checkNoteWithinIndicator(Note next) {
        if (next.getNoteYcoord() <= (Indicator.IND_YPOS + Indicator.IND_HEIGHT)
                && (next.getNoteYcoord() + Note.RECT_HEIGHT) >= Indicator.IND_YPOS) {
            return true;
        } else {
            return false;
        }
    }
}
