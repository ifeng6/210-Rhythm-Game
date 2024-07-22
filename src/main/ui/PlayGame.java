package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

// most packages/classes have code adapted from SpaceInvaders and JsonSerializationDemo
// SI: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
// JSON: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
 * Plays the rhythm game
 */

public class PlayGame extends JFrame {

    private static final int TIME = 20;
    public static final int ADJUSTMENT = 15;
    public static final int HEIGHT = 2 * ScorePanel.LABEL_HEIGHT + Level.MAX_HEIGHT + ADJUSTMENT;
    public static final int WIDTH = 400 + ADJUSTMENT;

    private GameState gameState;
    private Level level;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private int input;

    // constructs the game to be played on the menu frame
    public PlayGame(GameState gameState, int input) {
        super("Rhythm Game");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupLevel(input);

        this.gameState = gameState;
        gamePanel = new GamePanel(level);
        scorePanel = new ScorePanel(level);
        this.input = input;

        add(scorePanel, BorderLayout.NORTH);
        add(gamePanel);
        addKeyListener(new KeyHandler());
        addTimer();

        pack();
        setVisible(true);
        centreOnScreen();
        setResizable(false);
    }

    // EFFECTS: as of right now, only 1 level exists, so this sets this level up
    private void setupLevel(Integer input) {
        level = new Level();
        int heightAdjustment = 0;

        level.addIndicator(new IndicatorA());
        level.addIndicator(new IndicatorB());
        level.addIndicator(new IndicatorC());
        level.addIndicator(new IndicatorD());

        for (int i = 0; i < input; i++) {
            Random rand = new Random(); // initialise a random number from 1 to 4
            int n = rand.nextInt(4);
            n += 1;

            if (n == 1) {
                level.addNoteToBeAdded(new NoteA(heightAdjustment));
            } else if (n == 2) {
                level.addNoteToBeAdded(new NoteB(heightAdjustment));
            } else if (n == 3) {
                level.addNoteToBeAdded(new NoteC(heightAdjustment));
            } else if (n == 4) {
                level.addNoteToBeAdded(new NoteD(heightAdjustment));
            }

            heightAdjustment += 20;
        }
    }

    // EFFECTS: sets up a timer that does actions every TIME milliseconds
    private void addTimer() {
        Timer t = new Timer(TIME, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                level.updateNotesToBeAdded();
                level.updateActiveNotes();
                gamePanel.repaint();
                scorePanel.update();
                checkGameOver();
            }
        });

        t.start();
    }

    // Checks if the game is over and handles it appropriately
    private void checkGameOver() {
        if (!level.isCounterValid()) {
            level.getNotesToBeAdded().clear();
            level.getActiveNotes().clear();
            level.logGameOver();
        }
    }

    // class that handles key presses
    private class KeyHandler extends KeyAdapter {

        // EFFECTS: handles the key press with an appropriate action
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_M && (!level.isCounterValid() || level.getActiveNotes().isEmpty())) {
                gameState.addLevelScore(level.getLevelNumber(), level.getScore());

                if (level.isCounterValid()) {
                    gameState.setLevelProgress(level.getLevelNumber());
                }

                dispose();

                new MenuPanel(gameState);
            } else {
                level.keyPressed(key);
            }
        }
    }

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}
