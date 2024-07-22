package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

// most packages/classes have code adapted from SpaceInvaders and JsonSerializationDemo
// SI: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
// JSON: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
 * Represents the Main Menu, where the user can press a button to determine what happens next
 */

public class MenuPanel extends JFrame implements ActionListener {
    public static final int HEIGHT = 400;
    public static final int WIDTH = 400;
    public static final String PROGRESS = "The highest level you have beaten so far is level: ";
    public static final String CONTROLS = "Use the a, s, d, and f keys respectively for each indicator";
    public static final String SCORES = "Your previous scores are: ";


    private JPanel panel;
    private JLabel label;
    private JLabel displayLabel;
    private JTextField field;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton controls;
    private JButton showScores;
    private JButton saveGame;
    private JButton loadGame;
    private JButton playGame;
    private JButton quit;

    private static final String JSON_STORE = "./data/gameState.json";

    private GameState gameState;
    private int notesToPlay;

    // initializes the game's main menu, with buttons for actions
    public MenuPanel(GameState gameState) {
        super("Game Menu");
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new FlowLayout());
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.gameState = gameState;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        label = new JLabel(PROGRESS + gameState.getLevelProgress());
        displayLabel = new JLabel("");

        setupButtons();
        setupPanel();

        add(panel);
        pack();
        centreOnScreen();
        setVisible(true);
        setResizable(false);
    }

    // initialize buttons for the menu panel
    private void setupButtons() {
        controls = new JButton("Controls");
        controls.setActionCommand("Controls");
        controls.addActionListener(this);

        // view scores button
        showScores = new JButton("Display Scores");
        showScores.setActionCommand("Scores");
        showScores.addActionListener(this);

        // save game progress button
        saveGame = new JButton("Save Game");
        saveGame.setActionCommand("Save");
        saveGame.addActionListener(this);

        // load game progress button
        loadGame = new JButton("Load Game");
        loadGame.setActionCommand("Load");
        loadGame.addActionListener(this);

        // play game button
        playGame = new JButton("Play Game");
        playGame.setActionCommand("Play");
        playGame.addActionListener(this);

        // quit application button
        quit = new JButton("Quit");
        quit.setActionCommand("Quit");
        quit.addActionListener(this);
    }

    // initializes panel by adding buttons and labels
    private void setupPanel() {
        panel.add(Box.createRigidArea(new Dimension(1, 1)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(10, 1)));
        panel.add(controls);
        panel.add(Box.createRigidArea(new Dimension(10, 1)));
        panel.add(showScores);
        panel.add(Box.createRigidArea(new Dimension(10, 1)));
        panel.add(saveGame);
        //panel.add(Box.createRigidArea(new Dimension(1, 10)));
        panel.add(loadGame);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(playGame);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(quit);
        panel.add(Box.createRigidArea(new Dimension(30, 10)));
        panel.add(displayLabel);
    }

    // EFFECTS: processes the button that is pressed
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Controls")) {
            if (displayLabel.getText().equals(CONTROLS)) {
                displayLabel.setText("");
            } else {
                displayLabel.setText(CONTROLS);
            }
        } else if (e.getActionCommand().equals("Scores")) {
            if (gameState.getScores().get(1) == null) {
                displayLabel.setText("No scores yet");
            } else {
                List<Integer> scores = gameState.getScores().get(1);
                displayLabel.setText(SCORES + displayScores(scores));
            }
        } else if (e.getActionCommand().equals("Save")) {
            saveGameState();
            displayLabel.setText("Game saved!");
        } else if (e.getActionCommand().equals("Load")) {
            loadGameState();
            displayLabel.setText("Game loaded!");
        } else if (e.getActionCommand().equals("Play")) {
            panel.removeAll(); // resets the JPanel
            panel.revalidate();
            panel.repaint();

            panel.setLayout(new FlowLayout());

            JButton start = new JButton("Start");
            start.setActionCommand("Start");
            start.addActionListener(this);

            field = new JTextField(2);

            JLabel notesLabel = new JLabel("How many notes would you like this level to have?");

            panel.add(notesLabel);
            panel.add(field);
            panel.add(start);
        } else if (e.getActionCommand().equals("Quit")) {
            printLogsToConsole(EventLog.getInstance());
            System.exit(0);
        } else if (e.getActionCommand().equals("Start")) {
            notesToPlay = Integer.parseInt(field.getText());
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            dispose();
            new PlayGame(gameState, notesToPlay);
        }
    }

    public void printLogsToConsole(EventLog e) {
        for (Event next : e) {
            System.out.println(next.toString());
        }
    }

    // EFFECTS: saves the level to file
    public void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameState);
            jsonWriter.close();
            System.out.println("Saved game state " + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads level from file
    public void loadGameState() {
        try {
            gameState = jsonReader.read();
            System.out.println("Loaded level" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: produces a string of scores
    public String displayScores(List<Integer> scores) {
        String s = "";
        for (Integer score : scores) {
            s = s.concat(score.toString()).concat(", ");
        }
        return s;
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}


