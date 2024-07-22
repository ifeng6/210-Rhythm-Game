package persistence;

import model.Event;
import model.EventLog;
import model.GameState;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads game state from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the file and returns the game state
    public GameState read() throws IOException {
        EventLog.getInstance().logEvent(new Event("Game progress loaded"));
        String jsonData = readFile(source);
        JSONObject jsonobject = new JSONObject(jsonData);
        return parseGameState(jsonobject);
    }

    // EFFECTS: reads the file from the given source destination
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game state from JSONObject and returns it
    private GameState parseGameState(JSONObject jsonGameState) {
        GameState gameState = new GameState();
        gameState.setLevelProgress(jsonGameState.getInt("level progress"));

        HashMap<Integer, List<Integer>> scores = parseScores(jsonGameState.getJSONArray("scores"));
        gameState.setScores(scores);

        return gameState;
    }

    // EFFECTS: converts JSONArray scores to hashmap of the scores for each level
    private HashMap<Integer, List<Integer>> parseScores(JSONArray jsonScores) {
        HashMap<Integer, List<Integer>> scores = new HashMap<>();
        for (Object entry : jsonScores) {
            JSONObject jsonLevel = (JSONObject) entry;
            scores.put(jsonLevel.getInt("level"), parseLevelScores(jsonLevel.getJSONArray("scores")));
        }

        return scores;
    }

    // EFFECTS: converts JSONArray of scores for a single level into a list of integers
    private List<Integer> parseLevelScores(JSONArray jsonLevelScores) {
        List<Integer> levelScores = new ArrayList<>();
        for (Object entry : jsonLevelScores) {
            Integer levelScore = (Integer) entry;
            levelScores.add(levelScore);
        }

        return levelScores;
    }
}
