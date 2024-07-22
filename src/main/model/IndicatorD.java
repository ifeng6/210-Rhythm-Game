package model;

public class IndicatorD extends Indicator {
    private static final int POSITION = 7 * (Level.MAX_WIDTH / 8) - (Indicator.IND_WIDTH / 2);

    // constructs an indicator with x-coordinate X_POSITION
    public IndicatorD() {
        super(POSITION);
    }
}
