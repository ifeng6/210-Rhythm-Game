package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this is a player's account and stores all the information related to the current game state

public class GameState implements Writable {
    private Integer levelProgress;
    private HashMap<Integer, List<Integer>> scores; // level number key and associated value of scores for that level

    // constructs a new game state with no progress and no scores
    public GameState() {
        levelProgress = 0;
        scores = new HashMap<>();
    }

    // EFFECTS: returns the level progress of the player
    public Integer getLevelProgress() {
        return levelProgress;
    }

    // MODIFIES: this
    // EFFECTS: sets the level progress of the player
    public void setLevelProgress(Integer levelProgress) {
        this.levelProgress = levelProgress;
    }

    // EFFECTS: returns the hashmap of the scores of each level
    public HashMap<Integer, List<Integer>> getScores() {
        return scores;
    }

    // MODIFIES: this
    // EFFECTS: sets the history of scores on every level to the given hashmap
    public void setScores(HashMap<Integer, List<Integer>> scores) {
        this.scores = scores;
    }

    // MODIFIES: this
    // EFFECTS: if this level number does not exist, makes a new entry with that level number and add score
    //          else adds the given score to the specified levelNumber
    public void addLevelScore(int levelNumber, int score) {
        if (!scores.containsKey(levelNumber)) {
            List<Integer> newScoresList = new ArrayList<>();
            newScoresList.add(score);
            scores.put(levelNumber, newScoresList);
        } else {
            List<Integer> existingScoresList = scores.get(levelNumber);
            existingScoresList.add(score);
        }
    }

    // EFFECTS: puts the GameState to json, converts all game state information to a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonGameState = new JSONObject();
        jsonGameState.put("level progress", levelProgress);
        jsonGameState.put("scores", parseScores(scores));
        return jsonGameState;
    }

    // EFFECTS: converts the scores to a JSONArray
    public JSONArray parseScores(HashMap<Integer, List<Integer>> scores) {
        JSONArray jsonScores = new JSONArray();
        for (Map.Entry<Integer, List<Integer>> set : scores.entrySet()) {
            int level = set.getKey();
            List<Integer> levelScores = set.getValue();

            JSONObject jsonLevel = parseLevel(level, levelScores);
            jsonScores.put(jsonLevel);
        }

        return jsonScores;
    }

    // EFFECTS: converts a level entry into a JSONObject
    public JSONObject parseLevel(int level, List<Integer> levelScores) {
        JSONObject jsonLevel = new JSONObject();
        jsonLevel.put("level", level);
        jsonLevel.put("scores", parseLevelScores(levelScores));

        return jsonLevel;
    }

    // EFFECTS: parses the scores of each level to a JSONArray
    public JSONArray parseLevelScores(List<Integer> levelScores) {
        JSONArray jsonLevelScores = new JSONArray();

        for (int score : levelScores) {
            jsonLevelScores.put(score);
        }

        return jsonLevelScores;
    }
}
