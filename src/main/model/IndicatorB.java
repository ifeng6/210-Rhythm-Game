package model;

public class IndicatorB extends Indicator {
    private static final int POSITION = 3 * (Level.MAX_WIDTH / 8) - (Indicator.IND_WIDTH / 2);

    // constructs an indicator with x-coordinate X_POSITION
    public IndicatorB() {
        super(POSITION);
    }
}
