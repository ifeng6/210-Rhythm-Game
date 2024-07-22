package model;

public class IndicatorC extends Indicator {
    private static final int POSITION = 5 * (Level.MAX_WIDTH / 8) - (Indicator.IND_WIDTH / 2);

    // constructs an indicator with x-coordinate X_POSITION
    public IndicatorC() {
        super(POSITION);
    }
}
