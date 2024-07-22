package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static model.Indicator.*;
import static model.Note.*;
import static org.junit.jupiter.api.Assertions.*;

public class modelTest {
    private Note noteA;
    private Note noteB;
    private Note noteC;
    private Note noteD;
    private Indicator indicatorA;
    private Indicator indicatorB;
    private Indicator indicatorC;
    private Indicator indicatorD;
    private Level level;
    private List<Integer> scores;
    private GameState gameState;

    @BeforeEach
    void setup() {
        noteA = new NoteA(0);
        noteB = new NoteB(0);
        noteC = new NoteC(0);
        noteD = new NoteD(0);
        indicatorA = new IndicatorA();
        indicatorB = new IndicatorB();
        indicatorC = new IndicatorC();
        indicatorD = new IndicatorD();
        level = new Level();
        scores = new ArrayList<Integer>();
        gameState = new GameState();
    }

    @Test
    void testGetNoteXcoord() {
        assertEquals((Level.MAX_WIDTH / 8) - (Note.RECT_WIDTH / 2), noteA.getNoteXcoord());
    }

    @Test
    void testGetNoteYcoord() {
        assertEquals(0, noteA.getNoteYcoord());
    }

    @Test
    void testGetNoteWidth() {
        assertEquals(RECT_WIDTH, noteA.getNoteWidth());
    }

    @Test
    void testGetNoteHeight() {
        assertEquals(RECT_HEIGHT, noteA.getNoteHeight());
    }

    @Test
    void testGetIndicatorXcoord() {
        assertEquals((Level.MAX_WIDTH / 8) - (Indicator.IND_WIDTH / 2), indicatorA.getXcoord());
    }

    @Test
    void testGetIndicatorYcoord() {
        assertEquals(IND_YPOS, indicatorA.getYcoord());
    }

    @Test
    void testGetIndicatorWidth() {
        assertEquals(IND_WIDTH, indicatorA.getWidth());
    }

    @Test
    void testGetIndicatorHeight() {
        assertEquals(IND_HEIGHT, indicatorA.getHeight());
    }

    @Test
    void testUpdateNote() {
        noteA.updateNote();
        assertEquals(SPEED, noteA.getNoteYcoord());
    }

    @Test
    void testIsOutOfBounds() {
        noteA.setYcoord(Level.MAX_HEIGHT - (SPEED - 1));
        assertFalse(noteA.isOutOfBounds());
        noteA.updateNote();
        assertTrue(noteA.isOutOfBounds());
    }

    @Test
    void testGetCounter() {
        assertEquals(Level.COUNTER, level.getCounter());
    }

    @Test
    void testAddNote() {
        level.addActiveNote(noteA);
        assertEquals(1, level.getNotesLength());
        level.addActiveNote(noteA);
        assertEquals(2, level.getNotesLength());
    }


    @Test
    void testUpdateLevel() {
        Note note2;
        note2 = new NoteA(0);
        assertEquals(Level.COUNTER, level.getCounter());
        note2.updateNote();
        level.addActiveNote(noteA);
        level.addActiveNote(note2);
        level.updateActiveNotes();
        assertEquals(SPEED*2, note2.getNoteYcoord());
        assertEquals(SPEED, noteA.getNoteYcoord());
        note2.setYcoord(Level.MAX_HEIGHT - (SPEED - 1)); // sets note so that one update will make it out of bounds
        level.updateActiveNotes();
        assertEquals(Level.COUNTER - 1, level.getCounter());
    }

    @Test
    void testisCounterValid() {
        assertTrue(level.isCounterValid());
        for (int i = 0; i < Level.COUNTER; i++) {
            level.decrementCounter();
        }
        assertFalse(level.isCounterValid());
    }

    @Test
    void testDecrementCounter() {
        level.decrementCounter();
        assertEquals(Level.COUNTER - 1, level.getCounter());
    }

    @Test
    void testGetScore() {
        assertEquals(0, level.getScore());
    }

    @Test
    void testScoreChange() {
        level.incrementScore();
        assertEquals(Level.POINTS_PER_NOTE, level.getScore());
        level.decrementScore();
        assertEquals(0, level.getScore());
    }

    @Test
    void testAddIndicator() {
        level.addIndicator(indicatorA);
        assertEquals(1, level.getIndicatorsLength());
    }

    @Test
    void testGetIndicatorsLength() {
        assertEquals(0, level.getIndicatorsLength());
        level.addIndicator(indicatorA);
        assertEquals(1, level.getIndicatorsLength());
    }

    @Test
    void testAddScore() {
        assertEquals(0, level.getScoresSize());
        level.addScore(5);
        assertEquals(1, level.getScoresSize());
    }

    @Test
    void testClearScores() {
        level.addScore(10);
        level.addScore(7);
        assertEquals(2, level.getScoresSize());
        level.clearScores();
        assertEquals(0, level.getScoresSize());
    }

    @Test
    void testDisplayScores() {
        level.addScore(10);
        assertEquals("10, ", level.displayScores());
    }


    @Test
    void testGetLevelProgress() {
        assertEquals(0, gameState.getLevelProgress());
        gameState.setLevelProgress(5);
        assertEquals(5, gameState.getLevelProgress());
    }

    @Test
    void testGetScores() {
        HashMap<Integer, List<Integer>> levelScores = gameState.getScores();
        assertEquals(0, levelScores.size());
        gameState.addLevelScore(1, 5);
        assertEquals(1, levelScores.size());
        gameState.addLevelScore(1, 6);
        assertEquals(1, levelScores.size());
        assertEquals(2, levelScores.get(1).size());
    }

    @Test
    void testSetScores() {
        HashMap<Integer, List<Integer>> levelScores = new HashMap<>();
        List<Integer> someScores = new ArrayList<>();
        someScores.add(1);
        someScores.add(2);
        levelScores.put(5, someScores);

        gameState.setScores(levelScores);
        assertEquals(1, gameState.getScores().size());
    }

    @Test
    void testToJson() {
        gameState.setLevelProgress(5);
        gameState.addLevelScore(1, 5);
        JSONObject testJSON = gameState.toJson();

        assertEquals(testJSON.get("level progress"), gameState.getLevelProgress());

        JSONArray testJSONArray = (JSONArray) testJSON.get("scores");
        assertEquals(1, testJSONArray.length());
    }

    @Test
    void testGetNotes() {
        assertEquals(0, level.getActiveNotes().size());
        level.addActiveNote(noteA);
        assertEquals(1, level.getActiveNotes().size());
    }

    @Test
    void testGetIndicators() {
        assertEquals(0, level.getIndicators().size());
        level.addIndicator(indicatorA);
        assertEquals(1, level.getIndicators().size());
    }

    @Test
    void testUpdateNotesToBeAdded() {
        noteA.setAddDelay(0);
        noteB.setAddDelay(1);
        noteC.setAddDelay(2);

        level.addNoteToBeAdded(noteA);
        level.addNoteToBeAdded(noteB);
        level.addNoteToBeAdded(noteC);

        level.updateNotesToBeAdded();
        assertEquals(2, level.getNotesToBeAdded().size());
        assertEquals(1, level.getActiveNotes().size());
    }
}
