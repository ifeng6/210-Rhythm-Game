package persistence;

import model.GameState;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameState gameState = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameState.json");
        try {
            GameState gameState = reader.read();
            assertEquals(0, gameState.getLevelProgress());
            HashMap<Integer, List<Integer>> testMap = gameState.getScores();
            assertEquals(0, testMap.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameState.json");
        try {
            GameState gameState = reader.read();
            assertEquals(5, gameState.getLevelProgress());

            HashMap<Integer, List<Integer>> testMap = gameState.getScores();
            assertEquals(2, testMap.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
