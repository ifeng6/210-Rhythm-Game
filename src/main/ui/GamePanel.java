package ui;

import model.Indicator;
import model.Level;
import model.Note;

import javax.swing.*;
import java.awt.*;

// most packages/classes have code adapted from SpaceInvaders and JsonSerializationDemo
// SI: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
// JSON: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
 * Represents the GamePanel where notes, indicators, and dividers are drawn on
 */

public class GamePanel extends JPanel {
    private static final Color DIVIDER_COLOR = Color.darkGray;
    private static final String GAME_OVER = "Press 'm' to return to the main menu";

    private Level level;

    // constructs the game panel
    public GamePanel(Level level) {
        setPreferredSize(new Dimension(Level.MAX_WIDTH, Level.MAX_HEIGHT));
        setBackground(Color.lightGray);
        this.level = level;
    }

    // MODIFIES: g
    // EFFECTS: paints the components onto g
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (!level.isCounterValid() || level.getActiveNotes().isEmpty()) {
            processGameOver(g);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws dividers, indicators, and notes onto g
    private void drawGame(Graphics g) {
        drawDividers(g);
        drawIndicators(g);
        drawNotes(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the divider lines onto g
    private void drawDividers(Graphics g) {
        Color savedColor = g.getColor();
        g.setColor(DIVIDER_COLOR);
        g.drawLine(Level.MAX_WIDTH / 4, 0, Level.MAX_WIDTH / 4, Level.MAX_HEIGHT);
        g.drawLine((Level.MAX_WIDTH / 4) * 2, 0, (Level.MAX_WIDTH / 4) * 2, Level.MAX_HEIGHT);
        g.drawLine((Level.MAX_WIDTH / 4) * 3, 0, (Level.MAX_WIDTH / 4) * 3, Level.MAX_HEIGHT);
        g.setColor(savedColor);
    }

    // MODIFIES: g
    // EFFECTS: draws the indicators onto g
    private void drawIndicators(Graphics g) {
        for (Indicator next : level.getIndicators()) {
            drawIndicator(next, g);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws indicator (hollow red rectangle) on g
    private void drawIndicator(Indicator i, Graphics g) {
        Color savedColor = g.getColor();
        g.setColor(Indicator.BORDER_COLOR);
        g.drawRect(i.getXcoord(), i.getYcoord(), i.getWidth(), i.getHeight());
        g.setColor(savedColor);
    }

    // MODIFIES: g
    // EFFECTS: draws the notes onto g
    private void drawNotes(Graphics g) {
        for (Note next : level.getActiveNotes()) {
            drawNote(next, g);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws note (red rectangle) onto g
    private void drawNote(Note note, Graphics g) {
        Color savedColor = g.getColor();
        g.setColor(Note.COLOR);
        g.fillRect(note.getNoteXcoord(), note.getNoteYcoord(), Note.RECT_WIDTH, Note.RECT_HEIGHT);
        g.setColor(savedColor);
    }

    // MODIFIES: g
    // EFFECTS: prints a string onto g when the game is over
    private void processGameOver(Graphics g) {
        Color savedColor = g.getColor();
        g.setColor(new Color(0,0,0));
        g.setFont(new Font("Arial", 20, 20));
        int stringWidth = g.getFontMetrics().stringWidth(GAME_OVER);
        g.drawString(GAME_OVER, (Level.MAX_WIDTH - stringWidth) / 2, Level.MAX_HEIGHT / 2);
        g.setColor(savedColor);
    }
}
