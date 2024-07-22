package model;

public class IndicatorA extends Indicator {
    private static final int POSITION = (Level.MAX_WIDTH / 8) - (Indicator.IND_WIDTH / 2);

    // constructs an indicator with x-coordinate X_POSITION
    public IndicatorA() {
        super(POSITION);
    }
}
