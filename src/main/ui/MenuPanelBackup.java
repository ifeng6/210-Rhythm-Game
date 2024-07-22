/*
package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Platform.exit;

// most packages have code adapted from SpaceInvaders and JsonSerializationDemo
// SI: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/
// JSON: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class MenuPanelBackup {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 500;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/gameState.json";

    private GameState gameState;
    private Level level;
    private Note note;
    private Indicator indicator;
    private Scanner input;

    // initializes a level and runs the game
    public MenuPanelBackup() {
        note = new NoteA();
        indicator = new IndicatorA();
        level = new Level();
        level.addIndicator(indicator);
        gameState = new GameState();
        gameState.addLevelScore(1, 0);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runGame(level, gameState);
    }

    // EFFECTS: runs the game
    public void runGame(Level level, GameState gameState) {
        boolean isGameOver = false;
        String command = null;
        int commandInt = 0;
        input = new Scanner(System.in);

        System.out.println("Welcome to the game!");

        while (!isGameOver) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                isGameOver = true;
            } else if (command.equals("g")) {
                startGame(level);
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
        exit();
    }

    // Displays the start menu where the user can decide what to do next
    public void displayMenu() {
        System.out.println("The highest level you have beaten so far is " + gameState.getLevelProgress());
        System.out.println("Please select an option from below");
        System.out.println("\nc --> view a list of the controls");
        System.out.println("\ng --> start game");
        System.out.println("\ns --> save current game");
        System.out.println("\nl --> load existing game");
        System.out.println("\nq --> quit game");
        System.out.println("\n1 --> display the scores from level 1");
    }

    // MODIFIES: this and level
    // EFFECTS: starts the game, which ends when the counter < 0
    public void startGame(Level level) {
        System.out.println("Input the number of notes you would like: ");
        String command = null;
        int commandInt;

        command = input.next();
        commandInt = Integer.parseInt(command);

        for (int i = 0; i < commandInt; i++) { // this will be the random part, adding note 1 to note 4
            level.addActiveNote(note);
        }

        // Here the game starts and ends when the counter < 0
        while (level.isCounterValid()) {
            System.out.println("Allowed misses left: " + level.getCounter());
            System.out.println("Current score: " + level.getScore());
            level.updateActiveNotes();
        }

        level.addScore(level.getScore());
        processGameOver();
    }

    // adds score to history of scores
    public void processGameOver() {
        gameState.addLevelScore(level.getLevelNumber(), level.getScore());
        System.out.println("GameOver! Your score is: " + level.getScore());
    }


    // EFFECTS: processes the given command
    //          if the command is "c", then the controls are displayed
    //          if the command is "s", then the current instance is saved
    //          if the command is "l", then a previous instance is loaded
    public void processCommand(String command) {
        if (command.equals("c")) {
            System.out.println("Use the 'A' key for indicator1");
        } else if (command.equals("s")) {
            saveGameState();
        } else if (command.equals("l")) {
            loadGameState();
        } else if (command.equals("d")) {
            System.out.println(level.displayScores());
        } else if (command.equals("1")) {
            List<Integer> levelScores = gameState.getScores().get(1);
            System.out.println(displayScores(levelScores));
        }
    }

    public String displayScores(List<Integer> scores) {
        String s = "";
        for (Integer score : scores) {
            s = s.concat(score.toString()).concat(", ");
        }
        return s;
    }

    // EFFECTS: saves the level to file
    public void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameState);
            jsonWriter.close();
            System.out.println("Saved level " + " to " + JSON_STORE);
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
}


*/
