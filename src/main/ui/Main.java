package ui;

import model.GameState;

public class Main {

    public static void main(String[] args) {
        GameState gameState = new GameState();
        new MenuPanel(gameState);
    }
}
