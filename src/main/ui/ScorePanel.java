package ui;

import model.Level;

import javax.swing.*;
import java.awt.*;

// most packages/classes have code adapted from SpaceInvaders and JsonSerializationDemo
// SI: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
// JSON: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
 * Represents the ScorePanel where level information is displayed
 */

public class ScorePanel extends JPanel {
    private static final String NOTES_TEXT = "Notes left: ";
    private static final String COUNTER_TEXT = "Lives: ";
    private static final String SCORE_TEXT = "Score: ";
    public static final int LABEL_HEIGHT = 40;
    private static final int LABEL_WIDTH = Level.MAX_WIDTH;
    private Level level;
    private JLabel notesLeftLabel;
    private JLabel counterLabel;
    private JLabel scoreLabel;

    // EFFECTS: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Level level) {
        this.level = level;

        setBackground(new Color(150,150,150));
        notesLeftLabel = new JLabel(NOTES_TEXT + (level.getNotesLength() + level.getNotesToBeAdded().size()));
        notesLeftLabel.setPreferredSize(new Dimension(LABEL_WIDTH / 4, LABEL_HEIGHT));
        counterLabel = new JLabel(COUNTER_TEXT + level.getCounter());
        counterLabel.setPreferredSize(new Dimension(LABEL_WIDTH / 4, LABEL_HEIGHT));
        scoreLabel = new JLabel(SCORE_TEXT + level.getScore());
        scoreLabel.setPreferredSize(new Dimension(LABEL_WIDTH / 4, LABEL_HEIGHT));
        add(notesLeftLabel);
        add(counterLabel);
        add(scoreLabel);
    }

    // MODIFIES: this
    // EFFECTS: updates the notes remaining, the counter, and the score
    public void update() {
        notesLeftLabel.setText(NOTES_TEXT + (level.getNotesLength() + level.getNotesToBeAdded().size()));
        counterLabel.setText(COUNTER_TEXT + level.getCounter());
        scoreLabel.setText(SCORE_TEXT + level.getScore());
        repaint();
    }

}
