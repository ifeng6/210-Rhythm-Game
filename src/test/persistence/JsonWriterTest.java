package persistence;

import model.GameState;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            GameState gameState = new GameState();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            GameState gameState = new GameState();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameState.json");
            writer.open();
            writer.write(gameState);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGameState.json");
            gameState = reader.read();
            assertEquals(0, gameState.getLevelProgress());

            HashMap<Integer, List<Integer>> testMap = gameState.getScores();
            assertEquals(0, testMap.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            GameState gameState = new GameState();
            HashMap<Integer, List<Integer>> testMap = new HashMap<>();

            List<Integer> scoresForLevelOne = new ArrayList<>();
            List<Integer> scoresForLevelTwo = new ArrayList<>();

            scoresForLevelOne.add(1);
            scoresForLevelOne.add(2);

            scoresForLevelTwo.add(3);

            gameState.setLevelProgress(5);

            testMap.put(1, scoresForLevelOne);
            testMap.put(2, scoresForLevelTwo);

            gameState.setScores(testMap);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameState.json");
            writer.open();
            writer.write(gameState);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameState.json");
            gameState = reader.read();

            assertEquals(5, gameState.getLevelProgress());
            assertEquals(2, testMap.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}